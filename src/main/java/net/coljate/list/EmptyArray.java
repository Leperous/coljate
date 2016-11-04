package net.coljate.list;

/**
 *
 * @author ollie
 */
public class EmptyArray<T> extends ImmutableArray<T> {

    static final EmptyArray INSTANCE = new EmptyArray();
    private static final Object[] EMPTY = new Object[0];

    EmptyArray() {
        super(EMPTY, 0);
    }

}
