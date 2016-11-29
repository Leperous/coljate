package net.coljate.list;

import java.util.Collections;

import net.coljate.collection.ImmutableCollection;
import net.coljate.UnmodifiableIterator;
import net.coljate.list.impl.ImmutableJoinList;
import net.coljate.list.impl.ImmutableSingletonList;
import net.coljate.list.impl.ImmutableWrappedList;

/**
 *
 * @author ollie
 */
public interface ImmutableList<T> extends List<T>, ImmutableCollection<T> {

    @Override
    ImmutableListIterator<T> iterator();

    @Override
    @Deprecated
    default ImmutableList<T> immutableCopy() {
        return this;
    }

    default ImmutableList<T> prefixed(final T element) {
        return this.prefixed(of(element));
    }

    default ImmutableList<T> prefixed(final List<T> list) {
        return join(list.immutableCopy(), this);
    }

    default ImmutableList<T> suffixed(final T element) {
        return this.suffixed(of(element));
    }

    default ImmutableList<T> suffixed(final List<T> list) {
        return join(this, list.immutableCopy());
    }

    @Override
    @Deprecated
    default ImmutableCollection<T> with(final T element) {
        return this.suffixed(element);
    }

    @Override
    @Deprecated
    default java.util.List<T> javaCollectionCopy() {
        return Collections.unmodifiableList(List.super.javaCollectionCopy());
    }

    static <T> ImmutableList<T> of(final T element) {
        return new ImmutableSingletonList<>(element);
    }

    static <T> ImmutableList<T> copyOf(final java.util.Collection<? extends T> collection) {
        return ImmutableWrappedList.copyOf(collection);
    }

    @SuppressWarnings("unchecked")
    static <T> ImmutableList<T> join(
            final ImmutableList<? extends T> left,
            final ImmutableList<? extends T> right) {
        return ImmutableJoinList.of(left, right);
    }

    interface ImmutableListIterator<T> extends ListIterator<T>, UnmodifiableIterator<T> {

        static <T> ImmutableListIterator<T> empty() {
            return new ImmutableListIterator<T>() {

                @Override
                public boolean hasPrevious() {
                    return false;
                }

                @Override
                public T previous() {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public boolean hasNext() {
                    return false;
                }

                @Override
                public T next() {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

            };
        }

    }

}
