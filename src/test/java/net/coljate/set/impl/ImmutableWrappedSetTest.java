package net.coljate.set.impl;

import net.coljate.NewObjectCreator;
import net.coljate.SameObjectCreator;
import net.coljate.set.ImmutableSet;
import net.coljate.set.ImmutableSetTest;

import org.junit.jupiter.api.Nested;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class ImmutableWrappedSetTest {

    @Nested
    class OneElementTest extends SameObjectCreator implements ImmutableSetTest.OneElementTests<Object> {

        @Override
        public ImmutableSet<Object> createTestCollection() {
            return ImmutableWrappedSet.copyIntoHashSet(this.getCollectionElement());
        }

    }

    @Nested
    class MultiElementTest extends NewObjectCreator implements ImmutableSetTest.MultiElementTests<Object> {

        @Override
        public ImmutableSet<Object> createTestCollection(final java.util.List<Object> list) {
            return ImmutableSet.copyOf(list);
        }

    }

}
