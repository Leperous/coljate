package net.coljate.list;

import java.util.Iterator;

/**
 *
 * @author ollie
 */
public class ImmutableArray<T> extends Array<T> implements ImmutableList<T> {

    @Override
    protected Object[] underlying() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int length() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int count() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
