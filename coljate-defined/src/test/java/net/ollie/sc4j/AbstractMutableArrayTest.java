package net.ollie.sc4j;

import net.ollie.sc4j.utils.numeric.NonNegativeInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 * @author Ollie
 */
public abstract class AbstractMutableArrayTest<C extends Array.Mutable<Object>>
        extends AbstractMutableListTest<C> {

    @Override
    protected void assertContains(final C collection, final Object... objects) {
        super.assertContains(collection, objects);
        for (int i = 0; i < objects.length; i++) {
            assertThat(
                    "Object [" + objects[i] + "] should be " + i + "th position in [" + collection + "]",
                    collection.indexOf(objects[i]),
                    is(NonNegativeInteger.of(i)));
        }
    }

}
