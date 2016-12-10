package net.coljate.collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author ollie
 */
public abstract class MutableCollectionTest extends CollectionTest {

    protected abstract <T> MutableCollection<T> create(T... elements);

    @Test
    public void testRemoveFirst_Singleton() {
        final Object element = this.createObject();
        final MutableCollection<Object> collection = this.create(element);
        assertTrue("Should remove element", collection.removeFirst(element));
        assertTrue(collection.isEmpty());
        assertFalse(collection.contains(element));
    }

    @Test
    public void testClear() {

        //Given
        final Object element = this.createObject();
        final MutableCollection<Object> collection = this.create(element);
        assertTrue(collection.contains(element));

        //When
        collection.clear();

        //Then
        this.assertEmpty(collection);
        assertFalse(collection.contains(element));

    }

}
