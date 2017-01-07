package net.coljate.graph;

/**
 *
 * @author ollie
 */
public abstract class AbstractUndirectedRelationship<V, E>
        extends AbstractRelationship<V, E>
        implements UndirectedRelationship<V, E> {

    @Override
    protected boolean equals(Relationship<?, ?> that) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
