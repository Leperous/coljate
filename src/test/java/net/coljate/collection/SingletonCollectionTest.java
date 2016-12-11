package net.coljate.collection;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Assume;
import org.junit.Test;

/**
 * Tests for collections containing one element.
 *
 * @author ollie
 */
public interface SingletonCollectionTest<T> extends CollectionTest<T> {

    @Override
    default Collection<T> create(final java.util.List<T> elements) {
        Assume.assumeTrue(elements.size() == 1);
        return this.create(elements.get(0));
    }

    Collection<T> create(T element);

    @Test
    default void testCount_Singleton() {
        final Collection<T> collection = this.create(this.createObject());
        assertThat(collection.count(), is(1));
        assertFalse(collection.isEmpty());
    }

    @Test
    default void testContains_Singleton() {
        final T object = this.createObject();
        final Collection<T> collection = this.create(object);
        assertTrue("Singleton should contain " + object, collection.contains(object));
    }

    @Test
    default void testIterator_Singleton() {
        final T object = this.createObject();
        final Collection<T> collection = this.create(object);
        final Iterator<T> iterator = collection.iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(object));
        assertFalse(iterator.hasNext());
    }

}
