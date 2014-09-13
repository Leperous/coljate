package net.ollie.coljate;

import org.junit.Test;

/**
 *
 * @author Ollie
 * @see AbstractMutableMapTest
 */
public abstract class AbstractImmutableMapTest<C extends Map.Immutable<Object, Object>>
        extends AbstractMapTest<C> {

    @Test
    public void shouldImplementWithAndWithout() {

        final C originalMap = this.create();

        final Object key = new Object(), value = new Object();
        final Map.Immutable<Object, Object> newMap = originalMap.with(key, value);

        assertNotContainsKeyOrValue(originalMap, key, value);

        assertContainsKeyAndValue(newMap, key, value);
        assertNotContainsKeyOrValue(newMap, new Object(), new Object());

        final Map.Immutable<Object, Object> clearedMap = newMap.without(key);
        assertNotContainsKeyOrValue(clearedMap, key, value);

    }

}
