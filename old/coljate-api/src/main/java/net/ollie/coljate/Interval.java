package net.ollie.coljate;

import net.ollie.coljate.imposed.Distinctness.Unique;
import net.ollie.coljate.imposed.Ordered;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface Interval<V>
        extends Collection<V>, Ordered<V>, Unique<V> {

    @Nonnull
    Bound<V> lower();

    @Nonnull
    Bound<V> upper();

    @Override
    public default V first() {
        return this.lower().value();
    }

    interface Bound<V> {

        @CheckForNull
        V value();

        static <V> Default<V> of() {
            return Default.EMPTY;
        }

        @Nonnull
        static <V> Default<V> of(@CheckForNull final V value) {
            return value == null ? Default.EMPTY : new Default<>(value);
        }

        class Default<V> implements Bound<V> {

            static final Default EMPTY = new Default(null);

            private final V value;

            protected Default(final V value) {
                this.value = value;
            }

            @Override
            public V value() {
                return value;
            }

        }

    }

}
