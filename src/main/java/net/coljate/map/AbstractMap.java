package net.coljate.map;

import net.coljate.collection.AbstractCollection;

/**
 *
 * @author ollie
 */
public abstract class AbstractMap<K, V>
        extends AbstractCollection<Entry<K, V>>
        implements Map<K, V> {

    @Override
    protected boolean equals(final AbstractCollection<?> that) {
        return that instanceof AbstractMap
                && this.equals((AbstractMap) that);
    }

    protected abstract boolean equals(AbstractMap<?, ?> that);

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException(); //TODO
    }

}
