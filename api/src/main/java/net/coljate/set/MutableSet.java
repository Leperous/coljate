package net.coljate.set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.coljate.collection.MutableCollection;
import net.coljate.set.impl.MutableWrappedHashSet;
import net.coljate.set.impl.MutableWrappedSet;

/**
 *
 * @author ollie
 */
public interface MutableSet<T> extends Set<T>, MutableCollection<T> {

    /**
     *
     * @param element
     * @return true if the element was added because it was not previously contained by this set.
     */
    boolean add(T element);

    /**
     *
     * @param element
     * @return true if the given element was removed.
     */
    boolean remove(@Nullable Object element);

    /**
     *
     * @param elements
     * @return true if any element was added.
     */
    default boolean addAll(@Nonnull final Iterable<? extends T> elements) {
        boolean addedAny = false;
        for (final T element : elements) {
            addedAny |= this.add(element);
        }
        return addedAny;
    }

    @Deprecated
    default boolean removeFirst(@Nullable final Object element) {
        return this.remove(element);
    }

    @Override
    @Deprecated
    default boolean removeAll(@Nullable final Object element) {
        return this.remove(element);
    }

    static <T> MutableSet<T> viewOf(@Nonnull final java.util.Set<T> set) {
        return new MutableWrappedSet<>(set);
    }

    static <T> MutableSet<T> createHashSet() {
        return MutableWrappedHashSet.create();
    }

    static <T> MutableSet<T> createHashSet(final int initialCapacity) {
        return MutableWrappedHashSet.create(initialCapacity);
    }

}
