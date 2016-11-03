package net.coljate.list;

import java.util.Collections;

import net.coljate.ImmutableCollection;

/**
 *
 * @author ollie
 */
public interface ImmutableList<T> extends List<T>, ImmutableCollection<T> {

    @Override
    @Deprecated
    default ImmutableList<T> immutableCopy() {
        return this;
    }

    @Override
    public default java.util.List<T> javaCollectionCopy() {
        return Collections.unmodifiableList(List.super.javaCollectionCopy());
    }

}
