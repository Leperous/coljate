package net.coljate.list.lazy;

import java.util.function.IntFunction;

import net.coljate.util.Suppliers;

/**
 *
 * @author ollie
 */
public class LazyIndexedArray<T>
        implements LazyArray<T> {

    private static final Object COMPUTED_NULL = new Object();

    public static <T> LazyIndexedArray<T> create(final int length, final IntFunction<? extends T> function) {
        return new LazyIndexedArray<>(function, new Object[length]);
    }

    private final IntFunction<? extends T> supplier;
    private final Object[] store;

    protected LazyIndexedArray(final IntFunction<? extends T> supplier, final Object[] array) {
        this.supplier = supplier;
        this.store = array;
    }

    @Override
    public int length() {
        return store.length;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(final int index) {
        Object got = store[index];
        if (got == null) {
            got = Suppliers.firstNonNull(supplier.apply(index), COMPUTED_NULL);
            store[index] = got;
        }
        return got == COMPUTED_NULL ? null : (T) got;
    }

}
