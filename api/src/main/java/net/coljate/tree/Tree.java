package net.coljate.tree;

import java.util.Iterator;
import java.util.Objects;

import javax.annotation.CheckForNull;

import net.coljate.collection.Collection;
import net.coljate.map.Entry;
import net.coljate.map.Map;
import net.coljate.set.Set;
import net.coljate.tree.navigation.TreeNavigation;
import net.coljate.util.iterator.CovariantIterator;

/**
 * A {@link #root rooted} tree.
 *
 * @author ollie
 * @since 1.0
 */
public interface Tree<K, V, N extends Node<K, V, N>>
        extends Map<K, V> {

    @CheckForNull
    N root();

    @Override
    default N getEntry(final Object key) {
        return this.getEntry(key, TreeNavigation.getDefault());
    }

    default N getEntry(final Object key, final TreeNavigation navigation) {
        return navigation.first(this, node -> Objects.equals(key, node.key()));
    }

    default Collection<N> entries(final TreeNavigation navigation) {
        return navigation.collect(this, node -> true);
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

}
