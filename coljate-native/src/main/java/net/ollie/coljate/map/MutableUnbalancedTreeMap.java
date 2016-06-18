package net.ollie.coljate.map;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;

import javax.annotation.CheckForNull;

import org.checkerframework.checker.nullness.qual.NonNull;

import net.ollie.coljate.utils.Iterators;

/**
 *
 * @author Ollie
 * @see java.util.TreeMap
 */
public class MutableUnbalancedTreeMap<K, V>
        extends AbstractSortedMap<K, V>
        implements MutableMap<K, V> {

    public static <K extends Comparable<? super K>, V> MutableUnbalancedTreeMap<K, V> create() {
        return create(Comparator.naturalOrder());
    }

    public static <K, V> MutableUnbalancedTreeMap<K, V> create(@NonNull final Comparator<? super K> comparator) {
        return new MutableUnbalancedTreeMap<>(comparator);
    }

    public static <K, V> MutableUnbalancedTreeMap<K, V> copyOf(@NonNull final Map<K, V> map, final Comparator<? super K> comparator) {
        final MutableUnbalancedTreeMap<K, V> treeMap = create(comparator);
        map.forEach(treeMap::put);
        return treeMap;
    }

    public static <K extends Comparable<? super K>, V> MutableUnbalancedTreeMap<K, V> copyOf(@NonNull final Map<K, V> map) {
        return copyOf(map, Comparator.naturalOrder());
    }

    public static <K, V> MutableUnbalancedTreeMap<K, V> copyOf(@NonNull final SortedMap<K, V> map) {
        return copyOf(map, map.comparator());
    }

    private final Comparator<? super K> comparator;
    @CheckForNull
    private Node<K, V> root;

    MutableUnbalancedTreeMap(@NonNull final Comparator<? super K> comparator) {
        this.comparator = Objects.requireNonNull(comparator);
    }

    @Override
    public Comparator<? super K> comparator() {
        return comparator;
    }

    @Override
    @SuppressWarnings("unchecked")
    @Deprecated
    public V get(final Object key) {
        return this.get((K) key, SearchType.DEFAULT);
    }

    public V get(final K key, final SearchType search) {
        return search.get(key, this);
    }

    @Override
    public V put(final K key, final V value) {
        return this.put(key, value, SearchType.DEFAULT);
    }

    public V put(final K key, final V value, final SearchType search) {
        return search.put(key, value, this);
    }

    @Override
    @Deprecated
    @SuppressWarnings("unchecked")
    public V delete(final Object key) {
        return this.delete((K) key, SearchType.DEFAULT);
    }

    public V delete(final K key, final SearchType search) {
        return search.delete(key, this);
    }

    @Override
    public K maxKey() {
        final Node<K, V> max = this.maxNode();
        return max == null ? null : max.key;
    }

    @CheckForNull
    private Node<K, V> maxNode() {
        Node<K, V> node = root, parent = null;
        while (node != null) {
            parent = node;
            node = node.greater;
        }
        return parent;
    }

    @Override
    public K minKey() {
        final Node<K, V> min = this.minNode();
        return min == null ? null : min.key;
    }

    @CheckForNull
    private Node<K, V> minNode() {
        return new EntryIterator().next;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean containsKey(final Object key) {
        return this.containsKey((K) key, SearchType.DEFAULT);
    }

    public boolean containsKey(final K key, final SearchType search) {
        return search.get(key, root, comparator) != null;
    }

    @Override
    public MutableUnbalancedTreeMap<K, V> mutableCopy() {
        return copyOf(this);
    }

    @Override
    public ImmutableMap<K, V> immutableCopy() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public SortedMap<K, V> tail() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public Iterator<MutableMapEntry<K, V>> entries() {
        return this.isEmpty()
                ? Iterators.none()
                : new EntryIterator();
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public int count() {
        return SearchType.DEPTH_FIRST_RECURSIVE.count(root);
    }

    private static final class Node<K, V> implements MutableMapEntry<K, V> {

        private final K key;
        private V value;
        @CheckForNull
        private Node<K, V> lower, greater;

        Node(final K key, final V value) {
            this.key = Objects.requireNonNull(key);
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
        public V setValue(final V newValue) {
            final V previousValue = value;
            value = newValue;
            return previousValue;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }

    }

    private final class EntryIterator implements Iterator<MutableMapEntry<K, V>> {

        Node<K, V> parent, next;

        {
            this.descend(root);
        }

        private void descend(final Node<K, V> from) {
            Node<K, V> node = from;
            while (node != null) {
                parent = next;
                next = node;
                node = node.lower;
            }
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public MutableMapEntry<K, V> next() {
            final Node<K, V> next = this.next;
            if (parent == null) {
                this.next = null;
            } else {
                this.descend(parent.greater);
                this.next = this.next == null ? parent : this.next;
            }
            return next;
        }

    }

    public enum SearchType {

        DEPTH_FIRST_RECURSIVE {

            @CheckForNull
            @SuppressWarnings("null")
            @Override
            <K, V> Node<K, V> get(final K key, final Node<K, V> node, final Comparator<? super K> comparator) {
                final int c = node == null ? 0 : comparator.compare(node.key, key);
                if (c == 0) {
                    return node;
                } else if (c > 0) {
                    return this.get(key, node.greater, comparator);
                } else {
                    return this.get(key, node.lower, comparator);
                }
            }

            @Override
            <K, V> V put(final K key, final V value, final Node<K, V> node, final Comparator<? super K> comparator) {
                final int c = comparator.compare(node.key, key);
                @NonNull
                final Node<K, V> next;
                if (c == 0) {
                    return node.setValue(value);
                } else if (c > 0) {
                    if (node.lower == null) {
                        node.lower = new Node<>(key, value);
                        return null;
                    }
                    next = node.lower;
                } else {
                    if (node.greater == null) {
                        node.greater = new Node<>(key, value);
                        return null;
                    }
                    next = node.greater;
                }
                return this.put(key, value, next, comparator);
            }

            @Override
            <K, V> V delete(final K key, final Node<K, V> parent, final Node<K, V> child, final boolean parentGreater, final Comparator<? super K> comparator) {
                final int c = comparator.compare(child.key, key);
                final Node<K, V> lower = child.lower;
                final Node<K, V> greater = child.greater;
                if (c == 0) {
                    final V value = child.value;
                    //Delete the child.
                    if (parentGreater) {
                        parent.lower = null;
                    } else {
                        parent.greater = null;
                    }
                    //Assign its own children.
                    if (lower != null) {
                        this.put(lower.key, lower.value, parent, comparator);
                    }
                    if (greater != null) {
                        this.put(greater.key, greater.value, parent, comparator);
                    }
                    return value;
                } else if (c > 0) {
                    return this.delete(key, child, greater, true, comparator);
                } else {
                    return this.delete(key, child, lower, false, comparator);
                }
            }

            @Override
            int count(final Node<?, ?> from) {
                return from == null
                        ? 0
                        : 1 + this.count(from.lower) + this.count(from.greater);
            }

        },;

        public static final SearchType DEFAULT = DEPTH_FIRST_RECURSIVE;

        final <K, V> V get(K key, MutableUnbalancedTreeMap<K, V> map) {
            final Node<K, V> node = this.get(key, map.root, map.comparator);
            return node == null ? null : node.value;
        }

        @CheckForNull
        abstract <K, V> Node<K, V> get(K key, Node<K, V> node, Comparator<? super K> comparator);

        final <K, V> V put(final K key, final V value, final MutableUnbalancedTreeMap<K, V> map) {
            if (map.root == null) {
                map.root = new Node<>(key, value);
                return null;
            }
            return this.put(key, value, map.root, map.comparator);
        }

        abstract <K, V> V put(K key, V value, Node<K, V> root, Comparator<? super K> comparator);

        final <K, V> V delete(K key, @NonNull MutableUnbalancedTreeMap<K, V> map) {
            final Node<K, V> root = map.root;
            if (root == null) {
                return null;
            }
            final int c = map.comparator.compare(root.key, key);
            if (c == 0) {
                final V value = root.value;
                map.root = null;
                return value;
            } else if (c > 0) {
                return this.delete(key, root, root.greater, false, map.comparator);
            } else {
                return this.delete(key, root, root.lower, true, map.comparator);
            }
        }

        abstract <K, V> V delete(K key, @NonNull Node<K, V> parent, @NonNull Node<K, V> child, boolean parentGreater, Comparator<? super K> comparator);

        abstract int count(Node<?, ?> from);

    }

}
