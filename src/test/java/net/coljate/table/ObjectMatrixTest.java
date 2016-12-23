package net.coljate.table;

import net.coljate.ContainerTest;

/**
 *
 * @author ollie
 */
public abstract class ObjectMatrixTest
        extends ContainerTest
        implements MatrixTest<Object> {

    @Override
    public Cell<Integer, Integer, Object> createObject(int row, int column) {
        return new ImmutableCell<>(row, column, new Object());
    }

}
