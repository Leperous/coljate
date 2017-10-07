package net.coljate.map;

import java.util.Comparator;
import java.util.function.Predicate;

import javax.annotation.Nonnull;

import net.ollie.goat.functions.Predicates;

/**
 * Map sorted by keys.
 *
 * @author Ollie
 * @see SortedMap
 */
public interface KeySortedMap<K, V> extends SortedMap<K, V> {

    @Nonnull
    Comparator<? super K> keyComparator();

    @Override
    @Deprecated
    default Comparator<? super Entry<K, V>> comparator() {
        return Comparator.comparing(Entry::key, this.keyComparator());
    }

    KeySortedMap<K, V> filterKeys(Predicate<? super K> keyPredicate);

    @Override
    KeySortedMap<K, V> filter(Predicate<? super Entry<K, V>> predicate);

    @Nonnull
    default KeySortedMap<K, V> greaterThanKey(final K key, final boolean orEqual) {
        return this.filterKeys(Predicates.greaterThan(key, orEqual, this.keyComparator()));
    }

    @Override
    @Deprecated
    default KeySortedMap<K, V> greaterThan(final Entry<K, V> entry, final boolean orEqual) {
        return this.greaterThanKey(entry.key(), orEqual);
    }

    @Nonnull
    default KeySortedMap<K, V> lessThanKey(final K key, final boolean orEqual) {
        return this.filterKeys(Predicates.lessThan(key, orEqual, this.keyComparator()));
    }

    @Override
    @Deprecated
    default SortedMap<K, V> lessThan(final Entry<K, V> entry, final boolean orEqual) {
        return this.lessThanKey(entry.key(), orEqual);
    }

}
