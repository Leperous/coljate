package net.ollie.coljate.lists;

import java.util.Arrays;

import net.ollie.coljate.UnmodifiableIterator;
import net.ollie.coljate.utils.UnmodifiableArrayIterator;

/**
 *
 * @author Ollie
 * @see java.util.ArrayList
 */
public class ImmutableArrayList<T>
        extends AbstractList<T>
        implements ImmutableArray<T> {

    public static <T> ImmutableArray<T> of() {
        return ImmutableEmptyArray.get();
    }

    public static <T> ImmutableArray<T> of(final T element) {
        return new ImmutableArrayList<>(new Object[]{element});
    }

    @SafeVarargs
    public static <T> ImmutableArray<T> of(final T... elements) {
        switch (elements.length) {
            case 0:
                return of();
            default:
                return new ImmutableArrayList<>(elements);
        }
    }

    private final Object[] array;

    ImmutableArrayList(final Object[] array) {
        this.array = array;
    }

    @Override
    public int capacity() {
        return array.length;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(final int index) {
        return (T) array[index];
    }

    @Override
    public int count() {
        return array.length;
    }

    @Override
    public boolean isEmpty() {
        return array.length == 0;
    }

    @Override
    public ImmutableList<T> prefixed(final T element) {
        final Object[] newArray = new Object[array.length + 1];
        newArray[0] = element;
        System.arraycopy(array, 0, newArray, 1, array.length);
        return new ImmutableArrayList<>(newArray);
    }

    @Override
    public ImmutableList<T> suffixed(final T element) {
        final Object[] newArray = new Object[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = element;
        return new ImmutableArrayList<>(newArray);
    }

    @Override
    public MutableArray<T> mutableCopy() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public ImmutableArray<T> tail() {
        return of((T[]) Arrays.copyOfRange(array, 1, array.length));
    }

    @Override
    public UnmodifiableIterator<T> iterator() {
        return new UnmodifiableArrayIterator<>((T[]) array);
    }

}
