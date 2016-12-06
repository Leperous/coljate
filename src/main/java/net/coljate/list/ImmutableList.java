package net.coljate.list;

import net.coljate.collection.ImmutableCollection;
import net.coljate.list.impl.ImmutableJoinList;
import net.coljate.list.impl.ImmutableWrappedList;

/**
 *
 * @author ollie
 */
public interface ImmutableList<T> extends List<T>, ImmutableCollection<T> {

    @Override
    ImmutableListIterator<T> iterator();

    @Override
    @Deprecated
    default ImmutableList<T> immutableCopy() {
        return this;
    }

    default ImmutableList<T> prefixed(final T element) {
        return this.prefixed(of(element));
    }

    default ImmutableList<T> prefixed(final List<T> list) {
        return join(list.immutableCopy(), this);
    }

    default ImmutableList<T> suffixed(final T element) {
        return this.suffixed(of(element));
    }

    default ImmutableList<T> suffixed(final List<T> list) {
        return join(this, list.immutableCopy());
    }

    @Override
    @Deprecated
    default ImmutableCollection<T> with(final T element) {
        return this.suffixed(element);
    }

    static <T> ImmutableList<T> of(final T element) {
        return ImmutableArray.of(element);
    }

    @SafeVarargs
    static <T> ImmutableList<T> copyOf(final T... elements) {
        return ImmutableArray.copyOf(elements);
    }

    static <T> ImmutableList<T> copyOf(final java.util.Collection<? extends T> collection) {
        return ImmutableWrappedList.copyOf(collection);
    }

    @SuppressWarnings("unchecked")
    static <T> ImmutableList<T> join(
            final ImmutableList<? extends T> left,
            final ImmutableList<? extends T> right) {
        return ImmutableJoinList.of(left, right);
    }

}
