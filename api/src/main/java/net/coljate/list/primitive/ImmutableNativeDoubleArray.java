package net.coljate.list.primitive;

import net.coljate.collection.primitive.DoubleCollection;

/**
 *
 * @author Ollie
 */
public class ImmutableNativeDoubleArray extends NativeDoubleArray implements ImmutableDoubleArray {

    public static ImmutableDoubleArray copyOf(final DoubleCollection collection) {
        throw new UnsupportedOperationException();
    }

    protected ImmutableNativeDoubleArray(final double[] array) {
        super(array);
    }

    @Override
    public ImmutableDoubleListIterator iterator() {
        throw new UnsupportedOperationException(); //TODO
    }

}
