package net.coljate.table.impl;

import net.coljate.UnmodifiableIterator;
import net.coljate.table.Cell;
import net.coljate.table.ImmutableMatrix;

/**
 *
 * @author ollie
 */
public class ImmutableArrayBackedMatrix<T>
        extends ArrayBackedMatrix<T>
        implements ImmutableMatrix<T> {

    protected ImmutableArrayBackedMatrix(final Object[][] matrix) {
        super(matrix);
    }

    @Override
    public UnmodifiableIterator<Cell<Integer, Integer, T>> iterator() {
        return UnmodifiableIterator.wrap(super.iterator());
    }

}
