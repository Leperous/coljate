package net.coljate.tree;

import net.coljate.collection.ImmutableCollection;
import net.coljate.map.Entry;
import net.coljate.map.ImmutableMap;
import net.coljate.set.ImmutableSet;
import net.coljate.util.iterator.UnmodifiableCovariantIterator;

/**
 *
 * @author ollie
 * @since 1.0
 */
public interface ImmutableTree<K, V, N extends ImmutableNode<K, V, N>>
        extends Tree<K, V, N>, ImmutableMap<K, V> {

    @Override
    default N getEntry(final Object key) {
        return Tree.super.getEntry(key);
    }

    @Override
    ImmutableTree<K, V, ?> with(K key, V value);

    @Override
    default ImmutableSet<K> keys() {
        return Tree.super.keys().immutableCopy();
    }

    @Override
    default ImmutableCollection<V> values() {
        return Tree.super.values().immutableCopy();
    }

    @Override
    default UnmodifiableCovariantIterator<Entry<K, V>, N> iterator() {
        return UnmodifiableCovariantIterator.wrap(Tree.super.iterator());
    }

    @Override
    @Deprecated
    default ImmutableTree<K, V, N> immutableCopy() {
        return this;
    }

}
