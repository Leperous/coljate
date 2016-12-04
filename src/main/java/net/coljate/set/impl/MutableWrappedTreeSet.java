package net.coljate.set.impl;

import java.util.Comparator;

import net.coljate.set.SortedSet;
import net.coljate.util.Arrays;
import net.coljate.util.Suppliers;

/**
 *
 * @author ollie
 */
public class MutableWrappedTreeSet<T>
        extends MutableWrappedSet<T>
        implements SortedSet<T> {

    public static <T> MutableWrappedTreeSet<T> viewOf(final java.util.TreeSet<T> set) {
        return new MutableWrappedTreeSet<>(set);
    }

    public static <T> MutableWrappedTreeSet<T> copyOf(final java.util.SortedSet<T> set) {
        return new MutableWrappedTreeSet<>(new java.util.TreeSet<>(set));
    }

    @SafeVarargs
    public static <T> MutableWrappedTreeSet<T> copyOf(final Comparator<? super T> comparator, final T... elements) {
        final java.util.TreeSet<T> set = new java.util.TreeSet<>(comparator);
        Arrays.copyInto(elements, set::add);
        return viewOf(set);
    }

    @SafeVarargs
    public static <T extends Comparable<? super T>> MutableWrappedTreeSet<T> copyOf(final T... elements) {
        final java.util.TreeSet<T> set = new java.util.TreeSet<>();
        Arrays.copyInto(elements, set::add);
        return viewOf(set);
    }

    private final java.util.TreeSet<T> delegate;

    protected MutableWrappedTreeSet(final java.util.TreeSet<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public java.util.TreeSet<T> mutableJavaCopy() {
        return new java.util.TreeSet<>(delegate);
    }

    @Override
    public Comparator<? super T> comparator() {
        return Suppliers.firstNonNull(delegate.comparator(), (Comparator) Comparator.naturalOrder());
    }

    @Override
    public T first() {
        return delegate.first();
    }

    @Override
    public T last() {
        return delegate.last();
    }

    @Override
    public MutableWrappedTreeSet<T> mutableCopy() {
        return new MutableWrappedTreeSet<>(this.mutableJavaCopy());
    }

}
