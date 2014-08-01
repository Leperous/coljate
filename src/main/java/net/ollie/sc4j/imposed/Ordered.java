package net.ollie.sc4j.imposed;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

import net.ollie.sc4j.access.Traversable;
import net.ollie.sc4j.utils.Suppliers;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 * Values are ordered in some manner, for example by insertion order.
 *
 * @author Ollie
 * @see Sorted
 */
public interface Ordered<V>
        extends Traversable<V> {

    @CheckForNull
    V first();

    @Override
    default V head() {
        return this.first();
    }

    @Nonnull
    default V last(final Predicate<? super V> predicate) throws NoSuchElementException {
        return this.lastOrElse(predicate, Suppliers.noSuchElement());
    }

    V lastOrElse(Predicate<? super V> predicate, V defaultValue);

    @Nonnull
    default V lastOrElse(final Predicate<? super V> predicate, final Supplier<V> supplier) throws NoSuchElementException {
        final V last = this.lastOrElse(predicate, (V) null);
        if (last == null) {
            return supplier.get();
        } else {
            return last;
        }
    }

    @Override
    Ordered.Mutable<V> mutableCopy();

    @Override
    Ordered.Immutable<V> immutableCopy();

    default boolean equals(final Ordered<?> that) {
        return that != null
                && Objects.equals(this.head(), that.head())
                && Objects.equals(this.tail(), that.tail());
    }

    default int hash() {
        return this.isEmpty()
                ? 1
                : Objects.hashCode(this.head()) * Objects.hashCode(this.tail());
    }

    /**
     *
     * @param <V>
     */
    interface Mutable<V>
            extends Ordered<V>, Traversable.Mutable<V> {

    }

    /**
     *
     * @param <V>
     */
    interface Immutable<V>
            extends Ordered<V>, Traversable.Immutable<V> {

        @Override
        default Ordered.Immutable<V> immutableCopy() {
            return this;
        }

    }

}
