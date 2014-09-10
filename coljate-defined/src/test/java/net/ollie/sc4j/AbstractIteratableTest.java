package net.ollie.sc4j;

import net.ollie.sc4j.access.Streamable;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 * @author Ollie
 */
public abstract class AbstractIteratableTest<C extends Streamable<Object>>
        extends AbstractCollectionTest<C> {

    @Override
    protected void assertContainsNothing(C collection) {
        super.assertContainsNothing(collection);
        assertThat(collection.count(), is(0));
    }

}
