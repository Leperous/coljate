package net.coljate.map.lazy;

import java.util.function.Function;

import net.coljate.collection.Collection;
import net.coljate.map.Entry;
import net.coljate.map.ImmutableMap;
import net.coljate.map.Map;
import net.coljate.map.MutableMap;
import net.coljate.set.Set;
import net.coljate.set.lazy.LazySet;

import static javafx.scene.input.KeyCode.V;

/**
 *
 * @author Ollie
 */
public interface LazyMap<K, V> extends LazySet<Entry<K, V>>, Map<K, V> {

    @Override
    default MutableMap<K, V> mutableCopy() {
        return Map.super.mutableCopy();
    }

    @Override
    default ImmutableMap<K, V> immutableCopy() {
        return Map.super.immutableCopy();
    }

    @Override
    Set<? extends K> keys();

    @Override
    Collection<V> values();

    static <K, V1, V2> Function<Collection<Entry<K, V1>>, LazyMap<K, V2>> transformValues(final Function<? super V1, ? extends V2> transformation) {
        return collection -> {
            final Map<K, V1> map = Map.copyOrCast(collection);
            return new LazyTransformedValueMap<>(map, transformation);
        };
    }

}
