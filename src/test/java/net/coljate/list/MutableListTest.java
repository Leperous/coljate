package net.coljate.list;

import net.coljate.collection.MutableCollectionTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Ollie
 */
public interface MutableListTest<T>
        extends ListTest<T>, MutableCollectionTest<T> {

    @Override
    MutableList<T> create(java.util.List<T> elements);

    @Override
    default MutableList<T> create() {
        return this.create(emptyList());
    }

    @Override
    default MutableList<T> create(T element) {
        return this.create(singletonList(element));
    }

    @Test
    default void testSuffix_Empty() {
        final MutableList<T> list = this.create();
        final T element = this.createObject();
        list.suffix(element);
        assertTrue(list.contains(element));
        assertThat(list.first(), is(element));
        assertThat(list.last(), is(element));
    }

    @Test
    default void testSuffix_Singleton() {
        final T o1 = this.createObject(), o2 = this.createObject();
        final MutableList<T> list = this.create(o1);
        list.suffix(o2);
        assertTrue(list.contains(o2));
        assertThat(list.first(), is(o1));
        assertThat(list.last(), is(o2));
    }

}
