package net.coljate.list;

/**
 *
 * @author ollie
 */
public abstract class Array<T> implements List<T> {

    protected abstract Object[] underlying();

    @Override
    public MutableArray<T> mutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
