package net.ollie.coljate;

import net.ollie.coljate.access.Traversable;
import net.ollie.coljate.utils.Strings;

/**
 * Delegates operations to some underlying {@link java.util.Collection}.
 *
 * @author Ollie
 */
public abstract class AbstractNativelyDelegatedTraversable<V>
        implements Traversable<V> {

    protected abstract java.util.Collection<V> delegate();

    @Override
    public boolean isEmpty() {
        return this.delegate().isEmpty();
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public boolean contains(final Object object) {
        return this.delegate().contains(object);
    }

    @Override
    public String toString() {
        return this.toString(",");
    }

    public String toString(final String separator) {
        return Strings.toString(this, this.delegate(), separator);
    }

}
