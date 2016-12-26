package net.coljate.cache;

import net.coljate.collection.Collection;
import net.coljate.list.List;
import net.coljate.map.Entry;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ollie
 */
public interface MutableListMultimapTest<K, V>
        extends ListMultimapTest<K, V>, MutableMultimapTest<K, V> {

    @Override
    MutableListMultimap<K, V> createTestCollection();

    interface ZeroEntryTests<K, V>
            extends MutableListMultimapTest<K, V>, ListMultimapTest.ZeroEntryTests<K, V>, MutableMultimapTest.ZeroEntryTests<K, V> {

        @Test
        @Override
        default void testAdd() {

            final Entry<K, Collection<V>> entry = this.createTestObject();
            final MutableListMultimap<K, V> multimap = this.createTestCollection();

            assertTrue(multimap.add(entry));
            assertTrue(multimap.add(entry));

            final List<V> got = multimap.get(entry.key());
            assertThat(got.count(), is(2));

        }

    }

}
