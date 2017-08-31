package net.coljate.table.lazy;

import net.coljate.table.Matrix;

/**
 *
 * @author Ollie
 */
public class LazyTransposedMatrix<T>
        implements LazyMatrix<T> {

    private final Matrix<T> delegate;

    public LazyTransposedMatrix(final Matrix<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public int width() {
        return delegate.height();
    }

    @Override
    public int height() {
        return delegate.width();
    }

    @Override
    public T get(final int x, final int y) {
        return delegate.get(y, x);
    }

    @Override
    public Matrix<T> transpose() {
        return delegate;
    }

}
