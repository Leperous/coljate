package net.coljate.set.impl;

import java.util.Spliterator;

import net.coljate.collection.EmptyTest;
import net.coljate.set.ImmutableSetTest;
import net.coljate.set.SetTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Disabled;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class EmptySetTest implements ImmutableSetTest<Object>, SetTest.ZeroElementTests<Object>, EmptyTest<Object> {

    @Override
    @Disabled
    public int testSpliterator_Characteristics() {
        final int characteristics = this.createTestCollection().spliterator().characteristics();
        assertThat(characteristics & Spliterator.IMMUTABLE, is(Spliterator.IMMUTABLE));
        return characteristics;
    }

    @Override
    public EmptySet<Object> createTestCollection() {
        return EmptySet.instance();
    }

}
