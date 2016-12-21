package net.coljate.table;

import net.coljate.UnmodifiableIterator;
import net.coljate.util.Functions;

/**
 *
 * @author ollie
 */
public interface ImmutableMatrix<T>
        extends Matrix<T>, ImmutableTable<Integer, Integer, T> {

    @Override
    default ImmutableCell<Integer, Integer, T> cellIfPresent(final Object x, final Object y) {
        return Functions.ifNonNull(Matrix.super.cellIfPresent(x, y), Cell::immutableCopy);
    }

    @Override
    default UnmodifiableIterator<Cell<Integer, Integer, T>> iterator() {
        return UnmodifiableIterator.wrap(Matrix.super.iterator());
    }

    @Override
    @Deprecated
    default ImmutableMatrix<T> immutableCopy() {
        return this;
    }

}
