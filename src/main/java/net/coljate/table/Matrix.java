package net.coljate.table;

/**
 *
 * @author ollie
 */
public interface Matrix<T> extends Table<Integer, Integer, T> {

    int width();

    int height();

    T get(int x, int y);

    @Override
    default Cell<Integer, Integer, T> cellIfPresent(final Object x, final Object y) {
        return x instanceof Integer && y instanceof Integer
                ? new ImmutableCell<>((int) x, (int) y, this.get((int) x, (int) y))
                : null;
    }

    @Override
    Object[][] arrayCopy();

    @Override
    MutableMatrix<T> mutableCopy();

    @Override
    ImmutableMatrix<T> immutableCopy();

    default boolean isSquare() {
        return this.width() == this.height();
    }

    static <T> MutableMatrix<T> create(final int rows, final int columns) {
        return MutableMatrix.create(rows, columns);
    }

}
