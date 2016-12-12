package net.coljate.collection;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ollie
 */
public interface MutableCollectionTest<T> extends CollectionTest<T> {

    @Override
    MutableCollection<T> create(java.util.List<T> elements);

    @Test
    default void testRemoveFirst_Singleton() {
        final T element = this.createObject();
        final MutableCollection<T> collection = this.create(java.util.Collections.singletonList(element));
        assertTrue(collection.removeFirst(element), "Should remove element");
        assertTrue(collection.isEmpty());
        assertFalse(collection.contains(element));
    }

    @Test
    default void testClear() {

        //Given
        final T element = this.createObject();
        final MutableCollection<T> collection = this.create(java.util.Collections.singletonList(element));
        assertTrue(collection.contains(element), () -> "Collection should contain " + element);

        //When
        collection.clear();

        //Then
        assertTrue(collection.isEmpty());
        assertFalse(collection.contains(element));

    }

}
