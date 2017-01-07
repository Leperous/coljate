package net.coljate.graph;

import net.coljate.set.SetTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public interface GraphTest<V, E> extends SetTest<Relationship<V, E>> {

    @Override
    Graph<V, E> createTestCollection();

    interface ZeroRelationshipTests<V, E> extends GraphTest<V, E>, SetTest.ZeroElementTests<Relationship<V, E>> {

        @Test
        default void testEdges() {
            final Graph<V, E> graph = this.createTestCollection();
            assertTrue(graph.edges().isEmpty());
            assertThat(graph.size(), is(0));
        }

        @Test
        default void testVertices() {
            final Graph<V, E> graph = this.createTestCollection();
            assertTrue(graph.vertices().isEmpty());
            assertThat(graph.order(), is(0));
        }

    }

    interface OneRelationshipTest<V, E> extends GraphTest<V, E>, SetTest.OneElementTests<Relationship<V, E>> {

        @Test
        default void testVertices() {
            final Graph<V, E> graph = this.createTestCollection();
            assertThat(graph.vertices().count(), is(2));
            assertThat(graph.order(), is(2));
        }

        @Test
        default void testEdges() {
            final Graph<V, E> graph = this.createTestCollection();
            assertThat(graph.edges().count(), is(1));
            assertThat(graph.size(), is(1));
        }

    }

}
