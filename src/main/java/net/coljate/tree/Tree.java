package net.coljate.tree;

import java.util.Iterator;

import net.coljate.collection.Collection;
import net.coljate.map.Entry;
import net.coljate.map.Map;
import net.coljate.set.Set;
import net.coljate.tree.impl.EmptyTree;
import net.coljate.tree.impl.SingletonTree;
import net.coljate.tree.search.TreeSearch;

/**
 *
 * @author ollie
 * @since 1.0
 */
public interface Tree<K, V, E extends Entry<K, V>> extends Map<K, V> {

    E root();

    Collection<? extends Tree<K, V, E>> subtrees(Object key);

    @Override
    default E entry(final Object key) {
        return this.entry(key, TreeSearch.DEFAULT);
    }

    default E entry(final Object key, final TreeSearch search) {
        return search.findEntry(key, this);
    }

    @Override
    default Set<K> keys() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    default Collection<V> values() {
        throw new UnsupportedOperationException();
    }

    @Override
    default Iterator<Entry<K, V>> iterator() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    default MutableTree<K, V, ?> mutableCopy() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    default ImmutableTree<K, V, ?> immutableCopy() {
        throw new UnsupportedOperationException();
    }

    static <K, V> EmptyTree<K, V> of() {
        return EmptyTree.instance();
    }

    static <K, V> SingletonTree<K, V> of(final K key, final V value) {
        return new SingletonTree<>(key, value);
    }

}
