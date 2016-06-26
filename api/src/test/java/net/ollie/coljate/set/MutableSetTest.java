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
    public abstract MutableSet<T> create();

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

    @Test
    public void testAddClear() {
        final MutableSet<T> set = this.create();
        final T object = this.randomValue();
        set.add(object);
        set.clear();
        assertTrue(set.isEmpty());
    }

    @Test
    public void testEmpty_Remove() {
        final MutableSet<T> set = this.create();
        assertFalse(set.removeOnce(this.randomValue()));
        assertTrue(set.isEmpty());
    }

    @Test
    public void testSingleton_AddRemove() {
        final MutableSet<T> set = this.create();
        final T object = this.randomValue();
        set.add(object);
        assertTrue(set.contains(object));
        assertTrue(set.removeOnce(object));
        assertFalse(set.contains(object));
        assertTrue(set.isEmpty());
    }

}
