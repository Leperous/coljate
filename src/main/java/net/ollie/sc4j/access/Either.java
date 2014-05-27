package net.ollie.sc4j.access;

/**
 * Keyed by a boolean.
 *
 * @author Ollie
 */
public interface Either<V>
        extends Keyed<Boolean, V> {

    V get(boolean bool);

    @Override
    default V get(final Boolean bool) {
        return bool == null
                ? this.nullValue()
                : this.get(bool.booleanValue());
    }

    default V nullValue() {
        throw new IllegalArgumentException();
    }

}
