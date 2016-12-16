package net.coljate.list;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Ollie
 */
public interface ArrayTest<T> extends ListTest<T> {

    @Override
    Array<T> create(java.util.List<T> elements);

    @Override
    default Array<T> create(final T element) {
        return this.create(singletonList(element));
    }

    default Array<T> create() {
        return this.create(emptyList());
    }

    @Test
    default void testIndex_Empty() {
        final Array<T> array = this.create();
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(1));
    }

    @Test
    default void testIndex_Singleton() {
        final T element = this.createObject();
        final Array<T> array = this.create(element);
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(1));
        assertThat(array.get(0), is(element));
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(-1));
    }

}
