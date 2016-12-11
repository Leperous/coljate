package net.coljate;

import org.junit.Before;

/**
 *
 * @author ollie
 */
public abstract class ObjectContainerTest
        extends ContainerTest
        implements Tests<Object> {

    private int objectCounter;

    @Before
    public void resetObjectCounter() {
        objectCounter = 0;
    }

    @Override
    public Object createObject() {
        return new Object() {

            @Override
            public String toString() {
                return (++objectCounter) + ":" + Integer.toHexString(this.hashCode());
            }

        };
    }

}
