package net.ollie.coljate;

import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.utils.Strings;

/**
 *
 * @author Ollie
 */
public abstract class AbstractStreamable<V>
        implements Streamable<V> {

    @Override
    public boolean equals(final Object object) {
        return object instanceof Streamable
                && this.equals((Streamable<?>) object);
    }

    @Override
    public int hashCode() {
        return this.hash();
    }

    @Override
    public String toString() {
        return this.toString(",");
    }

    @Override
    public String toString(final String separator) {
        return Strings.toString(this, separator);
    }

}
