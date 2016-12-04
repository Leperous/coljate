package net.coljate.collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.AssumptionViolatedException;
import org.junit.Test;

/**
 *
 * @author ollie
 */
public abstract class CollectionTest {

    /**
     *
     * @param <T>
     * @param elements
     * @return a collection containing the given elements.
     * @throws AssumptionViolatedException if the collection does not support
     * containing the given number of elements. In this case the test will be
     * skipped.
     */
    protected abstract <T> Collection<T> create(T... elements) throws AssumptionViolatedException;

    @Test
    public void testCount_Empty() {
        final Collection<Object> collection = this.create();
        this.assertEmpty(collection);
    }

    @Test
    public void testIterator_Empty() {
        final Collection<Object> collection = this.create();
        assertFalse(collection.iterator().hasNext());
    }

    @Test
    public void testEquality_Empty() {
        final Collection<Object> e1 = this.create();
        final Collection<Object> e2 = this.create();
        if (e1 instanceof MutableCollection || e2 instanceof MutableCollection) {
            assertFalse(e1 == e2);
        }
        assertTrue(e1.equals(e2));
        assertTrue(e2.equals(e1));
    }

    @Test
    public void testCount_Singleton() {
        final Collection<Object> collection = this.create(new Object());
        assertFalse(collection.isEmpty());
        assertThat(collection.count(), is(1));
    }

    @Test
    public void testMutableCopy_Empty() {
        final Collection<Object> collection = this.create();
        final MutableCollection<? extends Object> mutable = collection.mutableCopy();
        this.assertEmpty(mutable);
    }

    @Test
    public void testImmutableCopy_Empty() {
        final Collection<Object> collection = this.create();
        final ImmutableCollection<? extends Object> immutable = collection.immutableCopy();
        this.assertEmpty(immutable);
    }

    protected void assertEmpty(final Collection<?> collection) {
        assertTrue(collection.isEmpty());
        assertThat(collection.count(), is(0));
    }

}
