package net.coljate.map;

import net.coljate.ContainerTest;

/**
 *
 * @author Ollie
 */
public abstract class ObjectMapTest
        extends ContainerTest
        implements MapTest<Object, Object> {

    @Override
    public Entry<Object, Object> createObject() {
        return new ImmutableEntry<>(new Object(), new Object());
    }

}
