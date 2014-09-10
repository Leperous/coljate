package net.ollie.coljate;

import net.ollie.coljate.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Ollie
 */
public abstract class AbstractMutableMapTest<C extends Map.Mutable<Object, Object>>
        extends AbstractIteratableTest<C> {

    @Test
    public void shouldPutAndRemove() {
        final C map = this.create();
        final Object key = new Object(), value = new Object();
        map.put(key, value);
        assertContainsKeyValue(map, key, value);
        map.remove(key);
        assertNotContainsKeyValue(map, key, value);
        assertContainsNothing(map);
    }

    protected <K, V> void assertContainsKeyValue(final Map<K, V> map, final K key, final V value) {
        assertTrue(map.contains(value));
        assertTrue(map.containsKey(key));
        assertTrue(map.containsValue(value));
    }

    protected <K, V> void assertNotContainsKeyValue(final Map<K, V> map, final K key, final V value) {
        assertFalse(map.contains(value));
        assertFalse(map.containsKey(key));
        assertFalse(map.containsValue(value));
    }

}
