package net.ollie.coljate.maps;

import java.util.Comparator;
import java.util.function.BiFunction;

import net.ollie.coljate.AbstractWrappedStreamable;
import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.imposed.sorting.Sorter;
import net.ollie.coljate.sets.ImmutableWrappedHashSet;
import net.ollie.coljate.sets.ImmutableWrappedTreeSet;
import net.ollie.coljate.sets.Set;
import net.ollie.coljate.utils.iterators.UnmodifiableIterator;

/**
 *
 * @author Ollie
 * @param <K>
 * @param <V>
 */
public class ImmutableWrappedTreeMap<K, V>
        extends AbstractWrappedStreamable<V>
        implements TreeMap.Immutable<K, V> {

    public static <K, V> TreeMap.Immutable<K, V> copy(final java.util.SortedMap<K, ? extends V> map) {
        return new ImmutableWrappedTreeMap<>(MutableWrappedTreeMap.copy(map));
    }

    public static <K, V> TreeMap.Immutable<K, V> copy(final TreeMap<K, V> map) {
        return new ImmutableWrappedTreeMap<>(map.mutableCopy());
    }

    public static <K extends Comparable<? super K>, V> TreeMap.Immutable<K, V> create() {
        return new ImmutableWrappedTreeMap<>(MutableWrappedTreeMap.<K, V>create());
    }

    public static <K, V> TreeMap.Immutable<K, V> create(final Comparator<? super K> keyComparator) {
        return new ImmutableWrappedTreeMap<>(MutableWrappedTreeMap.create(keyComparator));
    }

    private final TreeMap.Mutable<K, V> underlying;

    protected ImmutableWrappedTreeMap(final TreeMap.Mutable<K, V> underlying) {
        this.underlying = underlying;
    }

    @Override
    protected TreeMap<K, V> underlying() {
        return underlying;
    }

    @Override
    public Sorter<? super K> sorter() {
        return this.underlying().sorter();
    }

    public Sorter<? super Map.Entry<K, V>> entrySorter() {
        final Sorter<? super K> sorter = this.sorter();
        return Sorter.create((l, r) -> sorter.compare(l.key(), r.key()));
    }

    @Override
    public TreeMap.Mutable<K, V> mutableCopy() {
        return this.underlying().mutableCopy();
    }

    @Override
    public K first() {
        return this.underlying().first();
    }

    @Override
    public K root() {
        return this.underlying().root();
    }

    @Override
    public K parent(final K node) {
        return this.underlying().parent(node);
    }

    @Override
    public Set.Immutable<K> neighbours(final K node) {
        return this.underlying().neighbours(node).immutableCopy();
    }

    @Override
    public V maybeGet(final Object key) {
        return this.underlying().maybeGet(key);
    }

    @Override
    public Set.Immutable<K> children(final K node) {
        return this.underlying().children(node).immutableCopy();
    }

    @Override
    public UnmodifiableIterator<V> iterator() {
        return UnmodifiableIterator.of(this.underlying().iterator());
    }

    @Override
    public <V2> Map.Immutable<K, V2> transformEntries(final BiFunction<? super K, ? super V, ? extends V2> function) {
        final Map.Mutable<K, V2> mutable = ImmutableWrappedTreeMap.<K, V2>create(this.sorter()).mutableCopy();
        this.entries().forEach(entry -> mutable.put(entry.key(), function.apply(entry.key(), entry.value())));
        return mutable.immutableCopy();
    }

    @Override
    public Set.Immutable<K> keys() {
        return this.underlying().keys().immutableCopy();
    }

    @Override
    public Streamable.Immutable<V> values() {
        return this.underlying().values().immutableCopy();
    }

    @Override
    public TreeMap.Immutable<K, V> with(final K key, final V value) {
        final TreeMap.Mutable<K, V> tree = this.mutableCopy();
        tree.put(key, value);
        return copy(tree);
    }

    @Override
    public TreeMap.Immutable<K, V> without(final Object key) {
        if (this.contains(key)) {
            final TreeMap.Mutable<K, V> tree = this.mutableCopy();
            tree.remove(key);
            return copy(tree);
        } else {
            return this;
        }
    }

    @Override
    public Set.Immutable<? extends TreeMap.Immutable<K, V>> subtrees(final K key) {
        return ImmutableWrappedHashSet.copy(this.underlying().subtrees(key).stream().map(tree -> tree.immutableCopy()));
    }

    @Override
    public Set.Immutable<? extends Map.Immutable.Entry<K, V>> entries() {
        final Stream<Map.Immutable.Entry<K, V>, ?> streamed = this.underlying().entries().stream().map(entry -> entry.immutableCopy());
        return ImmutableWrappedTreeSet.copy(streamed, this.entrySorter());
    }

    @Override
    public TreeMap.Immutable<K, V> tail() {
        throw new UnsupportedOperationException("tail not supported yet!");
    }

    @Override
    public MultiMap.Immutable<V, K> inverseCopy() {
        throw new UnsupportedOperationException("inverse not supported yet!");
    }

}
