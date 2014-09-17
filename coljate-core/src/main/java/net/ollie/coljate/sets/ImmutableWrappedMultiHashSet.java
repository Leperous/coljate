package net.ollie.coljate.sets;

import java.util.Optional;
import java.util.function.Predicate;

import net.ollie.coljate.AbstractWrappedStreamable;
import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.utils.iterators.UnmodifiableIterator;
import net.ollie.coljate.utils.numeric.NonNegativeInteger;

/**
 *
 * @author Ollie
 */
public class ImmutableWrappedMultiHashSet<V>
        extends AbstractWrappedStreamable<V>
        implements MultiSet.Immutable<V> {

    public static <V> MultiSet.Immutable<V> copy(final Iterable<? extends V> iterable) {
        return new ImmutableWrappedMultiHashSet<>(MutableWrappedMultiHashSet.copy(iterable));
    }

    private final MutableWrappedMultiHashSet<V> underlying;

    ImmutableWrappedMultiHashSet(final MutableWrappedMultiHashSet<V> underlying) {
        this.underlying = underlying;
    }

    @Override
    protected Streamable<V> underlying() {
        return underlying;
    }

    @Override
    public Optional<V> findAny(Predicate<? super V> predicate) {
        throw new UnsupportedOperationException("findAny not supported yet!");
    }

    @Override
    public Set<V> unique() {
        throw new UnsupportedOperationException("unique not supported yet!");
    }

    @Override
    public Set<V> keys() {
        throw new UnsupportedOperationException("keys not supported yet!");
    }

    @Override
    public Streamable<NonNegativeInteger> values() {
        throw new UnsupportedOperationException("values not supported yet!");
    }

    @Override
    public MultiSet.Mutable<V> mutableCopy() {
        throw new UnsupportedOperationException("mutableCopy not supported yet!");
    }

    @Override
    public MultiSet.Immutable<V> immutableCopy() {
        throw new UnsupportedOperationException("immutableCopy not supported yet!");
    }

    @Override
    public NonNegativeInteger get(Object key) {
        throw new UnsupportedOperationException("get not supported yet!");
    }

    @Override
    public NonNegativeInteger count(Object object) {
        throw new UnsupportedOperationException("count not supported yet!");
    }

    @Override
    public Immutable<V> tail() {
        throw new UnsupportedOperationException("tail not supported yet!");
    }

    @Override
    public UnmodifiableIterator<V> iterator() {
        return UnmodifiableIterator.of(this.underlying().iterator());
    }

}
