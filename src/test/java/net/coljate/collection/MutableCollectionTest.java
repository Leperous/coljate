package net.coljate.collection;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ollie
 */
public interface MutableCollectionTest<T> extends CollectionTest<T> {

    @Override
    MutableCollection<T> create(java.util.List<T> elements);

    @Override
    default MutableCollection<T> create(T element) {
        return this.create(singletonList(element));
    }

    @Test
    default void testRemoveFirst_Singleton() {
        final T element = this.createObject();
        final MutableCollection<T> collection = this.create(element);
        assertTrue(collection.removeFirst(element), "Should remove element");
        assertTrue(collection.isEmpty());
        assertFalse(collection.contains(element));
    }
    
    @Test
    default void testRemoveAll_Singleton() {
        final T o1 = this.createObject();
        final MutableCollection<T> collection = this.create(Arrays.asList(o1, o1));
        collection.removeAll(o1);
        assertFalse(collection.contains(o1));
        assertTrue(collection.isEmpty());
    }
    
    @Test
    default void testClear_Singleton() {

        //Given
        final T element = this.createObject();
        final MutableCollection<T> collection = this.create(element);
        assertTrue(collection.contains(element), () -> "Collection should contain " + element);

        //When
        collection.clear();

        //Then
        assertTrue(collection.isEmpty());
        assertFalse(collection.contains(element));

    }

}
