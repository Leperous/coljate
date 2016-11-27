package net.coljate.list;

import net.coljate.Collection;
import net.coljate.utils.Equality;

/**
 *
 * @author ollie
 */
public interface List<T> extends Collection<T> {

    @Override
    ListIterator<T> iterator();

    @Override
    default MutableList<T> mutableCopy() {
        throw new UnsupportedOperationException(); //TODO default
    }

    @Override
    default ImmutableList<T> immutableCopy() {
        throw new UnsupportedOperationException(); //TODO default
    }

    @Override
    @Deprecated
    default java.util.List<T> javaCollectionCopy() {
        final java.util.List<T> list = new java.util.ArrayList<>(this.count());
        this.forEach(list::add);
        return list;
    }

    default boolean equals(final List<?> that) {
        return Equality.orderedEquals(this, that);
    }

    static <T> MutableWrappedList<T> viewOf(final java.util.List<T> list) {
        return new MutableWrappedList<>(list);
    }

    static <T> MutableWrappedArrayList<T> viewOf(final java.util.ArrayList<T> list) {
        return new MutableWrappedArrayList<>(list);
    }

}
