package net.coljate.map;

import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Ollie
 */
public class SameObjectEntryCreator extends NewObjectEntryCreator {

    private Entry<Object, Object> entry;

    @BeforeEach
    public final void resetEntry() {
        entry = this.createTestObject();
    }

    public Entry<Object, Object> getCollectionElement() {
        return entry;
    }

}
