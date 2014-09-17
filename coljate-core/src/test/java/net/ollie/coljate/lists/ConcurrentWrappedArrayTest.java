package net.ollie.coljate.lists;

/**
 *
 * @author Ollie
 */
public class ConcurrentWrappedArrayTest
        extends AbstractMutableArrayTest<Array.Mutable<Object>> {

    @Override
    protected Array.Mutable<Object> create(final Object... objects) {
        return ConcurrentWrappedArray.copy(objects);
    }

}
