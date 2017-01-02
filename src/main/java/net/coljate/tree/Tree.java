package net.coljate.tree;

import java.util.Iterator;
import java.util.Objects;

import javax.annotation.CheckForNull;

import net.coljate.collection.Collection;
import net.coljate.map.Entry;
import net.coljate.map.Map;
import net.coljate.set.Set;
import net.coljate.tree.impl.EmptyTree;
import net.coljate.tree.impl.SingletonTree;
import net.coljate.tree.navigation.TreeNavigation;
import net.coljate.util.iterator.CovariantIterator;

/**
 * A {@link #root rooted} tree.
 *
 * @author ollie
 * @since 1.0
 */
public interface Tree<K, V, N extends Node<K, V>>
        extends Map<K, V> {

    @CheckForNull
    N root();

    @Override
    default N getEntry(final Object key) {
        return this.getEntry(key, TreeNavigation.getDefault()); //FIXME
    }

    default N getEntry(final Object key, final TreeNavigation navigation) {
        return navigation.findNode(this, node -> Objects.equals(key, node.key()));
    }

    default Collection<N> entries(final TreeNavigation navigation) { //FIXME type N
        return navigation.collect(this);
    }

    @Override
    default Set<K> keys() {
        return this.keys(TreeNavigation.getDefault());
    }

    default Set<K> keys(final TreeNavigation navigation) {
        return this.entries(navigation)
                .transform(N::key)
                .distinct();
    }

    @Override
    default Collection<V> values() {
        return this.values(TreeNavigation.getDefault());
    }

    default Collection<V> values(final TreeNavigation navigation) {
        return this.entries(navigation).transform(N::value);
    }

    @Override
    default CovariantIterator<Entry<K, V>, N> iterator() {
        return CovariantIterator.of(this.iterator(TreeNavigation.getDefault()));
    }

    default Iterator<N> iterator(final TreeNavigation navigation) {
        return this.entries(navigation).iterator();
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
