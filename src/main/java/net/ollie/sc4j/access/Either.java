package net.ollie.sc4j.access;

/**
 * Keyed by a boolean.
 *
 * @author Ollie
 */
public interface Either<V>
        extends Keyed.Single<Boolean, V> {

    V get(boolean bool);

    @Override
    default V get(final Object bool) {
        return bool == null || !(bool instanceof Boolean)
                ? this.nullValue(bool)
                : this.get(((Boolean) bool).booleanValue());
    }

    default V nullValue(final Object key) {
        throw new IllegalArgumentException("Invalud key " + key);
    }

}
