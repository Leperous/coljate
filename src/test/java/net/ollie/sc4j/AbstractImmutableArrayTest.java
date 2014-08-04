package net.ollie.sc4j;

import net.ollie.sc4j.Array.Immutable;
import net.ollie.sc4j.utils.numeric.NonNegativeInteger;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

/**
 * @author Ollie
 */
public abstract class AbstractImmutableArrayTest
        extends AbstractIteratableTest<Array.Immutable<Object>> {

    @Test
    public void shouldPrefixAndRemove() {

        final Array.Immutable<Object> empty = this.create();
        final Object object = new Object();

        final Array.Immutable<Object> singleton = empty.withPrefix(object);
        assertContainsNothing(empty);
        assertContainsExactly(singleton, object);

        final Array.Immutable<Object> emptyAgain = singleton.withoutFirst(object);
        assertContainsNothing(emptyAgain);
        assertContainsExactly(singleton, object);

    }

    @Test
    public void shouldSuffixAndRemove() {

        final Array.Immutable<Object> empty = this.create();
        final Object o1 = new Object();
        final Object o2 = new Object();

        final Array.Immutable<Object> duo = empty.withSuffix(o1).withSuffix(o2);
        assertContainsNothing(empty);
        assertContainsExactly(duo, o1, o2);

        final Array.Immutable<Object> singleton = duo.withoutFirst(o1);
        assertContainsExactly(singleton, o2);
        assertThat(singleton.indexOf(o1), nullValue());
        assertContainsExactly(duo, o1, o2);

    }

    @Override
    protected void assertContainsNothing(Immutable<Object> collection) {
        super.assertContainsNothing(collection);
        assertThat(collection.count().intValue(), is(0));
    }

    protected void assertContainsExactly(final Array.Immutable<Object> array, final Object... objects) {
        assertEquals(objects.length, array.count().intValue());
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
