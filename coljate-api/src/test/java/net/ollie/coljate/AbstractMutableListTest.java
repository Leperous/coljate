package net.ollie.coljate;

import net.ollie.coljate.List;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author Ollie
 */
public abstract class AbstractMutableListTest<C extends List.Mutable<Object>>
        extends AbstractStreamableTest<C> {

    @Test
    public void shouldPrefix() {
        final Object o1 = new Object(), o2 = new Object();
        final C list = this.create(o1);
        list.prefix(o2);
        assertContains(list, o2, o1);
    }

    @Test
    public void shouldPrefixAll() {
        final Object o1 = new Object(), o2 = new Object(), o3 = new Object();
        final C list = this.create(o1);
        list.prefixAll(Arrays.asList(o2, o3));
        assertContains(list, o2, o3, o1);
    }

    @Test
    public void shouldSuffix() {
        final Object o1 = new Object(), o2 = new Object();
        final C list = this.create(o1);
        list.suffix(o2);
        assertContains(list, o1, o2);
    }

    @Test
    public void shouldSuffixAll() {
        final Object o1 = new Object(), o2 = new Object(), o3 = new Object();
        final C list = this.create(o1);
        list.suffixAll(Arrays.asList(o2, o3));
        assertContains(list, o1, o2, o3);
    }

    @Override
    protected void assertContains(final C collection, final Object... objects) {
        super.assertContains(collection, objects);
        if (objects.length > 0) {
            assertThat(collection.head(), is(objects[0]));
        }
        if (objects.length > 1) {
            final Object[] tail = new Object[objects.length - 1];
            System.arraycopy(objects, 1, tail, 0, objects.length - 1);
            assertThat(collection.tail(), is(this.create(tail)));
        }
    }

}
