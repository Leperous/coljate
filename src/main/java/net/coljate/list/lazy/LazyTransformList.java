package net.coljate.list.lazy;

import java.util.function.Function;

import net.coljate.collection.Collection;
import net.coljate.list.AbstractList;
import net.coljate.list.List;
import net.coljate.list.ListIterator;

/**
 *
 * @author Ollie
 */
public class LazyTransformList<F, T>
        extends AbstractList<T>
        implements LazyList<T> {

    public static <F, T> Function<Collection<F>, LazyTransformList<F, T>> transform(final Function<? super F, ? extends T> transform) {
        return (collection) -> {
            final List<F> list = List.copyOrCast(collection);
            return of(list, transform);
        };
    }

    static <F, T> LazyTransformList<F, T> of(final List<F> list, final Function<? super F, ? extends T> transform) {
        return new LazyTransformList<>(list, transform);
    }

    private final List<F> delegate;
    private final Function<? super F, ? extends T> transform;

    public LazyTransformList(final List<F> delegate, final Function<? super F, ? extends T> transform) {
        this.delegate = delegate;
        this.transform = transform;
    }

    @Override
    public T first() {
        return transform.apply(delegate.first());
    }

    @Override
    public T last() {
        return transform.apply(delegate.last());
    }

    @Override
    public ListIterator<T> iterator() {
        return new TransformedListIterator();
    }

    private final class TransformedListIterator implements ListIterator<T> {

        final ListIterator<F> iterator = delegate.iterator();

        @Override
        public boolean hasPrevious() {
            return iterator.hasPrevious();
        }

        @Override
        public T previous() {
            return transform.apply(iterator.previous());
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public T next() {
            return transform.apply(iterator.next());
        }

    }

}
