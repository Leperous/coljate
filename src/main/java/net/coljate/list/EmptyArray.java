package net.coljate.list;

import net.coljate.utils.Arrays;

/**
 *
 * @author ollie
 */
public class EmptyArray<T> extends ImmutableArray<T> {

    static final EmptyArray INSTANCE = new EmptyArray();

    EmptyArray() {
        super(Arrays.EMPTY, 0);
    }

}
