package net.ollie.coljate;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;

import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.utils.iterators.UnmodifiableIterator;

import com.google.common.collect.ImmutableCollection;
import java.io.Serializable;

/**
 *
 * @author Ollie
 */
public class ImmutableGuavaCollection<V> implements Streamable.Immutable<V>, Serializable {

    private static final long serialVersionUID = 1L;

    public static <V> ImmutableGuavaCollection<V> view(final ImmutableCollection<? extends V> collection) {
        return new ImmutableGuavaCollection<>(collection);
    }

    private final ImmutableCollection<? extends V> underlying;

    protected ImmutableGuavaCollection(final ImmutableCollection<? extends V> underlying) {
        this.underlying = underlying;
    }

    @Override
    public Streamable.Immutable<V> tail() {
        throw new UnsupportedOperationException("tail not supported yet!");
    }

    @Override
    public Stream<V, ? extends Streamable<V>> stream() {
        throw new UnsupportedOperationException("stream not supported yet!");
    }

    @Override
    public Mutable<V> mutableCopy() {
        throw new UnsupportedOperationException("mutableCopy not supported yet!");
    }

    @Override
    public Immutable<V> immutableCopy() {
        throw new UnsupportedOperationException("immutableCopy not supported yet!");
    }

    @Override
    public V head() {
        throw new UnsupportedOperationException("head not supported yet!");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("isEmpty not supported yet!");
    }

    @Override
    public boolean contains(Object object) {
        throw new UnsupportedOperationException("contains not supported yet!");
    }

    @Override
    public UnmodifiableIterator<V> iterator() {
        throw new UnsupportedOperationException("iterator not supported yet!");
    }

    @Override
    public Optional<V> findAny(Predicate<? super V> predicate) throws NoSuchElementException {
        throw new UnsupportedOperationException("findAny not supported yet!");
    }

}
