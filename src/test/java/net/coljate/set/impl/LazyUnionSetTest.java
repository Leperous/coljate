package net.coljate.set.impl;

import net.coljate.set.Set;

import static org.hamcrest.CoreMatchers.instanceOf;
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
public class LazyUnionSetTest {

    @Test
    public void testContains() {
        final Object o1 = new Object(), o2 = new Object(), o3 = new Object();
        final Set<Object> s1 = Set.copyOf(o1, o2);
        final Set<Object> s2 = Set.copyOf(o2, o3);
        final Set<Object> union = LazyUnionSet.unionOf(s1, s2);
        assertThat(union, instanceOf(LazyUnionSet.class));
        assertThat(union.count(), is(3));
        assertTrue(union.contains(o1));
        assertTrue(union.contains(o2));
        assertTrue(union.contains(o3));
        assertFalse(union.contains(new Object()));
    }

}
