package net.ollie.sc4j.collections;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;

import net.ollie.sc4j.Array;
import net.ollie.sc4j.List;
import net.ollie.sc4j.utils.Arrays;
import net.ollie.sc4j.utils.Iterables;

/**
 * A mutable list implementation that is backed by a native
 * {@link java.util.Deque deque}.
 *
 * @author Ollie
 */
public class MutableList<V>
        extends AbstractNativeCollection<V>
        implements List.Mutable<V> {

    public static <V> MutableList<V> copy(final Iterable<? extends V> iterable) {
        final java.util.Deque<V> deque = new java.util.LinkedList<>();
        for (final V item : iterable) {
            deque.add(item);
        }
        return view(deque);
    }

    public static <V> MutableList<V> view(final java.util.Deque<? extends V> deque) {
        return new MutableList(deque);
    }

    private final java.util.Deque<V> delegate;

    protected MutableList(final java.util.Deque<V> delegate) {
        this.delegate = delegate;
    }

    @Override
    protected java.util.Deque<V> delegate() {
        return delegate;
    }

    @Override
    public void prefix(final V value) {
        delegate.addFirst(value);
    }

    @Override
    public void prefixAll(final Iterable<? extends V> values) {
        final java.util.List<? extends V> list = Arrays.asList(values);
        final java.util.ListIterator<? extends V> iterator = list.listIterator(list.size());
        while (iterator.hasPrevious()) {
            delegate.addFirst(iterator.previous());
        }
    }

    @Override
    public void suffix(final V value) {
        delegate.addLast(value);
    }

    @Override
    public void suffixAll(Iterable<? extends V> values) {
        delegate.addAll(Iterables.toCollection(values));
    }

    @Override
    public List<V> tail() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <V2> List<V2> map(Function<? super V, ? extends V2> function) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<V> filter(Predicate<? super V> predicate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Array.Mutable<V> toArray() {
        return MutableArray.copy(this);
    }

    @Override
    public List.Mutable<V> mutable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List.Immutable<V> immutable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Comparator<? super V> comparator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public V first() {
        return delegate.peekFirst();
    }

    @Override
    public V last() {
        return delegate.peekLast();
    }

    @Override
    public V lastOrElse(Predicate<? super V> predicate, V defaultValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public V findOrElse(Predicate<? super V> predicate, V defaultValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sort(Comparator<? super V> comparator) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof List
                && this.equals((List) object);
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

}
