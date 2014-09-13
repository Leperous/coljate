package net.ollie.coljate;

import org.junit.Test;

/**
 *
 * @author Ollie
 */
public abstract class AbstractImmutableSetTest
        extends AbstractStreamableTest<Set.Immutable<Object>> {

    @Test
    public void shouldAddAndRemove() {
        final Object o1 = new Object(), o2 = new Object();
        final Set.Immutable<Object> set = this.create(o1);
        final Set.Immutable<Object> added = set.and(o2);
        assertContains(set, o1);
        assertContains(added, o1, o2);
    }

}
