//package net.ollie.coljate.lists;
//
//import java.util.Arrays;
//import java.util.Iterator;
//import java.util.Objects;
//
//import static net.ollie.coljate.utils.Assertions.checkNonNegative;
//
///**
// *
// * @author Ollie
// * @see java.util.ArrayList
// */
//public class MutableArrayList<T>
//        extends AbstractList<T>
//        implements MutableList<T> {
//
//    private static final Object[] EMPTY = new Object[0];
//
//    public static <T> MutableList<T> of() {
//        return new MutableArrayList<>(0);
//    }
//
//    @SafeVarargs
//    public static <T> MutableList<T> viewOf(final T... elements) {
//        return new MutableArrayList<>(elements);
//    }
//
//    @SafeVarargs
//    public static <T> MutableList<T> copyOf(final T... elements) {
//        return new MutableArrayList<>(Arrays.copyOf(elements, elements.length));
//    }
//
//    private Object[] array;
//    private int size;
//
//    public MutableArrayList(final int initialCapacity) {
//        checkNonNegative(initialCapacity);
//        this.array = initialCapacity == 0 ? EMPTY : new Object[initialCapacity];
//    }
//
//    public MutableArrayList(final T[] array) {
//        this.array = array;
//        this.size = array.length;
//    }
//
//    @SuppressWarnings("unchecked")
//    private T[] typedArray() {
//        return (T[]) array;
//    }
//
//    @Override
//    public int capacity() {
//        return array.length;
//    }
//
//    @Override
//    public void setCapacity(final int capacity) {
//        checkNonNegative(capacity);
//        if (capacity == this.capacity()) {
//            return;
//        }
//        array = capacity == 0
//                ? EMPTY
//                : Arrays.copyOf(array, capacity);
//        size = Math.min(size, capacity);
//    }
//
//    @Override
//    public int count() {
//        return size;
//    }
//
//    @Override
//    @SuppressWarnings("unchecked")
//    public T get(final int index) {
//        return (T) array[index];
//    }
//
//    @Override
//    public boolean add(final T element) {
//        this.ensureCapacity(size);
//        array[size++] = element;
//        return true;
//    }
//
//    @Override
//    public T set(final int index, final T element) {
//        final T previous = this.get(index);
//        array[index] = element;
//        return previous;
//    }
//
//    @Override
//    public boolean removeOnce(final Object element) {
//        for (int i = 0; i < array.length; i++) {
//            if (Objects.equals(array[i], element)) {
//                this.set(i, null);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public boolean removeAll(final Object element) {
//        boolean removed = false;
//        for (int i = 0; i < array.length; i++) {
//            if (Objects.equals(array[i], element)) {
//                this.set(i, null);
//                removed = true;
//            }
//        }
//        return removed;
//    }
//
//    @Override
//    public void clear() {
//        this.array = new Object[array.length];
//        this.size = 0;
//    }
//
//    @Override
//    public MutableList<T> tail() {
//        throw new UnsupportedOperationException(); //TODO
//    }
//
//    @Override
//    public MutableList<T> mutableCopy() {
//        return copyOf(this.typedArray());
//    }
//
//    @Override
//    public ImmutableList<T> immutableCopy() {
//        return ImmutableArrayList.of(this.typedArray());
//    }
//
//    @Override
//    public Iterator<T> iterator() {
//        return new Iterator<T>() {
//
//            int index = 0;
//
//            @Override
//            public boolean hasNext() {
//                return index < size;
//            }
//
//            @Override
//            public T next() {
//                return MutableArrayList.this.get(index++);
//            }
//
//            @Override
//            public void remove() {
//                array[index] = null;
//                size--;
//            }
//
//        };
//    }
//
//}
