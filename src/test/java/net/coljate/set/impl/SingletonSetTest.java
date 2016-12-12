package net.coljate.set.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import org.junit.jupiter.api.Test;

import net.coljate.collection.ObjectCollectionTest;
import net.coljate.collection.SingletonCollectionTest;
import net.coljate.set.ImmutableSetTest;

/**
 *
 * @author ollie
 */
public class SingletonSetTest
        extends ObjectCollectionTest
        implements ImmutableSetTest<Object>, SingletonCollectionTest<Object> {

    @Override
    public SingletonSet<Object> create(final Object element) {
        return SingletonSet.of(element);
    }

    @Override
    public SingletonSet<Object> create(final java.util.List<Object> elements) {
        assumeTrue(elements.size() == 1);
        return this.create(elements.get(0));
    }

    @Test
    public void testPermitsNull() {
        final SingletonSet<Object> singleton = SingletonSet.of(null);
        assertTrue(singleton.contains(null));
    }

}
