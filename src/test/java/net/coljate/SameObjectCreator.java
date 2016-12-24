package net.coljate;

import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author ollie
 */
public class SameObjectCreator implements TestObjectCreator<Object> {

    private Object object;

    @BeforeEach
    public final void resetObject() {
        object = new Object();
    }

    public Object getTestObject() {
        return object;
    }

    @Override
    @Deprecated
    public Object createTestObject() {
        return object;
    }

}
