package net.coljate.graph.impl;

import java.util.Iterator;
import java.util.Objects;

import net.coljate.graph.AbstractGraph;
import net.coljate.graph.Graph;
import net.coljate.graph.ImmutableGraph;
import net.coljate.graph.MutableGraph;
import net.coljate.graph.Relationship;
import net.coljate.graph.ValueGraph;
import net.coljate.map.Entry;
import net.coljate.map.Map;

/**
 *
 * @author Ollie
 */
public class MappedValueGraph<K, V, E>
        extends AbstractGraph<K, E>
        implements ValueGraph<K, V, E> {

    private final Graph<K, E> graph;
    private final Map<K, V> vertexValues;

    public MappedValueGraph(final Graph<K, E> delegate, final Map<K, V> vertexValues) {
        this.graph = Objects.requireNonNull(delegate);
        this.vertexValues = Objects.requireNonNull(vertexValues);
    }

    @Override
    public Iterator<Relationship<K, E>> iterator() {
        return graph.iterator();
    }

    @Override
    public V get(final K key) {
        return vertexValues.get(key);
    }

    @Override
    public V getIfPresent(final Object vertexKey) {
        return vertexValues.getIfPresent(vertexKey);
    }

    @Override
    public Entry<K, V> getVertex(final Object vertexKey) {
        return vertexValues.getEntry(vertexKey);
    }

    @Override
    public MutableGraph<K, E> mutableCopy() {
        return graph.mutableCopy();
    }

    @Override
    public ImmutableGraph<K, E> immutableCopy() {
        return graph.immutableCopy();
    }

}
