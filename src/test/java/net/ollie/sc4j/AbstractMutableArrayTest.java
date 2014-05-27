package net.ollie.sc4j;

import java.util.Arrays;
import java.util.OptionalInt;

import net.ollie.sc4j.Array.Mutable;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author Ollie
 */
public abstract class AbstractMutableArrayTest
        extends AbstractCollectionTest<Array.Mutable<Object>> {

    @Test
    public void shouldPrefix() {
        final Object o1 = new Object(), o2 = new Object();
        final Array.Mutable<Object> array = this.create(o1);
        array.prefix(o2);
        assertContains(array, o2, o1);
    }

    @Test
    public void shouldPrefixAll() {
        final Object o1 = new Object(), o2 = new Object(), o3 = new Object();
        final Array.Mutable<Object> array = this.create(o1);
        array.prefixAll(Arrays.asList(o2, o3));
        assertContains(array, o2, o3, o1);
    }

    @Test
    public void shouldSuffix() {
        final Object o1 = new Object(), o2 = new Object();
        final Array.Mutable<Object> array = this.create(o1);
        array.suffix(o2);
        assertContains(array, o1, o2);
    }

    @Test
    public void shouldSuffixAll() {
        final Object o1 = new Object(), o2 = new Object(), o3 = new Object();
        final Array.Mutable<Object> array = this.create(o1);
        array.suffixAll(Arrays.asList(o2, o3));
        assertContains(array, o1, o2, o3);
    }

    @Override
    protected void assertContains(final Mutable<Object> collection, final Object... objects) {
        super.assertContains(collection, objects);
        for (int i = 0; i < objects.length; i++) {
            assertThat(
                    "Object [" + objects[i] + "] should be " + i + "th position in [" + collection + "]",
                    collection.indexOf(objects[i]),
                    is(OptionalInt.of(i)));
        }
    }

}
