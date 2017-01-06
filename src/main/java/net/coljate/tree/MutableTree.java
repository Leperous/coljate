package net.coljate.tree;

import javax.annotation.Nonnull;

import net.coljate.map.Entry;
import net.coljate.map.MutableMap;
import net.coljate.util.iterator.CovariantIterator;

/**
 *
 * @author ollie
 * @since 1.0
 */
public interface MutableTree<K, V, N extends MutableNode<K, V, N>>
        extends Tree<K, V, N>, MutableMap<K, V> {

    @Override
    default N getEntry(final Object key) {
        return Tree.super.getEntry(key);
    }

    @Override
    V put(K key, V value);

    @Override
    default V evict(final Object key) {
        final N node = this.getEntry(key);
        return node != null && this.remove(node)
                ? node.value()
                : null;
    }

    @Override
    default boolean remove(final Object key, final Object value) {
        final N node = this.getEntry(key);
        return node != null
                && node.contains(key, value)
                && this.remove(node);
    }

    boolean remove(@Nonnull N node);

    @Override
    default CovariantIterator<Entry<K, V>, N> iterator() {
        return Tree.super.iterator();
    }

}
