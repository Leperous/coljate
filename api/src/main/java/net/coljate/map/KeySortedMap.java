package net.coljate.map;

import java.util.Comparator;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface KeySortedMap<K, V> extends SortedMap<K, V> {

    @Nonnull
    Comparator<? super K> keyComparator();

    @Override
    @Deprecated
    default Comparator<? super Entry<K, V>> comparator() {
        return Comparator.comparing(Entry::key, this.keyComparator());
    }

}
