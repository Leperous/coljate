package net.coljate.table.lazy;

import java.util.function.Function;

import net.coljate.table.Matrix;

/**
 *
 * @author ollie
 */
public class LazyTransformedMatrix<F, T>
        implements LazyMatrix<T> {

    private final Matrix<? extends F> delegate;
    private final Function<? super F, ? extends T> transformation;

    public LazyTransformedMatrix(
            final Matrix<? extends F> delegate,
            final Function<? super F, ? extends T> transformation) {
        this.delegate = delegate;
        this.transformation = transformation;
    }

    @Override
    public int width() {
        return delegate.width();
    }

    @Override
    public int height() {
        return delegate.height();
    }

    @Override
    public T get(int x, int y) {
        return transformation.apply(delegate.get(x, y));
    }

}
