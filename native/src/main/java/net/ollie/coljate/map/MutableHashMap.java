package net.ollie.coljate.map;

import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

import net.ollie.coljate.Collection;
import net.ollie.coljate.list.MutableForwardLinkedList;
import net.ollie.coljate.list.MutableList;
import net.ollie.coljate.map.mixin.CopiedToHashMap;
import net.ollie.coljate.set.AbstractSet;
import net.ollie.coljate.set.Set;
import net.ollie.coljate.set.mixin.CopiedToHashSet;
import net.ollie.coljate.utils.Iterators;

/**
 *
 * @author Ollie
 * @see java.util.HashMap
 */
public class MutableHashMap<K, V>
        extends AbstractMap<K, V>
        implements MutableMap<K, V>, HashMap<K, V>, CopiedToHashMap<K, V> {

    public static <K, V> MutableHashMap<K, V> create() {
        return create(HashMap.DEFAULT_INITIAL_CAPACITY);
    }

    public static <K, V> MutableHashMap<K, V> create(final int initialCapacity) {
        return new MutableHashMap<>(initialCapacity);
    }

    private Bucket<K, V>[] buckets;
    private int count;

    @SuppressWarnings("unchecked")
    MutableHashMap(final int initialCapacity) {
        this.buckets = new Bucket[initialCapacity];
    }

    @Override
    public V get(final Object key) {
        if (key == null) {
            return null;
        }
        final Bucket<K, V> bucket = buckets[this.indexOf(key)];
        return bucket == null ? null : bucket.get(key);
    }

    private int indexOf(final Object key) {
        return key.hashCode() % buckets.length;
    }

    @Override
    public Map<K, V> tail() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public boolean isEmpty() {
        return this.count() == 0;
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public V put(final K key, final V value) {
        final int index = this.indexOf(key);
        Bucket<K, V> bucket = buckets[index];
        if (bucket == null) {
            bucket = this.createBucket();
            buckets[index] = bucket;
        }
        return bucket.put(key, value, () -> count++);
    }

    @Override
    public V deleteKey(final Object key) {
        final int index = this.indexOf(key);
        final Bucket<K, V> bucket = buckets[index];
        if (bucket == null) {
            return null;
        }
        final V value = bucket.delete(key, () -> count--);
        if (bucket.isEmpty()) {
            buckets[index] = null;
        }
        return value;
    }

    @Override
    public Iterator<? extends MutableMapEntry<K, V>> entries() {
        return new EntryIterator();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        buckets = new Bucket[buckets.length];
    }

    @Override
    public boolean containsKey(final Object key) {
        final Bucket<K, V> bucket = buckets[this.indexOf(key)];
        return bucket != null && bucket.find(key).isPresent();
    }

    @Override
    public Set<K> keys() {
        return new KeySet();
    }

    @Override
    public Collection<V> values() {
        return new MapValues<>(this);
    }

    protected <K, V> Bucket<K, V> createBucket() {
        return new ListBucket<>(MutableForwardLinkedList.create());
    }

    protected interface Bucket<K, V> {

        V get(Object key);

        boolean isEmpty();

        Optional<? extends MapEntry<K, V>> find(Object key);

        V put(K key, V value, Runnable ifNew);

        V delete(Object key, Runnable ifDeleted);

        Iterator<? extends MutableMapEntry<K, V>> entries();

    }

    private static final class ListBucket<K, V> implements Bucket<K, V> {

        private final MutableList<Entry<K, V>> list;

        ListBucket(final MutableList<Entry<K, V>> list) {
            this.list = list;
        }

        @Override
        public boolean isEmpty() {
            return list.isEmpty();
        }

        @Override
        public Optional<? extends MutableMapEntry<K, V>> find(final Object key) {
            return list.first(element -> Objects.equals(element.key, key));
        }

        @Override
        public V get(final Object key) {
            return this.find(key).map(MapEntry::value).orElse(null);
        }

        @Override
        public V put(final K key, final V value, final Runnable ifPut) {
            final MutableMapEntry<K, V> entry = this.find(key).orElse(null);
            if (entry == null) {
                list.suffix(new Entry<>(key, value));
                ifPut.run();
                return null;
            } else {
                return entry.setValue(value);
            }
        }

        @Override
        public V delete(final Object key, final Runnable ifDeleted) {
            for (final Iterator<Entry<K, V>> iterator = list.iterator(); iterator.hasNext();) {
                final Entry<K, V> next = iterator.next();
                if (Objects.equals(key, next.key)) {
                    iterator.remove();
                    ifDeleted.run();
                    return next.value;
                }
            }
            return null;
        }

        @Override
        public Iterator<? extends MutableMapEntry<K, V>> entries() {
            return list.iterator();
        }

    }

    private static final class Entry<K, V> extends AbstractMapEntry<K, V> implements MutableMapEntry<K, V> {

        private final K key;
        private V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K key() {
            return key;
        }

        @Override
        public V value() {
            return value;
        }

        @Override
        public V setValue(final V value) {
            final V oldValue = value;
            this.value = value;
            return oldValue;
        }

    }

    private final class EntryIterator implements Iterator<MutableMapEntry<K, V>> {

        private int index;
        private Iterator<? extends MutableMapEntry<K, V>> entries = Iterators.none();

        @Override
        public boolean hasNext() {
            while (!entries.hasNext() && index < buckets.length) {
                final Bucket<K, V> bucket = buckets[index++];
                entries = bucket == null ? Iterators.none() : bucket.entries();
            }
            return entries.hasNext();
        }

        @Override
        public MutableMapEntry<K, V> next() {
            return entries.next();
        }

    }

    private final class KeySet extends AbstractSet<K> implements CopiedToHashSet<K> {

        @Override
        public Iterator<K> iterator() {
            return Iterators.transform(MutableHashMap.this.iterator(), MapEntry::key);
        }

        @Override
        public boolean contains(final Object key) {
            return MutableHashMap.this.containsKey(key);
        }

        @Override
        public boolean isEmpty() {
            return MutableHashMap.this.isEmpty();
        }

        @Override
        public Set<K> tail() {
            throw new UnsupportedOperationException(); //TODO
        }

    }

}
