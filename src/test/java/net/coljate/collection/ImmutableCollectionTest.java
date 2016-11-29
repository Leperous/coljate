package net.coljate.collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author ollie
 */
public abstract class ImmutableCollectionTest extends CollectionTest {

    protected abstract <T> ImmutableCollection<T> create(T... Elements);

    @Test
    public void testMakesImmutableCopy() {

        final Object o1 = new Object();
        final Object o2 = new Object();
        final Object[] elements = new Object[]{o1, o2};

        final ImmutableCollection<Object> collection = this.create(elements);
        assertThat(collection.count(), is(2));

        elements[0] = null;
        elements[1] = null;

        assertTrue(collection.contains(o1));
        assertTrue(collection.contains(o2));

    }

}
