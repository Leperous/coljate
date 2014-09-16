package net.ollie.coljate;

import net.ollie.coljate.Collection;

import java.util.Arrays;

import javax.annotation.OverridingMethodsMustInvokeSuper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Ollie
 */
public abstract class AbstractCollectionTest<C extends Collection<Object>> {

    protected abstract C create(final Object... objects);

    @Test
    public void shouldCreateEmpty() {
        assertContainsNothing(this.create());
    }

    @Test
    public void emptyShouldImplementEqualsAndHash() {
        assertEquals(this.create(), this.create());
        assertEquals(this.create().hashCode(), this.create().hashCode());
    }

    @Test
    public void singletonShouldImplementEqualsAndHash() {
        final Object o1 = new Object();
        assertEquals(this.create(o1), this.create(o1));
        assertEquals(this.create(o1).hashCode(), this.create(o1).hashCode());
    }

    @OverridingMethodsMustInvokeSuper
    protected void assertContainsNothing(final C collection) {
        assertTrue("Collection [" + collection + "] should be empty", collection.isEmpty());
        assertFalse("Collection [" + collection + "] should not contain anything", collection.contains(new Object()));
    }

    @OverridingMethodsMustInvokeSuper
    protected void assertContains(final C collection, final Object... objects) {
        if (objects.length == 0) {
            assertContainsNothing(collection);
        }
        for (final Object object : objects) {
            assertTrue("Collection [" + collection + "] of class [" + collection.getClass().getSimpleName() + "] should contain [" + object + "]",
                    collection.contains(object));
        }
        assertTrue(collection.containsAny(Arrays.asList(objects)));
        assertTrue(collection.containsAll(Arrays.asList(objects)));
    }

}
