package net.coljate.list;

import java.util.Optional;

import net.coljate.collection.MutableCollectionTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public interface QueueTest<T> extends MutableCollectionTest<T> {

    @Override
    Queue<T> createTestCollection();

    interface ZeroElementTests<T> extends QueueTest<T>, MutableCollectionTest.ZeroElementTests<T> {

        @Test
        default void testPoll() {
            assertThat(this.createTestCollection().poll(), is(Optional.empty()));
        }

        @Test
        default void testPeek() {
            assertThat(this.createTestCollection().peek(), is(Optional.empty()));
        }

    }

}
