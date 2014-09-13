package net.ollie.coljate;

import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.access.Streamable.Stream;
import net.ollie.coljate.utils.numeric.NonNegativeInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
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

}
