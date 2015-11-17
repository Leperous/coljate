package net.ollie.coljate;

/**
 *
 * @author Ollie
 * @see java.util.AbstractCollection
 */
public abstract class AbstractCollection<T> implements Collection<T> {

    @Override
    public String toString() {
        return Collection.toString(this);
    }

}
