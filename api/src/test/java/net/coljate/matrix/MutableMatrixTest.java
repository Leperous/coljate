package net.coljate.matrix;

import net.coljate.table.MutableMatrix;
import net.coljate.table.MutableTableTest;

/**
 *
 * @author ollie
 */
public interface MutableMatrixTest<T> extends MatrixTest<T>, MutableTableTest<Integer, Integer, T> {

    @Override
    MutableMatrix<T> createTestCollection();

}
