package net.ollie.sc4j.collections;

import java.util.Iterator;

import net.ollie.sc4j.access.Iteratable;

/**
 * Delegates operations to some underlying {@link java.util.Collection}.
 *
 * @author Ollie
 */
public abstract class AbstractNativeCollection<V>
        implements Iteratable<V> {

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
    public int size() {
        return this.delegate().size();
    }

    @Override
    public Object[] toRawArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<V> iterator() {
        return this.delegate().iterator();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + this.delegate();
    }

}
