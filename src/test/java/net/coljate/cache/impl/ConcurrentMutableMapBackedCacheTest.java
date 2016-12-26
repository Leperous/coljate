package net.coljate.cache.impl;

import java.util.function.Function;

import net.coljate.cache.MutableCache;
import net.coljate.cache.MutableCacheTest;
import net.coljate.map.NewObjectEntryCreator;

import org.junit.jupiter.api.Nested;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class ConcurrentMutableMapBackedCacheTest {

    @Nested
    class ZeroEntryTest
            extends NewObjectEntryCreator
            implements MutableCacheTest.ZeroEntryTests<Object, Object> {

        @Override
        public MutableCache<Object, Object> createTestCollection() {
            return new ConcurrentMutableMapBackedCache<>(Function.identity());
        }

    }

}
