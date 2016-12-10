//package net.coljate.set.impl;
//
//import net.coljate.set.Set;
//
//import static org.hamcrest.CoreMatchers.instanceOf;
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertThat;
//import static org.junit.Assert.assertTrue;
//import org.junit.Test;
//
///**
// *
// * @author ollie
// */
//@SuppressWarnings({"unchecked", "rawtypes"})
//public class LazyUnionSetTest {
//
//    @Test
//    public void testContains() {
//        final Object o1 = this.createObject(), o2 = this.createObject(), o3 = this.createObject();
//        final Set<Object> s1 = Set.of(o1, o2);
//        final Set<Object> s2 = Set.of(o2, o3);
//        final Set<Object> union = LazyUnionSet.viewOf(s1, s2);
//        assertThat(union, instanceOf(LazyUnionSet.class));
//        assertThat(union.count(), is(3));
//        assertTrue(union.contains(o1));
//        assertTrue(union.contains(o2));
//        assertTrue(union.contains(o3));
//        assertFalse(union.contains(this.createObject()));
//    }
//
//}
