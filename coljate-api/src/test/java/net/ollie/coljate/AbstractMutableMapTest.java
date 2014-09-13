package net.ollie.coljate;

import net.ollie.coljate.Map;

import org.junit.Test;

/**
 *
 * @author Ollie
 */
public abstract class AbstractMutableMapTest<C extends Map.Mutable<Object, Object>>
        extends AbstractMapTest<C> {

    @Test
    public void shouldPutAndRemove() {
        final C map = this.create();
        final Object key = new Object(), value = new Object();
        map.put(key, value);
        assertContainsKeyAndValue(map, key, value);
        map.remove(key);
        assertNotContainsKeyOrValue(map, key, value);
        assertContainsNothing(map);
    }

}
