package net.coljate.set;

import java.util.Arrays;
import java.util.Spliterator;

import net.coljate.TestObjectCreator;
import net.coljate.collection.CollectionTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ollie
 */
public interface SetTest<T> extends CollectionTest<T> {

    @Override
    Set<T> createTestCollection();

    @Test
    default int testSpliterator_Characteristics() {
        final int characteristics = this.createTestCollection().spliterator().characteristics();
        assertThat(
                "Spliterator characteristics (" + Integer.toBinaryString(characteristics) + ") must contain distinct bit.",
                characteristics & Spliterator.DISTINCT,
                is(Spliterator.DISTINCT));
        return characteristics;
    }

    interface ZeroElementTests<T> extends SetTest<T>, CollectionTest.ZeroElementTests<T> {

    }

    interface OneElementTests<T> extends SetTest<T>, CollectionTest.OneElementTests<T> {

    }

    interface MultiElementTests<T> extends SetTest<T>, TestObjectCreator<T> {

        @Override
        default Set<T> createTestCollection() {
            return this.createTestCollection(java.util.Collections.emptyList());
        }

        default Set<T> createTestCollection(final T t1) {
            return this.createTestCollection(Arrays.asList(t1));
        }

        default Set<T> createTestCollection(final T... elements) {
            return this.createTestCollection(Arrays.asList(elements));
        }

        Set<T> createTestCollection(java.util.List<T> list);

        @Test
        default void testCount() {
            assertThat(this.createTestCollection(this.createTestObject(), this.createTestObject()).count(), is(2));
        }

        @Test
        default void testAnd() {
            final T a = this.createTestObject(), b = this.createTestObject(), c = this.createTestObject();
            final Set<T> first = this.createTestCollection(a, b);
            final Set<T> second = this.createTestCollection(b, c);
            final Set<T> and = first.and(second);
            assertThat(and.count(), is(1));
            assertFalse(and.contains(a));
            assertTrue(and.contains(b));
            assertFalse(and.contains(c));
        }

        @Test
        default void testOr() {
            final T a = this.createTestObject(), b = this.createTestObject(), c = this.createTestObject();
            final Set<T> first = this.createTestCollection(a, b);
            final Set<T> second = this.createTestCollection(b, c);
            final Set<T> or = first.or(second);
            assertThat(or.count(), is(3));
        }

        @Test
        default void testNot() {
            final T a = this.createTestObject(), b = this.createTestObject(), c = this.createTestObject();
            final Set<T> first = this.createTestCollection(a, b);
            final Set<T> second = this.createTestCollection(b, c);
            final Set<T> not = first.not(second);
            assertTrue(not.contains(a));
            assertFalse(not.contains(b));
            assertFalse(not.contains(c));
            assertThat(not.count(), is(1));
        }

        @Test
        default void testXor() {
            final T a = this.createTestObject(), b = this.createTestObject(), c = this.createTestObject();
            final Set<T> first = this.createTestCollection(a, b);
            final Set<T> second = this.createTestCollection(b, c);
            final Set<T> xor = first.xor(second);
            assertTrue(xor.contains(a));
            assertFalse(xor.contains(b));
            assertTrue(xor.contains(c));
            assertThat(xor.count(), is(2));
        }

    }

}
