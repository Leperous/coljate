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

    @Override
    @Deprecated
    public default java.util.List<T> javaCollectionCopy() {
        return Collections.unmodifiableList(List.super.javaCollectionCopy());
    }

    interface ImmutableListIterator<T> extends ListIterator<T>, UnmodifiableIterator<T> {

    }

}
