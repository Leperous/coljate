package net.coljate.graph;

import net.coljate.map.Entry;
import net.coljate.util.Functions;

/**
 * A graph of vertices {@code Entry<K,V>} with a key/value association.
 *
 * @author ollie
 * @param <K> vertex key type
 * @param <V> vertex value type
 * @param <E> edge type
 */
public interface ValueGraph<K, V, E> extends Graph<K, E> {

    Entry<K, V> getVertex(Object vertexKey);

    default V getVertexValue(final Object vertexKey) {
        return Functions.ifNonNull(this.getVertex(vertexKey), Entry::value);
    }

}
