package net.ollie.sc4j.collections;

import java.io.Serializable;
import java.util.Comparator;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.function.Predicate;

import net.ollie.sc4j.Array;
import net.ollie.sc4j.access.Keyed;
import net.ollie.sc4j.imposed.Unique;
import net.ollie.sc4j.utils.Iterables;

/**
 *
 * @author Ollie
 */
public class ImmutableArray<V>
        extends ImmutableList<V>
        implements Array.Immutable<V>, Serializable {

    private static final long serialVersionUID = 1L;

    public static <V> Array.Immutable<V> copy(final V[] array) {
        return new ImmutableArray<>(MutableArray.copy(array));
    }

    public static <V> Array.Immutable<V> copy(final Iterable<? extends V> iterable) {
        return new ImmutableArray<>(MutableArray.copy(iterable));
    }

    private final Array<V> underlying;

    protected ImmutableArray(final Array<V> underlying) {
        super(underlying);
        this.underlying = underlying;
    }

    protected <T> Array.Immutable<T> viewOf(final Array<T> list) {
        return new ImmutableArray<>(list);
    }

    @Override
    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    protected Array<V> underlying() {
        return underlying;
    }

    @Override
    public Array.Mutable<V> mutable() {
        return this.underlying().mutable();
    }

    @Override
    public V get(final int index) throws IndexOutOfBoundsException {
        return this.underlying().get(index);
    }

    @Override
    public Array.Immutable<V> sort(final Comparator<? super V> comparator) {
        return super.sort(comparator).toArray();
    }

    @Override
    public Array.Immutable<V> withPrefix(final V value) {
        return super.withPrefix(value).toArray();
    }

    @Override
    public Array.Immutable<V> withSuffix(final V value) {
        return super.withSuffix(value).toArray();
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public Array.Immutable<V> withoutFirst(final Object value) {
        return super.withoutFirst(value).toArray();
    }

    @Override
    public Array.Immutable<V> withoutAll(final Object value) {
        return super.withoutAll(value).toArray();
    }

    @Override
    public Array.Immutable<V> tail() {
        return super.tail().toArray();
    }

    @Override
    public <V2> Array.Immutable<V2> map(final Function<? super V, ? extends V2> function) {
        return super.map(function).toArray();
    }

    @Override
    public Array.Immutable<V> filter(final Predicate<? super V> predicate) {
        return super.filter(predicate).toArray();
    }

    @Override
    public Array.Immutable<V> segment(final int from, final int to) {
        return this.underlying().segment(from, to).immutable();
    }

    @Override
    public OptionalInt indexOf(final Object value) {
        return this.underlying().indexOf(value);
    }

    @Override
    public Unique<Integer> keys() {
        return this.underlying().keys();
    }

    @Override
    public Array.Immutable<V> filterKeys(final Predicate<? super Integer> predicate) {
        return this.underlying().filterKeys(predicate).immutable();
    }

    @Override
    public <K2> Keyed<K2, V> mapKeys(final Function<? super Integer, ? extends K2> function) {
        throw new UnsupportedOperationException(); //TODO mapkeys
    }

    @Override
    public Array.Immutable<V> toArray() {
        return this;
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof Array
                && this.equals((Array) object);
    }

    @Override
    public int hashCode() {
        return Iterables.hashCode(this);
    }

}
