package net.coljate.collection;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ollie
 */
public interface ImmutableCollectionTest<T> extends CollectionTest<T> {

    @Override
    ImmutableCollection<T> create(java.util.List<T> elements);

    @Override
    default ImmutableCollection<T> create(final T element) {
        return this.create(singletonList(element));
    }

    @Override
    default ImmutableCollection<T> create() {
        return this.create(emptyList());
    }

    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    default void testIteratorRemove_Empty_Unsupported() {
        final ImmutableCollection<T> collection = this.create();
        final Iterator<T> iterator = collection.iterator();
        assertThrows(UnsupportedOperationException.class, iterator::remove);
    }

    @Test
    default void testIteratorRemove_Singleton_Unsupported() {
        final ImmutableCollection<T> collection = this.create(this.createObject());
        final Iterator<T> iterator = collection.iterator();
        iterator.next();
        assertThrows(UnsupportedOperationException.class, iterator::remove);
    }

}
