package net.ollie.sc4j.collections;

import net.ollie.sc4j.AbstractMutableListTest;
import net.ollie.sc4j.utils.Arrays;

/**
 *
 * @author Ollie
 */
public class MutableListTest
        extends AbstractMutableListTest<MutableList<Object>> {

    @Override
    protected MutableList<Object> create(final Object... objects) {
        return MutableList.copy(Arrays.asList(objects));
    }

}
