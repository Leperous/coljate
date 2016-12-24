package net.coljate.set.impl;

import net.coljate.NewObjectCreator;
import net.coljate.SameObjectCreator;
import net.coljate.set.ImmutableSet;
import net.coljate.set.ImmutableSetTest;
import net.coljate.set.SetTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class ImmutableWrappedSetTest {

    @Nested
    class OneElementTest extends SameObjectCreator implements ImmutableSetTest.OneElementTest<Object> {

        @Override
        public ImmutableSet<Object> createTestCollection() {
            return ImmutableWrappedSet.copyIntoHashSet(this.getTestObject());
        }

    }

    @Nested
    class RepeatedElementTest extends NewObjectCreator implements ImmutableSetTest<Object>, SetTest.DuplicateElementTests<Object> {

        private Object o1, o2;

        @BeforeEach
        public final void resetTestObjects() {
            o1 = this.createTestObject();
            o2 = this.createTestObject();
        }

        @Override
        public ImmutableSet<Object> createTestCollection() {
            return ImmutableWrappedSet.copyIntoHashSet(o1, o2, o1);
        }

        @Override
        public Object firstElement() {
            return o1;
        }

        @Override
        public Object secondElement() {
            return o2;
        }

    }

}
