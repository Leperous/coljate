package net.coljate.set.impl;

import net.coljate.collection.CollectionTest;
import net.coljate.set.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class LazyIntersectionSetTest extends CollectionTest {

    @Override
    protected <T> Set<T> create(final T... elements) {
        Set<T> current = Set.of();
        for (final T element : elements) {
            current = LazyIntersectionSet.viewOf(Set.of(element), current);
        }
        return current;
    }

    @Test
    @Override
    public void testContains_Singleton() {
        final Object o1 = this.createObject();
        final Set<Object> collection = this.create(o1);
        assertFalse(collection.contains(o1));
    }

    @Test
    public void testContains_Intersection() {
        final Object object = this.createObject();
        final Set<?> set = LazyIntersectionSet.viewOf(Set.of(object), Set.of(object));
        assertTrue(set.contains(object));
        assertThat(set.count(), is(1));
    }

}
