package net.coljate.list;

import java.util.Objects;

import net.coljate.list.impl.MutableNativeArray;

/**
 *
 * @author ollie
 */
public interface MutableArray<T> extends Array<T>, MutableList<T> {

    T set(int i, T value);

    void resize(int length);

    default boolean replace(int i, T expectedValue, T replacementValue) {
        if (Objects.equals(this.get(i), expectedValue)) {
            this.set(i, replacementValue);
            return true;
        } else {
            return false;
        }
    }

    static <T> MutableArray<T> create(final int length) {
        return MutableNativeArray.create(length);
    }

    static <T> MutableArray<T> copyOf(final T[] elements) {
        return MutableNativeArray.copyOf(elements);
    }

    @SafeVarargs
    static <T> MutableArray<T> viewOf(final T... elements) {
        return MutableNativeArray.viewOf(elements);
    }

}
