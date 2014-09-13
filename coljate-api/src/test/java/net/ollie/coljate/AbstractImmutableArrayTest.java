package net.ollie.coljate;

import net.ollie.coljate.utils.numeric.NonNegativeInteger;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

/**
 * @author Ollie
 */
public abstract class AbstractImmutableArrayTest
        extends AbstractStreamableTest<Array.Immutable<Object>> {

    @Test
    public void shouldPrefixAndRemove() {

        final Array.Immutable<Object> empty = this.create();
        final Object object = new Object();

        final Array.Immutable<Object> singleton = empty.andPrefix(object);
        assertContainsNothing(empty);
        assertContainsExactly(singleton, object);

        final Array.Immutable<Object> emptyAgain = singleton.notFirst(object);
        assertContainsNothing(emptyAgain);
        assertContainsExactly(singleton, object);

    }

    @Test
    public void shouldSuffixAndRemove() {

        final Array.Immutable<Object> empty = this.create();
        final Object o1 = new Object();
        final Object o2 = new Object();

        final Array.Immutable<Object> duo = empty.andSuffix(o1).andSuffix(o2);
        assertContainsNothing(empty);
        assertContainsExactly(duo, o1, o2);

        final Array.Immutable<Object> singleton = duo.notFirst(o1);
        assertContainsExactly(singleton, o2);
        assertThat(singleton.indexOf(o1), nullValue());
        assertContainsExactly(duo, o1, o2);

    }

    @Test
    public void shouldRemoveAll() {

        final Object o1 = new Object(), o2 = new Object(), o3 = new Object();
        final Array.Immutable<Object> array = this.create(o1, o2, o3, o2);

        final Array.Immutable<Object> without = array.notAll(o2);
        assertThat(without.count(), is(NonNegativeInteger.of(2)));
        assertThat(without, is(this.create(o1, o3)));

    }

    @Override
    protected void assertContainsNothing(final Array.Immutable<Object> collection) {
        super.assertContainsNothing(collection);
        assertThat(collection.count().intValue(), is(0));
        assertThat(collection.notFirst(new Object()), is(collection));
        assertThat(collection.notAll(new Object()), is(collection));
    }

    protected void assertContainsExactly(final Array.Immutable<Object> array, final Object... objects) {
        assertEquals("Length of " + array, objects.length, array.count().intValue());
        assertEquals(objects.length == 0, array.isEmpty());
        int i = 0;
        for (final Object object : objects) {
            assertTrue(array.contains(object));
            assertThat("Index create [" + object + "] inside [" + array + "]",
                    array.indexOf(object),
                    is(NonNegativeInteger.of(i++)));
        }
    }

}
