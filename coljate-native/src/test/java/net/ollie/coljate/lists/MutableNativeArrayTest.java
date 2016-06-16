package net.ollie.coljate.lists;

import net.ollie.coljate.list.MutableArray;

/**
 *
 * @author ollie
 */
public class MutableNativeArrayTest extends MutableArrayTest {

    @Override
    protected MutableArray<Object> create(final Object... objects) {
        return MutableNativeArray.copyOf(objects);
    }

}
