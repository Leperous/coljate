package net.coljate.map;

import net.coljate.set.SequentialSet;
import net.coljate.set.SortedSet;
import net.coljate.tree.impl.RedBlackTreeMap;

/**
 *
 * @author Ollie
 */
public interface SortedMap<K, V> extends SortedSet<Entry<K, V>>, Map<K, V> {

    @Override
    SequentialSet<K> keys();

    @Override
    default MutableSortedMap<K, V> mutableCopy() {
        return RedBlackTreeMap.copyOf(this);
    }

}
