package net.ollie.coljate.lists;

import net.ollie.coljate.lists.AbstactMutableWrappedArray;
import net.ollie.coljate.Array;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author Ollie
 */
public class MutableArrayTest {

    @Test
    public void testEmpty() {
        final Array.Mutable<Object> empty = AbstactMutableWrappedArray.create(0);
        assertTrue(empty.isEmpty());
        try {
            empty.set(0, new Object());
            fail("Set didn't throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException ignore) {
        }
    }

    @Test
    public void testEmptyInitialSize() {
        final Array.Mutable<Object> empty = AbstactMutableWrappedArray.create(4);
        assertThat(empty.count(), is(0));
        empty.set(0, new Object());
        empty.set(1, new Object());
        empty.set(2, new Object());
        empty.set(3, new Object());
    }

}
