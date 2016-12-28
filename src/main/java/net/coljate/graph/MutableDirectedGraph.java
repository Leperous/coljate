package net.coljate.graph;

/**
 *
 * @author ollie
 */
public interface MutableDirectedGraph<V, E> extends DirectedGraph<V, E>, MutableGraph<V, E> {

    @Override
    default boolean add(final Relationship<V, E> relationship) {
        return relationship instanceof DirectedRelationship
                && this.add((DirectedRelationship<V, E>) relationship);
    }

    boolean add(DirectedRelationship<V, E> relationship);

    @Override
    default boolean remove(final Relationship<?, ?> relationship) {
        return relationship instanceof DirectedRelationship
                && this.remove((DirectedRelationship) relationship);
    }

    boolean remove(DirectedRelationship<?, ?> relationship);

    @Override
    MutableDirectedGraph<V, E> mutableCopy();

}
