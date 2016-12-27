package net.coljate.graph;

import java.util.Objects;
import java.util.Optional;

import net.coljate.set.impl.TwoSet;
import net.coljate.util.iterator.CovariantIterator;

/**
 *
 * @author ollie
 */
public interface DirectedGraph<V, E> extends Graph<V, E> {

    @Override
    CovariantIterator<Relationship<V, E>, ? extends DirectedRelationship<V, E>> iterator();

    interface DirectedRelationship<V, E> extends Relationship<V, E> {

        V from();

        V to();

        @Override
        default boolean containsVertex(final Object vertex) {
            return this.isFrom(vertex) || this.isTo(vertex);
        }

        @Override
        default boolean isBetween(final Object fromVertex, final Object toVertex) {
            return this.isFrom(fromVertex) && this.isTo(toVertex);
        }

        default boolean isFrom(final Object vertex) {
            return Objects.equals(vertex, this.from());
        }

        default boolean isTo(final Object vertex) {
            return Objects.equals(vertex, this.to());
        }

        @Override
        default TwoSet<V> vertices() {
            return TwoSet.require(this.from(), this.to());
        }

        @Override
        default Optional<V> otherVertex(final Object vertex) {
            if (Objects.equals(vertex, this.from())) {
                return Optional.of(this.to());
            } else if (Objects.equals(vertex, this.to())) {
                return Optional.of(this.from());
            } else {
                return Optional.empty();
            }
        }

    }

}
