package net.coljate.map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Ollie
 */
public interface BidirectionalMapTest<K, V> extends MapTest<K, V> {

    @Override
    BidirectionalMap<K, V> createTestCollection();

    interface ZeroEntryTests<K, V> extends BidirectionalMapTest<K, V>, MapTest.ZeroEntryTests<K, V> {

        @Test
        default void testCount_Inverse() {
            assertThat(this.createTestCollection().inverseView().count(), is(0));
        }

        @Test
        default void testIsEmpty_Inverse() {
            assertTrue(this.createTestCollection().inverseView().isEmpty());
        }

    }

    interface OneEntryTests<K, V> extends BidirectionalMapTest<K, V>, MapTest.OneEntryTests<K, V> {

        @Test
        default void testCount_Inverse() {
            assertThat(this.createTestCollection().inverseView().count(), is(1));
        }

        @Test
        default void testIsEmpty_Inverse() {
            assertFalse(this.createTestCollection().inverseView().isEmpty());
        }

    }

}
