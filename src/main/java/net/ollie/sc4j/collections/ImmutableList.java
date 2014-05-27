package net.ollie.sc4j.collections;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;

import net.ollie.sc4j.Array;
import net.ollie.sc4j.List;
import net.ollie.sc4j.utils.Iterables;
import net.ollie.sc4j.utils.Iterables.UnmodifiableIterator;

/**
 *
 * @author Ollie
 */
public class ImmutableList<V>
        extends AbstractDelegatedIteratable<V>
        implements List.Immutable<V> {

    private final List<V> underlying;

    protected ImmutableList(final List<V> underlying) {
        this.underlying = underlying;
    }

    @Override
    protected List<V> underlying() {
        return underlying;
    }

    protected <T> List.Immutable<T> viewOf(final List<T> list) {
        return list instanceof List.Immutable
                ? (List.Immutable<T>) list
                : new ImmutableList<>(list);
    }

    @Override
    public List.Mutable<V> mutable() {
        return this.underlying().mutable();
    }

    @Override
    public <V2> List.Immutable<V2> map(Function<? super V, ? extends V2> function) {
        return this.underlying().map(function).immutable();
    }

    @Override
    public List.Immutable<V> tail() {
        return this.underlying().tail().immutable();
    }

    @Override
    public List.Immutable<V> withPrefix(final V value) {
        final List.Mutable<V> copy = this.mutable();
        copy.prefix(value);
        return this.viewOf(copy);
    }

    @Override
    public List.Immutable<V> withSuffix(final V value) {
        final List.Mutable<V> copy = this.mutable();
        copy.suffix(value);
        return this.viewOf(copy);
    }

    @Override
    public List.Immutable<V> withoutFirst(final Object value) {
        final List.Mutable<V> copy = this.mutable();
        return copy.removeFirst(value)
                ? this.viewOf(copy)
                : this;
    }

    @Override
    public List.Immutable<V> withoutAll(final Object value) {
        final List.Mutable<V> copy = this.mutable();
        return copy.removeAll(value) > 0
                ? this.viewOf(copy)
                : this;
    }

    @Override
    public UnmodifiableIterator<V> iterator() {
        return Iterables.unmodifiable(this.underlying().iterator());
    }

    @Override
    public List.Immutable<V> filter(final Predicate<? super V> predicate) {
        return this.underlying().filter(predicate).immutable();
    }

    @Override
    public Comparator<? super V> comparator() {
        return this.underlying().comparator();
    }

    @Override
    public V first() {
        return this.underlying().first();
    }

    @Override
    public V last() {
        return this.underlying().last();
    }

    @Override
    public V lastOrElse(final Predicate<? super V> predicate, V defaultValue) {
        return this.underlying().lastOrElse(predicate, defaultValue);
    }

    @Override
    public List.Immutable<V> sort(final Comparator<? super V> comparator) {
        final List.Mutable<V> copy = this.mutable();
        copy.sort(comparator);
        return this.viewOf(copy);
    }

    @Override
    public Array.Immutable<V> toArray() {
        return this.underlying().immutable().toArray();
    }

}
