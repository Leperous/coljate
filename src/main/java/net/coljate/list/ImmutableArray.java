package net.coljate.list;

import net.coljate.list.impl.EmptyArray;
import net.coljate.list.impl.ImmutableNativeArray;
import net.coljate.list.impl.SingletonArray;

/**
 *
 * @author ollie
 */
public interface ImmutableArray<T>
        extends Array<T>, ImmutableList<T> {

    @Override
    default ImmutableListIterator<T> iterator() {
        return new ImmutableArrayIterator<>(this);
    }

    @Override
    @Deprecated
    default ImmutableArray<T> immutableCopy() {
        return this;
    }

    static <T> ImmutableArray<T> of() {
        return EmptyArray.instance();
    }

    static <T> ImmutableArray<T> of(final T element) {
        return SingletonArray.of(element);
    }

    @SafeVarargs
    static <T> ImmutableArray<T> copyOf(final T... elements) {
        switch (elements.length) {
            case 0:
                return EmptyArray.instance();
            case 1:
                return of(elements[0]);
            default:
                return ImmutableNativeArray.copyOf(elements);
        }
    }

    static class ImmutableArrayIterator<T> extends ArrayIterator<T> implements ImmutableListIterator<T> {

        public ImmutableArrayIterator(Array<? extends T> array) {
            super(array);
        }

    }

}
