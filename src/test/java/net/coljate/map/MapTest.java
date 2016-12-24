package net.coljate.map;

import net.coljate.collection.CollectionTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ollie
 */
public interface MapTest<K, V> extends CollectionTest<Entry<K, V>> {

    @Override
    Map<K, V> createTestCollection();

    interface ZeroElementTests<K, V> extends MapTest<K, V>, CollectionTest.ZeroElementTests<Entry<K, V>> {

    }

    interface OneElementTests<K, V> extends MapTest<K, V>, CollectionTest.OneElementTests<Entry<K, V>> {

        @Test
        default void testKeys() {
            final Map<K, V> map = this.createTestCollection();
            assertThat(map.keys().count(), is(1));
            assertTrue(map.keys().contains(this.getCollectionElement().key()));
            assertFalse(map.keys().contains(this.getCollectionElement().value()));
        }

    }

}
