package net.coljate.collection;

import net.coljate.Tests;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author ollie
 */
public interface SingletonCollectionTests<T> extends Tests<T> {

    Collection<T> createSingleton(T element);

    @Test
    default void testContains() {
        final T object = this.createObject();
        final Collection<T> collection = this.createSingleton(object);
        assertTrue(collection.contains(object));
    }

}
