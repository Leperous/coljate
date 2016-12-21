package net.coljate.table;

import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Function;

import net.coljate.collection.Collection;
import net.coljate.table.lazy.LazyProductMatrix;
import net.coljate.table.lazy.LazyTransformedMatrix;
import net.coljate.table.lazy.LazyTransposedMatrix;

/**
 *
 * @author ollie
 */
public interface Matrix<T> extends Table<Integer, Integer, T> {

    T get(int x, int y);

    int width();

    int height();

    @Override
    default int count() {
        return Math.multiplyExact(this.width(), this.height());
    }

    @Override
    default Cell<Integer, Integer, T> cellIfPresent(final Object x, final Object y) {
        return x instanceof Integer && y instanceof Integer
                ? new ImmutableCell<>((int) x, (int) y, this.get((int) x, (int) y))
                : null;
    }

    @Override
    default Object[][] arrayCopy() {
        final int h = this.height();
        final int w = this.width();
        final Object[][] array = new Object[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                array[i][j] = this.get(i, j);
            }
        }
        return array;
    }

    default boolean isSquare() {
        return this.width() == this.height();
    }

    @Override
    default Iterator<Cell<Integer, Integer, T>> iterator() {
        return new MatrixIterator<>(this);
    }

    default Matrix<T> transpose() {
        return new LazyTransposedMatrix<>(this);
    }

    default <R> Matrix<R> transformValues(final Function<? super T, ? extends R> transformation) {
        return new LazyTransformedMatrix<>(this, transformation);
    }

    default <T2, P, V> Matrix<V> product(
            final Matrix<T2> that,
            final BiFunction<? super T, ? super T2, ? extends P> product,
            final Function<? super Collection<? extends P>, ? extends V> collate) {
        return new LazyProductMatrix<>(this, that, product, collate);
    }

    @Override
    MutableMatrix<T> mutableCopy();

    @Override
    ImmutableMatrix<T> immutableCopy();

    static <T> MutableMatrix<T> create(final int rows, final int columns) {
        return MutableMatrix.create(rows, columns);
    }

}
