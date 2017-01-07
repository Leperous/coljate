package net.coljate.map;

import net.coljate.set.Set;

/**
 *
 * @author ollie
 * @see <a href="https://en.wikipedia.org/wiki/Bijection">Bijection</a>
 */
public interface BidirectionalMap<K, V> extends Map<K, V> {

    @Override
    Entry<K, V> getEntry(Object key);

    BidirectionalMap<V, K> inverse();

    default Entry<V, K> getInverseEntry(final Object key) {
        return this.inverse().getEntry(key);
    }

    @Override
    default Set<V> values() {
        return this.inverse().keys();
    }

    @Override
    MutableBidirectionalMap<K, V> mutableCopy();

}
