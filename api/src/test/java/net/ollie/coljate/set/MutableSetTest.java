package net.ollie.coljate.set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Ollie
 */
public abstract class MutableSetTest<T> extends SetTest<T> {

    @Override
    protected abstract MutableSet<T> create();

    @Test
    public void testAdd_NotTwice() {
        final T object = this.randomValue();
        final MutableSet<T> set = this.create();
        assertFalse(set.contains(object));
        assertTrue(set.add(object));
        assertTrue(set.contains(object));
        assertFalse(set.add(object));
        assertTrue(set.contains(object));
    }

}
