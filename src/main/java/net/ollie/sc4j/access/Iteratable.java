package net.ollie.sc4j.access;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import net.ollie.sc4j.utils.Arrays;
import net.ollie.sc4j.utils.Iterables;
import net.ollie.sc4j.utils.Iterators;
import net.ollie.sc4j.utils.UnmodifiableIterator;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 * Iterable collection. Introduces "reduce" method.
 *
 * @author Ollie
 */
public interface Iteratable<V>
        extends Traversable<V>, Iterable<V> {

    default int size() {
        return Iterables.doCount(this);
    }

    @Override
    default boolean isEmpty() {
        return !this.iterator().hasNext();
    }

    @CheckReturnValue
    default <R> R reduce(final BiFunction<R, V, ? extends R> function, final R initial) {
        R current = initial;
        for (final V element : this) {
            current = function.apply(current, element);
        }
        return current;
    }

    @Nonnull
    Object[] toRawArray();

    @Override
    default boolean contains(final Object object) {
        for (final V element : this) {
            if (Objects.equals(element, object)) {
                return true;
            }
        }
        return false;
    }

    @Override
    default V head() {
        final Iterator<V> iterator = this.iterator();
        return iterator.hasNext()
                ? iterator.next()
                : null;
    }

    @Override
    Iteratable<V> tail();

    @Override
    default V findOrElse(final Predicate<? super V> predicate, final V defaultValue) {
        for (final V element : this) {
            if (predicate.test(element)) {
                return element;
            }
        }
        return defaultValue;
    }

    @Override
    Iteratable.Mutable<V> mutableCopy();

    @Override
    Iteratable.Immutable<V> immutableCopy();

    default boolean equals(final Iteratable<?> that) {
        return that != null
                && Iterables.equals(this, that);
    }

    default int hash() {
        return Iterables.sumHashCode(this); //Ignore order
    }

    interface Mutable<V>
            extends Iteratable<V>, Traversable.Mutable<V> {

    }

    interface Immutable<V>
            extends Iteratable<V>, Traversable.Immutable<V> {

        @Override
        Iteratable.Immutable<V> tail();

        @Override
        UnmodifiableIterator<V> iterator();

        @Override
        default Iteratable.Immutable<V> immutableCopy() {
            return this;
        }

    }

    abstract class Empty<V>
            implements Iteratable.Immutable<V> {

        @Override
        public UnmodifiableIterator<V> iterator() {
            return Iterators.empty();
        }

        @Override
        public Object[] toRawArray() {
            return Arrays.empty();
        }

        @Override
        public Iteratable.Immutable<V> tail() {
            return this;
        }

        @Override
        public V head() {
            return null;
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public boolean contains(final Object object) {
            return false;
        }

        @Override
        public <V2> Iteratable<V2> map(Function<? super V, ? extends V2> function) {
            return (Iteratable<V2>) this;
        }

        @Override
        public Iteratable<V> filter(final Predicate<? super V> predicate) {
            return this;
        }

        @Override
        public V findOrElse(final Predicate<? super V> predicate, final V defaultValue) {
            return defaultValue;
        }

    }

    abstract class Singleton<V>
            implements Iteratable.Immutable<V> {

        private final V value;

        protected Singleton(final V value) {
            this.value = value;
        }

        @Override
        public UnmodifiableIterator<V> iterator() {
            return Iterators.singleton(value);
        }

        @Override
        public Object[] toRawArray() {
            return new Object[]{value};
        }

        @Override
        public V head() {
            return value;
        }

        @Override
        public boolean isEmpty() {
            return value != null;
        }

        @Override
        public boolean contains(final Object object) {
            return Objects.equals(value, object);
        }

        @Override
        public V findOrElse(final Predicate<? super V> predicate, final V defaultValue) {
            return predicate.test(value)
                    ? value
                    : defaultValue;
        }

        @Override
        public boolean equals(final Object object) {
            return object instanceof Iteratable
                    && this.equals((Iteratable) object);
        }

        @Override
        public int hashCode() {
            return this.hash();
        }

    }

}
