package net.coljate.map.impl;

import org.junit.jupiter.api.Nested;

import net.coljate.map.MutableBidirectionalMapTest;
import net.coljate.map.NewObjectEntryCreator;

/**
 *
 * @author Ollie
 */
public class MutableHashBidirectionalMapTest {

    @Nested
    class EmptyBidirectionMapTest extends NewObjectEntryCreator implements MutableBidirectionalMapTest.ZeroEntryTests<Object, Object> {

        @Override
        public MutableHashBidirectionalMap<Object, Object> createTestCollection() {
            return MutableHashBidirectionalMap.create();
        }

    }

}
