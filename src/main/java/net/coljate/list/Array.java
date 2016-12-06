package net.coljate.list;

import net.coljate.feature.Indexed;
import net.coljate.list.impl.MutableWrappedArrayList;

/**
 *
 * @author ollie
 */
public interface Array<T> extends Indexed<T>, List<T> {

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
    default java.util.ArrayList<T> mutableJavaCopy() {
        return this.mutableJavaCopy(java.util.ArrayList::new);
    }

    @Override
    default boolean contains(final Object object) {
        return List.super.contains(object);
    }

    @Override
    default MutableArray<T> mutableCopy() {
        return MutableWrappedArrayList.viewOf(this.mutableJavaCopy());
    }

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
