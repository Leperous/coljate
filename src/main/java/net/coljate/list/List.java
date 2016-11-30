package net.coljate.list;

import net.coljate.feature.Ordered;
import net.coljate.list.impl.ImmutableNativeArray;
import net.coljate.list.impl.MutableWrappedList;
import net.coljate.util.Equality;

/**
 *
 * @author ollie
 */
public interface List<T> extends Ordered<T> {

    @Override
    ListIterator<T> iterator();

    @Override
    default T first() {
        return this.iterator().next();
    }
    
    @Override
    default MutableList<T> mutableCopy() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    default ImmutableList<T> immutableCopy() {
        return ImmutableNativeArray.copyOf(this);
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
        return MutableWrappedList.viewOf(list);
    }

}
