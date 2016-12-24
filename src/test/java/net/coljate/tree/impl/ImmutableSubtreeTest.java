package net.coljate.tree.impl;

import net.coljate.map.NewObjectEntryCreator;
import net.coljate.tree.ImmutableTree;
import net.coljate.tree.ImmutableTreeTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class ImmutableSubtreeTest extends SubtreeTest {

    @Nested
    class LeafTreeTest extends NewObjectEntryCreator implements ImmutableTreeTest.OneEntryTests<Object, Object> {

        private ImmutableSubtree<Object, Object> lastTree;

        @BeforeEach
        public void beforeTree() {
            Assumptions.assumeTrue(lastTree == null);
            lastTree = ImmutableSubtree.of(new Object(), new Object());
        }

        @AfterEach
        public final void afterTree() {
            lastTree = null;
        }

        @Override
        public ImmutableTree<Object, Object, ?> createTestCollection() {
            return lastTree;
        }

        @Override
        public ImmutableSubtree<Object, Object> getCollectionElement() {
            return lastTree;
        }

        @Override
        @Disabled //TODO
        public void testMutableCopy() {
            OneEntryTests.super.testMutableCopy();
        }
        
        

    }

}
