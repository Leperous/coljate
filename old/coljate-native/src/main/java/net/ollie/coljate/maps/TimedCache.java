package net.ollie.coljate.maps;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

import static net.ollie.coljate.utils.functions.Functions.doNothing;
import net.ollie.coljate.utils.functions.Maybe;

import java.time.Duration;

/**
 *
 * @author Ollie
 */
public class TimedCache<K, V> extends DelegatedCache<K, V> {

    public static <K, V> TimedCache<K, V> readWriteExpires(
            final Cache<K, V> delegate,
            final Duration expireAfterWrite,
            final Duration expireAfterRead,
            final Timer timer) {
        return new TimedCache<>(
                delegate,
                MutableWrappedHashMap.create(),
                task -> timer.schedule(task, expireAfterWrite.toMillis()),
                task -> timer.schedule(task, expireAfterRead.toMillis())
        );
    }

    public static <K, V> TimedCache<K, V> writeExpires(
            final Cache<K, V> delegate,
            final Duration expireAfterWrite,
            final Timer timer) {
        return new TimedCache<>(
                delegate,
                MutableWrappedHashMap.create(),
                task -> timer.schedule(task, expireAfterWrite.toMillis()),
                task -> doNothing()
        );
    }

    public static <K, V> TimedCache<K, V> readExpires(
            final Cache<K, V> delegate,
            final Duration expireAfterRead,
            final Timer timer) {
        return new TimedCache<>(
                delegate,
                MutableWrappedHashMap.create(),
                task -> doNothing(),
                task -> timer.schedule(task, expireAfterRead.toMillis())
        );
    }

    private static final Maybe<TimerTask, Boolean> CANCEL = Maybe.of(TimerTask::cancel);
    private final Map.Mutable<K, TimerTask> tasks;
    private final Consumer<TimerTask> onPut, onRead;

    protected TimedCache(
            final Cache<K, V> delegate,
            final Map.Mutable<K, TimerTask> tasks,
            final Consumer<TimerTask> onPut,
            final Consumer<TimerTask> onRead) {
        super(delegate);
        this.tasks = tasks;
        this.onPut = onPut;
        this.onRead = onRead;
    }

    @Override
    protected void onWrite(final K key, final V value) {
        onPut.accept(this.createEvictTask(key));
    }

    @Override
    protected void onRead(final K key) {
        onRead.accept(this.createEvictTask(key));
    }

    @Override
    public void clear() {
        this.cancelAll();
        super.clear();
    }

    @Override
    protected void onRemove(final Object key) {
        final TimerTask task = tasks.remove(key);
        if (task != null) {
            task.cancel();
        }
    }

    private TimerTask createEvictTask(final K key) {
        final TimerTask newTask = new EvictTask(key);
        CANCEL.apply(tasks.put(key, newTask));
        return newTask;
    }

    private void cancelAll() {
        final Iterator<? extends Map.Entry<K, TimerTask>> iterator = tasks.entries().iterator();
        while (iterator.hasNext()) {
            CANCEL.apply(iterator.next().value());
            iterator.remove();
        }
    }

    private final class EvictTask
            extends TimerTask {

        private final K key;

        EvictTask(final K key) {
            this.key = key;
        }

        @Override
        public void run() {
            TimedCache.this.remove(key);
        }

    }

}
