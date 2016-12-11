package net.coljate.set.impl;

/**
 *
 * @author ollie
 */
public class MutableWrappedLinkedHashSet<T>
        extends MutableWrappedSet<T> {

    public static <T> MutableWrappedLinkedHashSet<T> create(final int initialCapacity) {
        return viewOf(new java.util.LinkedHashSet<>(initialCapacity));
    }

    public static <T> MutableWrappedLinkedHashSet<T> viewOf(final java.util.LinkedHashSet<T> set) {
        return new MutableWrappedLinkedHashSet<>(set);
    }

    protected MutableWrappedLinkedHashSet(final java.util.LinkedHashSet<T> delegate) {
        super(delegate);
    }

    @Override
    public MutableWrappedLinkedHashSet<T> mutableCopy() {
        return new MutableWrappedLinkedHashSet<>(this.mutableJavaCopy());
    }

    @Override
    public java.util.LinkedHashSet<T> mutableJavaCopy() {
        return this.mutableJavaCopy(java.util.LinkedHashSet::new);
    }

}
