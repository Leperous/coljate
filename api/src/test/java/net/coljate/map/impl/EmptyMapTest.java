package net.coljate.map.impl;

import java.util.Spliterator;

import net.coljate.map.ImmutableMapTest;
import net.coljate.map.SameObjectEntryCreator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class EmptyMapTest
        extends SameObjectEntryCreator
        implements ImmutableMapTest.ZeroEntryTests<Object, Object> {

    @Override
    public EmptyMap<Object, Object> createTestCollection() {
        return EmptyMap.instance();
    }

    @Override
    public int testSpliterator_Characteristics() {
        final int characteristics = this.createTestCollection().spliterator().characteristics();
        assertThat(characteristics & Spliterator.IMMUTABLE, is(Spliterator.IMMUTABLE));
        return characteristics;
    }

}
