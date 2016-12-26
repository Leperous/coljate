package net.coljate.cache.impl;

import net.coljate.cache.MutableListMultimap;
import net.coljate.cache.MutableListMultimapTest;
import net.coljate.collection.Collection;
import net.coljate.list.ImmutableList;
import net.coljate.list.MutableList;
import net.coljate.map.Entry;
import net.coljate.map.ImmutableEntry;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MutableCacheBackedListMultimapTest {

    @Nested
    class ZeroEntryTest
            implements MutableListMultimapTest.ZeroEntryTests<Object, Object> {

        @Override
        public MutableListMultimap<Object, Object> createTestCollection() {
            return MutableCacheBackedListMultimap.createLinkedListMultimap();
        }

        @Override
        public Entry<Object, Collection<Object>> createTestObject() {
            return ImmutableEntry.of(new Object(), ImmutableList.of(new Object()));
        }

        @Test
        public void testGet_Missing() {
            final MutableList<Object> list = this.createTestCollection().get(new Object());
            assertNotNull(list);
            assertTrue(list.isEmpty());
        }

    }

}
