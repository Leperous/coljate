package net.ollie.coljate.set;

import java.util.Iterator;

import net.ollie.coljate.map.Map;
import net.ollie.coljate.map.MutableMap;
import net.ollie.coljate.set.mixin.CopiedToHashSet;
import net.ollie.coljate.utils.Assertions;

/**
 *
 * @author Ollie
 */
public class MutableWrappedMultiSet<T>
        extends AbstractSet<T>
        implements MultiSet<T>, MutableSet<T>, CopiedToHashSet<T> {

    public static <T> MutableWrappedMultiSet<T> viewOf(final MutableMap<T, Integer> count) {
        return new MutableWrappedMultiSet<>(count);
    }

    public static <T> MutableWrappedMultiSet<T> copyOf(final Map<T, Integer> count) {
        return viewOf(count.mutableCopy());
    }

    private final MutableMap<T, Integer> count;

    protected MutableWrappedMultiSet(final MutableMap<T, Integer> count) {
        this.count = count;
    }

    @Override
    public int count(final Object object) {
        return count.getOrDefault(object, () -> 0);
    }

    @Override
    public Set<T> unique() {
        return count.keys();
    }

    @Override
    public MutableWrappedMultiSet<T> mutableCopy() {
        return copyOf(count);
    }

    @Override
    public boolean isEmpty() {
        return count.isEmpty();
    }

    @Override
    public boolean add(final T key) {
        count.compute(key, (k, current) -> (current == null ? 0 : current) + 1);
        return true;
    }

    @Override
    @Deprecated
    public boolean remove(final Object key, final int times) {
        final int current = this.count(key);
        this.decrement((T) key, times);
        return current > 0;
    }

    public int decrement(final T key, final int count) {
        //FIXME integer overflow
        Assertions.checkArgument(count >= 0, () -> "Cannot decrement by negative amount!");
        return this.count.compute(key, (k, current) -> current == null || current <= count ? null : current - count);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean removeAll(final Object element) {
        final Integer count = this.count.deleteKey(element);
        return count != null && count > 0;
    }

    @Override
    public void clear() {
        count.clear();
    }

    @Override
    public Iterator<T> iterator() {
        return count.keys().iterator();
    }

    @Override
    public MultiSet<T> tail() {
        throw new UnsupportedOperationException(); //TODO
    }

}
