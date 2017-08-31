package net.coljate.collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import net.coljate.TestObjectCreator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Ollie
 */
public interface MutableCollectionTest<T> extends CollectionTest<T>, TestObjectCreator<T> {

    @Override
    MutableCollection<T> createTestCollection();

    interface ZeroElementTests<T> extends MutableCollectionTest<T>, CollectionTest.ZeroElementTests<T> {

        @Test
        default void testClear() {
            this.createTestCollection().clear();
        }

        @Test
        default void testRemoveFirst() {
            assertFalse(this.createTestCollection().removeFirst(this.createTestObject()));
        }

    }

    interface OneElementTests<T> extends MutableCollectionTest<T>, CollectionTest.OneElementTests<T> {

        @Test
        default void testClear() {
            final MutableCollection<T> collection = this.createTestCollection();
            final T element = this.getCollectionElement();
            assertTrue(collection.contains(element));
            collection.clear();
            assertFalse(collection.contains(element));
            assertThat(collection.count(), is(0));
        }

    }

}
