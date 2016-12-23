package net.coljate.table.impl;

import net.coljate.collection.AllCollectionSizeTest;
import net.coljate.table.Cell;
import net.coljate.table.ObjectMatrixTest;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class ArrayBackedMatrixTest
        extends ObjectMatrixTest
        implements AllCollectionSizeTest<Cell<Integer, Integer, Object>> {

    @Override
    public ArrayBackedMatrix<Object> create(final java.util.List<Cell<Integer, Integer, Object>> cells) {
        return ArrayBackedMatrix.copyOf(cells);
    }

}
