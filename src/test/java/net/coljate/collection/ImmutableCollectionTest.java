package net.coljate.collection;

import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author ollie
 */
public interface ImmutableCollectionTest<T> extends CollectionTest<T> {

    @Override
    ImmutableCollection<T> create(T... elements);

    @Test
    default void testImmutableCopy_Empty_IsSelf() {
        final ImmutableCollection<T> c1 = this.create();
        final ImmutableCollection<T> c2 = c1.immutableCopy();
        assertThat(c1, sameInstance(c2));
    }

}
