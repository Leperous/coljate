package net.coljate.table;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public interface MatrixTest<T> extends TableTest<Integer, Integer, T> {

    @Override
    Matrix<T> create(java.util.List<Cell<Integer, Integer, T>> cells);

    @Override
    @Deprecated
    default Cell<Integer, Integer, T> createObject() {
        return this.createObject(0, 0);
    }

    Cell<Integer, Integer, T> createObject(int row, int column);

}
