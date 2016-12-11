package net.coljate.list;

import net.coljate.feature.Indexed;
import net.coljate.list.impl.MutableWrappedArrayList;
import net.coljate.list.impl.RepeatedValueArray;

/**
 *
 * @author ollie
 */
public interface Array<T> extends Indexed<T>, List<T> {

    /**
     * @return the length of this array. It will be equal to or greater than the {@link #count}.
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

    static <T> Array<T> repeated(final T value, final int count) {
        return new RepeatedValueArray<>(value, count);
    }

}
