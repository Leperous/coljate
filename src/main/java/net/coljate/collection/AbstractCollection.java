package net.coljate.collection;

import net.coljate.util.Strings;

/**
 *
 * @author ollie
 */
public abstract class AbstractCollection<T> implements Collection<T> {

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[" + Strings.toString(this) + "]";
    }

}
