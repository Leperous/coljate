package net.coljate.table.lazy;

import java.util.function.BiFunction;

import net.coljate.table.Matrix;

/**
 *
 * @author Ollie
 */
public class LazySumMatrix<A, B, T>
        implements LazyMatrix<T> {

    private final Matrix<A> a;
    private final Matrix<B> b;
    private final BiFunction<? super A, ? super B, ? extends T> sum;

    public LazySumMatrix(
            final Matrix<A> a,
            final Matrix<B> b,
            final BiFunction<? super A, ? super B, ? extends T> sum) {
        if (a.width() != b.width() || a.height() != b.height()) {
            throw new IllegalArgumentException("Matrices not same size");
        }
        this.a = a;
        this.b = b;
        this.sum = sum;
    }

    @Override
    public T get(final int x, final int y) {
        return sum.apply(a.get(x, y), b.get(x, y));
    }

    @Override
    public int width() {
        return a.width();
    }

    @Override
    public int height() {
        return a.height();
    }

}
