package net.coljate;

/**
 *
 * @author ollie
 */
public class NewObjectCreator implements TestObjectCreator<Object> {

    @Override
    public Object createTestObject() {
        return new Object();
    }

}
