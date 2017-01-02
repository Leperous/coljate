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
public interface ImmutableTree<K, V, E extends ImmutableNode<K, V>>
        extends Tree<K, V, E>, ImmutableMap<K, V> {

    @Override
    E root();

    @Override
    default E getEntry(final Object key) {
        return Tree.super.getEntry(key);
    }

    @Override
    ImmutableTree<K, V, ? extends E> with(K key, V value);

    @Override
    default ImmutableSet<K> keys() {
        return Tree.super.keys().immutableCopy();
    }

    @Override
    default ImmutableCollection<V> values() {
        return Tree.super.values().immutableCopy();
    }

    @Override
    default UnmodifiableCovariantIterator<Entry<K, V>, E> iterator() {
        return UnmodifiableCovariantIterator.wrap(Tree.super.iterator());
    }

    @Override
    @Deprecated
    default ImmutableTree<K, V, E> immutableCopy() {
        return this;
    }

}
