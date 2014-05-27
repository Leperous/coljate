package net.ollie.sc4j.collections;

import net.ollie.sc4j.Array;
import net.ollie.sc4j.utils.Iterables;

/**
 *
 * @author Ollie
 */
public abstract class AbstractArray<V>
        implements Array<V> {

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
