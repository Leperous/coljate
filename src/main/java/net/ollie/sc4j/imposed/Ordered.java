package net.ollie.sc4j.imposed;

import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.function.Supplier;

import net.ollie.sc4j.utils.Suppliers;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 * Values are ordered in some manner, for example by insertion order.
 *
 * @author Ollie
 * @see Sorted
 */
public interface Ordered<V> {

    @CheckForNull
    V first();

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

}
