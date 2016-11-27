package net.coljate.list;

import java.util.Collections;

import net.coljate.ImmutableCollection;
import net.coljate.UnmodifiableIterator;

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

    ImmutableList<T> prefixed(T element);

    ImmutableList<T> suffixed(T element);

    @Override
    @Deprecated
    default ImmutableCollection<T> with(final T element) {
        return this.suffixed(element);
    }

    @Override
    @Deprecated
    public default java.util.List<T> javaCollectionCopy() {
        return Collections.unmodifiableList(List.super.javaCollectionCopy());
    }

    interface ImmutableListIterator<T> extends ListIterator<T>, UnmodifiableIterator<T> {

    }

}
