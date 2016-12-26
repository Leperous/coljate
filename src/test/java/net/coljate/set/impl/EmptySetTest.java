package net.coljate.set.impl;

import net.coljate.collection.EmptyTest;
import net.coljate.set.ImmutableSetTest;
import net.coljate.set.SetTest;

import org.junit.jupiter.api.Disabled;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class EmptySetTest implements ImmutableSetTest<Object>, SetTest.ZeroElementTests<Object>, EmptyTest<Object> {

    @Override
    @Disabled
    public void testSpliterator_Characteristics() {
        ImmutableSetTest.super.testSpliterator_Characteristics();
    }

    @Override
    public EmptySet<Object> createTestCollection() {
        return EmptySet.instance();
    }

}
