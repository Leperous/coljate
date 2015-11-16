package net.ollie.coljate;

import java.util.Iterator;

import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.access.Streamable.Stream;
import net.ollie.coljate.utils.numeric.NonNegativeInteger;

import static org.hamcrest.CoreMatchers.is;
import org.hamcrest.core.AnyOf;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Ollie
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public abstract class AbstractStreamableTest<C extends Streamable<Object>>
        extends AbstractCollectionTest<C> {

    @Override
    protected void assertContainsNothing(final C collection) {
        super.assertContainsNothing(collection);
        assertThat(collection.count(), is(NonNegativeInteger.ZERO));
    }

    @Test
    public void shouldImplementStream() {
        final Object object = new Object();
        final Stream<Object, ? extends Streamable<Object>> stream = this.create(object).stream();
        assertNotNull(stream);
        assertFalse(stream.filter(o -> o != object).collect().contains(object));
    }

    @Test
    public void shouldImplementIterator() {
        final Object o1 = new Object(), o2 = new Object();
        final Iterator<Object> iterator = this.create(o1, o2).iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), AnyOf.anyOf(is(o1), is(o2)));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), AnyOf.anyOf(is(o1), is(o2)));
        assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldImplementImmutableCopy() {
        final Object o1 = new Object(), o2 = new Object();
        final C collection = this.create(o1, o2);
        final Streamable.Immutable<Object> immutable = collection.immutableCopy();
        assertNotNull(immutable);
        assertTrue(immutable.contains(o1));
        assertTrue(immutable.contains(o2));
    }

}
