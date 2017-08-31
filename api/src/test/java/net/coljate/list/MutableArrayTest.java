package net.coljate.list;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Ollie
 */
public interface MutableArrayTest<T> extends ArrayTest<T>, MutableListTest<T> {

    @Override
    MutableArray<T> createTestCollection();

    interface ZeroElementTests<T> extends MutableArrayTest<T>, ArrayTest.ZeroElementTests<T>, MutableListTest.ZeroElementTests<T> {

        @Test
        default void testResize() {
            final MutableArray<T> array = this.createTestCollection();
            assertThat(array.length(), is(0));
            array.resize(1);
            assertThat(array.length(), is(1));
            array.resize(1);
            assertThat(array.length(), is(1));
        }

    }

    interface OneElementTests<T> extends MutableArrayTest<T>, ArrayTest.OneElementTests<T>, MutableListTest.OneElementTests<T> {

    }

}
