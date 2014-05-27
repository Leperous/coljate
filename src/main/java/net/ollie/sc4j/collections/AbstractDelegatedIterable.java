package net.ollie.sc4j.collections;

import net.ollie.sc4j.access.Iteratable;

/**
 *
 * @author Ollie
 */
public abstract class AbstractDelegatedIterable<V>
        implements Iteratable<V> {

    protected abstract Iteratable<V> underlying();

    @Override
    public boolean isEmpty() {
        return this.underlying().isEmpty();
    }

    @Override
    public boolean contains(final Object object) {
        return this.underlying().contains(object);
    }

    @Override
    public Object[] toRawArray() {
        return this.underlying().toRawArray();
    }

    @Override
    public V head() {
        return this.underlying().head();
    }

    @Override
    public String toString() {
        return this.underlying().toString();
    }

}
