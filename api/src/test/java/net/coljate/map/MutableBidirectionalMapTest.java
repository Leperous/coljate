package net.coljate.map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public interface MutableBidirectionalMapTest<K, V>
        extends BidirectionalMapTest<K, V>, MutableMapTest<K, V> {

    @Override
    MutableBidirectionalMap<K, V> createTestCollection();

    interface ZeroEntryTests<K, V> extends MutableBidirectionalMapTest<K, V>, BidirectionalMapTest.ZeroEntryTests<K, V>, MutableMapTest.ZeroEntryTests<K, V> {
        
        @Test
        default void testPut_Inverse() {

            final Entry<K, V> entry = this.createTestObject();
            final K key = entry.key();
            final V value = entry.value();

            final MutableBidirectionalMap<K, V> map = this.createTestCollection();
            final V previous = map.put(entry);
            assertNull(previous);
            assertThat(map.getEntry(key), is(entry));

            assertThat(map.inverseView().getEntry(value), is(Entry.of(value, key)));
        }

    }

    interface OneEntryTests<K, V> extends MutableBidirectionalMapTest<K, V>, BidirectionalMapTest.OneEntryTests<K, V>, MutableMapTest.OneEntryTests<K, V> {

    }

}
