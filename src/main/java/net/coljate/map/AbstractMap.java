package net.coljate.map;

import net.coljate.collection.AbstractCollection;
import net.coljate.util.Equality;
import net.coljate.util.Hash;

/**
 *
 * @author ollie
 */
public abstract class AbstractMap<K, V>
        extends AbstractCollection<Entry<K, V>>
        implements Map<K, V> {

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    protected boolean equals(final AbstractCollection<?> that) {
        return that instanceof AbstractMap
                && this.equals((AbstractMap) that);
    }

    protected boolean equals(final AbstractMap<?, ?> that) {
        return this.getClass() == that.getClass()
                && Equality.unorderedEquals(this, that);
    }

    @Override
    public int hashCode() {
        return Hash.unorderedHash(this);
    }

}
