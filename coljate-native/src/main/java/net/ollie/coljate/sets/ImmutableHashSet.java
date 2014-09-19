package net.ollie.coljate.sets;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import net.ollie.coljate.maps.ImmutableHashMap;
import net.ollie.coljate.maps.Map;
import net.ollie.coljate.maps.MutableWrappedHashMap;
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
    private static final ImmutableHashSet EMPTY = new ImmutableHashSet(ImmutableHashMap.create());
    private static final Object VALUE = new Object();

    public static <V> Set.Immutable<V> create() {
        return EMPTY;
    }

    @SafeVarargs
    public static <V> ImmutableHashSet<V> create(final V... values) {
        //TODO make this more efficient
        ImmutableHashMap<V, Object> map = ImmutableHashMap.create();
        for (final V value : values) {
            map = map.with(value, VALUE);
        }
        return view(map);
    }

    public static <V> ImmutableHashSet<V> copy(final Iterable<? extends V> iterable) {
        final Map.Mutable<V, Object> map = MutableWrappedHashMap.create();
        for (final V element : iterable) {
            map.put(element, VALUE);
        }
        return view(ImmutableHashMap.copy(map));
    }

    public static <V> ImmutableHashSet<V> view(final Map.Immutable<V, ?> map) {
        return new ImmutableHashSet<>(ImmutableHashMap.copy(map));
    }

    public static <V> Collector<V, ?, ImmutableHashSet<V>> collector() {
        return new ImmutableHashSetCollector<>();
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

    private static final class ImmutableHashSetCollector<V>
            extends AbstractSetCollector<V, Set.Mutable<V>, ImmutableHashSet<V>> {

        @Override
        public Supplier<Set.Mutable<V>> supplier() {
            return MutableWrappedHashSet::create;
        }

        @Override
        public Function<Set.Mutable<V>, ImmutableHashSet<V>> finisher() {
            return set -> copy(set);
        }

    }

}
