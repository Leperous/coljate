package net.coljate.collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

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
        assertTrue("Should remove element", collection.removeFirst(element));
        assertTrue(collection.isEmpty());
        assertFalse(collection.contains(element));
    }

    @Test
    default void testClear() {

        //Given
        final T element = this.createObject();
        final MutableCollection<T> collection = this.create(java.util.Collections.singletonList(element));
        assertTrue("Collection should contain " + element, collection.contains(element));

        //When
        collection.clear();

        //Then
        assertTrue(collection.isEmpty());
        assertFalse(collection.contains(element));

    }

}
