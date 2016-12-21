package net.coljate.table.lazy;

import java.util.function.BiFunction;
import java.util.function.Function;

import net.coljate.collection.Collection;
import net.coljate.list.MutableArray;
import net.coljate.table.Matrix;

/**
 *
 * @author ollie
 */
public class LazyProductMatrix<A, B, P, T>
        implements LazyMatrix<T> {

    private final Matrix<A> a;
    private final Matrix<B> b;
    private final BiFunction<? super A, ? super B, ? extends P> product;
    private final Function<? super Collection<? extends P>, ? extends T> collapse;

    public LazyProductMatrix(
            final Matrix<A> a,
            final Matrix<B> b,
            final BiFunction<? super A, ? super B, ? extends P> product,
            final Function<? super Collection<? extends P>, ? extends T> collapse) {
        this.a = a;
        this.b = b;
        this.product = product;
        this.collapse = collapse;
    }

    @Override
    public int width() {
        return b.width();
    }

    @Override
    public int height() {
        return a.height();
    }

    public int commonSize() {
        return a.width();
    }

    @Override
    public T get(final int x, final int y) {
        final MutableArray<P> list = MutableArray.create(this.commonSize());
        for (int r = 0; r < this.width(); r++) {
            final P product = this.product.apply(a.get(x, r), b.get(r, y));
            list.suffix(product);
        }
        return collapse.apply(list);
    }

}
