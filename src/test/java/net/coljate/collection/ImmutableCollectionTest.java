package net.coljate.collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author ollie
 */
public abstract class ImmutableCollectionTest extends CollectionTest {

    @Override
    protected abstract <T> ImmutableCollection<T> create(T... elements);

    @Test
    public void testImmutableCopy_Singleton() {
        final ImmutableCollection<Object> c1 = this.create(new Object());
        final ImmutableCollection<Object> c2 = c1.immutableCopy();
        assertThat(c1, sameInstance(c2));
    }
    
    @Test
    public void testCopyIsImmutable_Two() {

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
