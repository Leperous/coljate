package net.coljate.tree;

import net.coljate.UnmodifiableIterator;
import net.coljate.collection.Collection;
import net.coljate.collection.ImmutableCollection;
import net.coljate.map.Entry;
import net.coljate.map.ImmutableEntry;
import net.coljate.map.ImmutableMap;
import net.coljate.set.ImmutableSet;

/**
 *
 * @author ollie
 * @since 1.0
 */
public interface ImmutableTree<K, V, E extends ImmutableEntry<K, V>>
        extends Tree<K, V, E>, ImmutableMap<K, V> {

    @Override
    E root();

    @Override
    Collection<? extends ImmutableTree<K, V, E>> subtrees(Object key);

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
    default UnmodifiableIterator<Entry<K, V>> iterator() {
        return UnmodifiableIterator.wrap(Tree.super.iterator());
    }

    @Override
    @Deprecated
    default ImmutableTree<K, V, E> immutableCopy() {
        return this;
    }

}
