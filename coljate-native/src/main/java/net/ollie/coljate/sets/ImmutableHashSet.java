package net.ollie.coljate.sets;

import net.ollie.coljate.maps.ImmutableHashMap;
import net.ollie.coljate.maps.Map;
import net.ollie.coljate.utils.iterators.UnmodifiableIterator;

import java.io.Serializable;

/**
 *
 * @author Ollie
 */
public class ImmutableHashSet<V>
        extends Set.Abstract<V>
        implements Set.Immutable<V>, Serializable {

    private static final long serialVersionUID = 1L;
    private static final Object VALUE = new Object();

    @SafeVarargs
    public static <V> Set.Immutable<V> create(final V... values) {
        //TODO make this more efficient
        ImmutableHashMap<V, Object> map = ImmutableHashMap.create();
        for (final V value : values) {
            map = map.with(value, VALUE);
        }
        return view(map);
    }

    public static <V> Set.Immutable<V> view(final Map.Immutable<V, ?> map) {
        return new ImmutableHashSet<>(ImmutableHashMap.copy(map));
    }

    private final ImmutableHashMap<V, Object> map;

    protected ImmutableHashSet(final ImmutableHashMap<V, Object> map) {
        this.map = map;
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(final Object object) {
        return map.containsKey(object);
    }

    @Override
    public UnmodifiableIterator<V> iterator() {
        return map.keys().iterator();
    }

    @Override
    public Stream<V, ? extends Set<V>> stream() {
        return map.keys().stream();
    }

    @Override
    public Set.Mutable<V> mutableCopy() {
        return map.keys().mutableCopy();
    }

    @Override
    public Set.Immutable<V> and(final V value) {
        return this.contains(value) ? this : new ImmutableHashSet<>(map.with(value, VALUE));
    }

    @Override
    public Set.Immutable<V> not(Object value) {
        return this.contains(value) ? new ImmutableHashSet<>(map.without(value)) : this;
    }

}
