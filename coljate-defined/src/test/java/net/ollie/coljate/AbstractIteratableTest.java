package net.ollie.coljate;

import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.utils.numeric.NonNegativeInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 * @author Ollie
 */
public abstract class AbstractIteratableTest<C extends Streamable<Object>>
        extends AbstractCollectionTest<C> {

    @Override
    protected void assertContainsNothing(final C collection) {
        super.assertContainsNothing(collection);
        assertThat(collection.count(), is(NonNegativeInteger.ZERO));
    }

}
