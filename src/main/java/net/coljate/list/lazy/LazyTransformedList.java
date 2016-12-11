package net.coljate.list.lazy;

import java.util.function.Function;

import net.coljate.collection.Collection;
import net.coljate.collection.lazy.LazyTransformedCollection;
import net.coljate.list.List;
import net.coljate.list.ListIterator;

/**
 *
 * @author Ollie
 */
public class LazyTransformedList<F, T>
        extends LazyTransformedCollection<F, T>
        implements LazyList<T> {

    public static <F, T> Function<Collection<F>, ? extends LazyTransformedList<F, T>> transform(final Function<? super F, ? extends T> transformation) {
        return (collection) -> {
            final List<F> list = List.copyOrCast(collection);
            return new LazyTransformedList<>(list, transformation);
        };
    }

    private final List<F> delegate;

    public LazyTransformedList(final List<F> delegate, final Function<? super F, ? extends T> transformation) {
        super(delegate, transformation);
        this.delegate = delegate;
    }

    @Override
    public T first() {
        return this.transform(delegate.first());
    }

    @Override
    public T last() {
        return this.transform(delegate.last());
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
            return transform(iterator.previous());
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public T next() {
            return transform(iterator.next());
        }

    }

}
