package net.coljate.collection;

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
    default Collection<T> create(final T... elements) {
        Assume.assumeTrue(elements.length == 1);
        return this.createSingleton(elements[0]);
    }

    Collection<T> createSingleton(T element);

    @Test
    default void testCount_Singleton() {
        final Collection<T> collection = this.createSingleton(this.createObject());
        assertThat(collection.count(), is(1));
        assertFalse(collection.isEmpty());
    }

    @Test
    default void testContains_Singleton() {
        final T object = this.createObject();
        final Collection<T> collection = this.createSingleton(object);
        assertTrue(collection.contains(object));
    }

}
