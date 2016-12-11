package net.coljate.map.lazy;

import java.util.Iterator;
import java.util.function.Function;

import net.coljate.collection.lazy.LazyCollection;
import net.coljate.collection.lazy.LazyTransformedCollection;
import net.coljate.map.Entry;
import net.coljate.map.ImmutableEntry;
import net.coljate.map.Map;
import net.coljate.set.Set;
import net.coljate.util.Iterators;

/**
 *
 * @author Ollie
 */
public class LazyTransformedValueMap<K, V1, V2> implements LazyMap<K, V2> {

    private final Map<K, V1> map;
    private final Function<? super V1, ? extends V2> transformation;

    protected LazyTransformedValueMap(
            final Map<K, V1> map,
            final Function<? super V1, ? extends V2> transformation) {
        this.map = map;
        this.transformation = transformation;
    }

    @Override
    public Entry<K, V2> entry(final Object key) {
        return this.transform(map.entry(key));
    }

    private Entry<K, V2> transform(final Entry<K, V1> entry) {
        return entry == null
                ? null
                : new ImmutableEntry<>(entry.key(), transformation.apply(entry.value()));
    }

    @Override
    public Set<? extends K> keys() {
        return map.keys();
    }

    private LazyCollection<V2> values;

    @Override
    public LazyCollection<V2> values() {
        return values == null
                ? (values = new LazyTransformedCollection<>(map.values(), transformation))
                : values;
    }

    @Override
    public Iterator<Entry<K, V2>> iterator() {
        return Iterators.transform(map.iterator(), this::transform);
    }

}
