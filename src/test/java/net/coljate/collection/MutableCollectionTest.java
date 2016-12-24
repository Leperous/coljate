package net.coljate.collection;

import net.coljate.TestObjectCreator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ollie
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

}
