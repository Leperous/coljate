package net.coljate.list;

import net.coljate.feature.FastGet;

/**
 *
 * @author ollie
 */
public interface Array<T> extends List<T>, FastGet<Integer, T> {

    /**
     *
     * @param index
     * @return
     */
    T get(int index);

    @Override
    default T get(final Integer i) {
        return this.get(i.intValue());
    }

    /**
     * @return the length of this array. It will be equal to or greater than the
     * {@link #count}.
     */
    int length();

    @Override
    default T last() {
        return this.get(this.count());
    }

    @Override
    default boolean isEmpty() {
        return this.count() == 0;
    }

    @Override
    default ListIterator<T> iterator() {
        return new ArrayIterator<>(this);
    }

    @Override
    default boolean equals(final List<?> list) {
        return list instanceof Array
                && this.equals((Array) list);
    }

    default boolean equals(final Array<?> array) {
        return List.super.equals(array);
    }

    @Override
    MutableArray<T> mutableCopy();

    @Override
    ImmutableArray<T> immutableCopy();

    class ArrayIterator<T> implements ListIterator<T> {

        final Array<? extends T> array;
        int index = 0;

        protected ArrayIterator(Array<? extends T> array) {
            this.array = array;
        }

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public T previous() {
            return array.get(--index);
        }

        @Override
        public boolean hasNext() {
            return index < array.count();
        }

        @Override
        public T next() {
            return array.get(index++);
        }

    }

}
