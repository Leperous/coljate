package net.ollie.coljate.maps;

import java.util.Arrays;
import java.util.stream.Collectors;

import net.ollie.coljate.AbstractStreamableTest;
import static net.ollie.coljate.matchers.MapMatchers.containsKey;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Ollie
 */
public abstract class AbstractMapTest<C extends Map<Object, Object>>
        extends AbstractStreamableTest<C> {

    protected abstract C create(Iterable<Map.Entry<Object, Object>> entries);

    @Override
    protected C create(final Object... objects) {
        return this.create(Arrays.stream(objects).map(object -> entry(object, object)).collect(Collectors.toList()));
    }

    @Test
    public void testSingleton() {

        final Object key = new Object(), value = new Object();
        final C map = this.create(java.util.Collections.singleton(entry(key, value)));

        assertThat(map, containsKey(key));
        assertThat(map, not(containsKey(value)));
        assertThat(map, not(containsKey(new Object())));

        assertTrue(map.containsValue(value));
        assertFalse(map.containsValue(key));
        assertFalse(map.containsValue(new Object()));

    }

    protected <K, V> void assertContainsKeyAndValue(final Map<K, V> map, final K key, final V value) {
        assertTrue(map.contains(value));
        assertTrue(map.containsKey(key));
        assertTrue(map.containsValue(value));
        assertThat(map.get(key), is(value));
    }

    protected <K, V> void assertNotContainsKeyOrValue(final Map<K, V> map, final K key, final V value) {
        assertFalse("Map [" + map + "] should not contain key [" + map + "]", map.containsKey(key));
        assertFalse(map.containsValue(value));
        assertFalse(map.contains(value));
    }

    protected static <K, V> Map.Entry<K, V> entry(final K key, final V value) {
        return new Map.Entry<K, V>() {

            @Override
            public K key() {
                return key;
            }

            @Override
            public V value() {
                return value;
            }

            @Override
            public Map.Entry<V, K> inverseCopy() {
                return entry(value, key);
            }

            @Override
            public Map.Mutable.Entry<K, V> mutableCopy() {
                throw new UnsupportedOperationException("mutableCopy not supported yet!");
            }

            @Override
            public Map.Immutable.Entry<K, V> immutableCopy() {
                throw new UnsupportedOperationException("immutableCopy not supported yet!");
            }

        };
    }

}
