package net.ollie.sc4j;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Ollie
 */
public abstract class AbstractMutableMapTest<C extends Map.Mutable<Object, Object>>
        extends AbstractCollectionTest<C> {

    @Test
    public void shouldPutAndRemove() {
        final Map.Mutable<Object, Object> map = this.create();
        final Object key = new Object(), value = new Object();
        map.put(key, value);
        assertContainsKeyValue(map, key, value);
    }

    protected <K, V> void assertContainsKeyValue(final Map<K, V> map, final K key, final V value) {
        assertTrue(map.contains(value));
        assertTrue(map.containsKey(key));
        assertTrue(map.containsValue(value));
    }

}
