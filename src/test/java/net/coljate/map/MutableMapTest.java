package net.coljate.map;

import net.coljate.set.MutableSetTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ollie
 */
public interface MutableMapTest<K, V> extends MapTest<K, V>, MutableSetTest<Entry<K, V>> {

    @Override
    MutableMap<K, V> createTestCollection();

    interface ZeroEntryTests<K, V> extends MutableMapTest<K, V>, MapTest.ZeroEntryTests<K, V>, MutableSetTest.ZeroElementTests<Entry<K, V>> {

        @Test
        default void testPut() {
            final MutableMap<K, V> map = this.createTestCollection();
            final Entry<K, V> entry = this.createTestObject();
            final V previous = map.put(entry.key(), entry.value());
            assertNull(previous);
            assertThat(map.get(entry.key()), is(entry.value()));
        }

        @Test
        default void testEvict_NullKey() {
            assertNull(this.createTestCollection().evict(null));
        }

    }

    interface OneEntryTests<K, V> extends MutableMapTest<K, V>, MapTest.OneEntryTests<K, V>, MutableSetTest.OneElementTests<Entry<K, V>> {

    }

}
