package net.coljate.tree;

import net.coljate.collection.Collection;
import net.coljate.map.Entry;
import net.coljate.map.MutableEntry;
import net.coljate.map.MutableMap;
import net.coljate.util.iterator.CovariantIterator;
import net.coljate.util.Functions;

/**
 *
 * @author ollie
 * @since 1.0
 */
public interface MutableTree<K, V, E extends MutableEntry<K, V>>
        extends Tree<K, V, E>, MutableMap<K, V> {

    @Override
    default E getEntry(final Object key) {
        return Tree.super.getEntry(key);
    }

    @Override
    default V put(final K key, final V value) {
        return Functions.ifNonNull(
                this.getEntry(key),
                entry -> entry.getAndSetValue(value));
    }

    @Override
    Collection<? extends MutableTree<K, V, E>> subtrees(Object key);

    @Override
    default CovariantIterator<Entry<K, V>, E> iterator() {
        return Tree.super.iterator();
    }

}
