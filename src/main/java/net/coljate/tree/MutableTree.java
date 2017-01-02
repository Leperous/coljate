package net.coljate.tree;

import net.coljate.map.Entry;
import net.coljate.map.MutableMap;
import net.coljate.util.Functions;
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
    default V put(final K key, final V value) {
        return Functions.ifNonNull(
                this.getEntry(key),
                entry -> entry.getAndSetValue(value));
    }

    @Override
    default CovariantIterator<Entry<K, V>, N> iterator() {
        return Tree.super.iterator();
    }

}
