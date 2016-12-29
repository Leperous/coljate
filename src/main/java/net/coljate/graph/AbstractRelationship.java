package net.coljate.graph;

/**
 *
 * @author ollie
 */
public abstract class AbstractRelationship<V, E> implements Relationship<V, E> {

    @Override
    public boolean equals(final Object that) {
        return that instanceof Relationship
                && this.equals((Relationship) that);
    }

    protected abstract boolean equals(Relationship<?, ?> that);

    @Override
    public abstract int hashCode();

}
