package net.coljate.map.lazy;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import net.coljate.collection.AbstractCollection;
import net.coljate.collection.lazy.LazyCollection;
import net.coljate.map.Entry;
import net.coljate.map.Map;
import net.coljate.set.AbstractSet;
import net.coljate.set.Set;
import net.coljate.set.lazy.LazySet;
import net.coljate.util.Iterators;

/**
 *
 * @author Ollie
 */
public class LazyFilteredMap<K, V> implements LazyMap<K, V> {

    public static <K, V> LazyFilteredMap<K, V> filterKeys(final Map<K, V> map, final Predicate<? super K> predicate) {
        return filterEntries(map, (key, value) -> predicate.test(key));
    }

    public static <K, V> LazyFilteredMap<K, V> filterValues(final Map<K, V> map, final Predicate<? super V> predicate) {
        return filterEntries(map, (key, value) -> predicate.test(value));
    }

    public static <K, V> LazyFilteredMap<K, V> filterEntries(final Map<K, V> map, final Predicate<? super Entry<K, V>> predicate) {
        return new LazyFilteredMap<>(map, predicate);
    }

    public static <K, V> LazyFilteredMap<K, V> filterEntries(final Map<K, V> map, final BiPredicate<? super K, ? super V> predicate) {
        return new LazyFilteredMap<>(map, entry -> predicate.test(entry.key(), entry.value()));
    }

    private final Map<K, V> map;
    private final Predicate<? super Entry<K, V>> predicate;

    public LazyFilteredMap(
            final Map<K, V> map,
            final Predicate<? super Entry<K, V>> predicate) {
        this.map = Objects.requireNonNull(map, "map");
        this.predicate = Objects.requireNonNull(predicate, "predicate");
    }

    @Override
    public Entry<K, V> entry(final Object key) {
        final Entry<K, V> entry = map.entry(key);
        return this.test(entry) ? entry : null;
    }

    private boolean test(final Entry<K, V> entry) {
        return entry != null
                && predicate.test(entry);
    }

    private KeySet keys;

    @Override
    public LazySet<K> keys() {
        return keys == null
                ? (keys = new KeySet())
                : keys;
    }

    private ValueCollection values;

    @Override
    public LazyCollection<V> values() {
        return values == null
                ? (values = new ValueCollection())
                : values;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return Iterators.filter(map.iterator(), this::test);
    }

    private final class KeySet
            extends AbstractSet<K>
            implements LazySet<K> {

        @Override
        public boolean contains(final Object object) {
            return LazyFilteredMap.this.test(LazyFilteredMap.this.entry(object));
        }

        @Override
        public Iterator<K> iterator() {
            return Iterators.transform(LazyFilteredMap.this.iterator(), Entry::key);
        }

        @Override
        protected boolean equals(final Set<?> that) {
            return this == that;
        }

    }

    private final class ValueCollection
            extends AbstractCollection<V>
            implements LazyCollection<V> {

        @Override
        public Iterator<V> iterator() {
            return Iterators.transform(LazyFilteredMap.this.iterator(), Entry::value);
        }

        @Override
        protected boolean equals(AbstractCollection<?> that) {
            throw new UnsupportedOperationException(); //TODO
        }

        @Override
        public int hashCode() {
            throw new UnsupportedOperationException(); //TODO
        }

    }

}
