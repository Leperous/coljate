package net.coljate.tree;

import java.util.Iterator;

import net.coljate.collection.Collection;
import net.coljate.map.Entry;
import net.coljate.map.Map;
import net.coljate.set.MutableSet;
import net.coljate.set.Set;
import net.coljate.tree.impl.EmptyTree;
import net.coljate.tree.impl.SingletonTree;
import net.coljate.tree.navigation.TreeNavigation;

/**
 *
 * @author ollie
 * @since 1.0
 */
public interface Tree<K, V, E extends Entry<K, V>> extends Map<K, V> {

    E root();

    Collection<? extends Tree<K, V, E>> subtrees(Object key);

    @Override
    default E getEntry(final Object key) {
        return this.getEntry(key, TreeNavigation.getDefault());
    }

    default E getEntry(final Object key, final TreeNavigation search) {
        return search.findEntry(key, this);
    }

    @Override
    default Set<K> keys() {
        return this.keys(TreeNavigation.getDefault());
    }

    default Set<K> keys(final TreeNavigation navigation) {
        return navigation.collect(this, MutableSet.createHashSet())
                .transform(E::key)
                .distinct();
    }

    @Override
    default Collection<V> values() {
        throw new UnsupportedOperationException();
    }

    default Collection<V> values(final TreeNavigation navigation) {
        return navigation.collect(this, MutableSet.createHashSet()).transform(E::value);
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
