package net.coljate.set.impl;

import java.io.Serializable;

/**
 *
 * @author ollie
 */
public class MutableWrappedHashSet<T>
        extends MutableWrappedSet<T>
        implements HashSet<T>, Serializable {

    public static final int DEFAULT_INITIAL_CAPACITY = 10;

    public static <T> MutableWrappedHashSet<T> create() {
        return create(DEFAULT_INITIAL_CAPACITY);
    }

    public static <T> MutableWrappedHashSet<T> create(final int initialCapacity) {
        return viewOf(new java.util.HashSet<>(initialCapacity));
    }

    @SafeVarargs
    public static <T> MutableWrappedHashSet<T> copyOf(final T... elements) {
        final java.util.HashSet<T> set = new java.util.HashSet<>(elements.length);
        for (int i = 0; i < elements.length; i++) {
            set.add(elements[i]);
        }
        return viewOf(set);
    }

    public static <T> MutableWrappedHashSet<T> viewOf(final java.util.HashSet<T> set) {
        return new MutableWrappedHashSet<>(set);
    }

    public static <T> MutableWrappedHashSet<T> copyOf(final java.util.Set<T> set) {
        return new MutableWrappedHashSet<>(new java.util.HashSet<>(set));
    }

    private static final long serialVersionUID = 1L;

    private final java.util.HashSet<T> delegate;

    protected MutableWrappedHashSet(final java.util.HashSet<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public java.util.HashSet<T> mutableJavaCopy() {
        return new java.util.HashSet<>(delegate);
    }

    @Override
    public MutableWrappedHashSet<T> mutableCopy() {
        return new MutableWrappedHashSet<>(this.mutableJavaCopy());
    }

}
