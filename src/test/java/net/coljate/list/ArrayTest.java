package net.coljate.list;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ollie
 */
public interface ArrayTest<T> extends ListTest<T> {

    @Override
    Array<T> createTestCollection();

    interface ZeroElementTests<T> extends ArrayTest<T>, ListTest.ZeroElementTests<T> {

    }

    interface OneElementTests<T> extends ArrayTest<T>, ListTest.OneElementTests<T> {

        @Test
        default void testGet() {
            assertThat(this.createTestCollection().get(0), is(this.getTestObject()));
        }

        @Test
        default void testGet_IndexOutOfBounds() {
            assertThrows(IndexOutOfBoundsException.class, () -> this.createTestCollection().get(-1));
            assertThrows(IndexOutOfBoundsException.class, () -> this.createTestCollection().get(1));
        }

    }

}
