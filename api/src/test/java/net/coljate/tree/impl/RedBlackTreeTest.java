package net.coljate.tree.impl;

import net.coljate.map.Entry;
import net.coljate.tree.MutableBinaryTree;
import net.coljate.tree.MutableBinaryTreeTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class RedBlackTreeTest {

    class BaseIntegerKeyTest {

        private int counter;

        @BeforeEach
        public final void resetKeyCounter() {
            counter = 0;
        }

        public Entry<Integer, Object> createTestObject() {
            return Entry.of(counter++, new Object());
        }

    }

    @Nested
    class EmptyRedBlackTreeTest
            extends BaseIntegerKeyTest
            implements MutableBinaryTreeTest.ZeroNodeTests<Integer, Object> {

        @Override
        public MutableBinaryTree<Integer, Object, ?> createTestCollection() {
            return RedBlackTree.keyComparing();
        }

        @Override
        @Disabled //FIXME implement, reenable
        public void testImmutableCopy() {
            ZeroNodeTests.super.testImmutableCopy();
        }

    }

}
