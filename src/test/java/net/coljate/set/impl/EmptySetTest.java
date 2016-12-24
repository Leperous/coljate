package net.coljate.set.impl;

import net.coljate.collection.EmptyTest;
import net.coljate.set.ImmutableSetTest;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class EmptySetTest implements ImmutableSetTest<Object>, EmptyTest<Object> {

    @Override
    public EmptySet<Object> createTestCollection() {
        return EmptySet.instance();
    }

}
