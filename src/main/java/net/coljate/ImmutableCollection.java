package net.coljate;

import java.util.Collections;

/**
 *
 * @author ollie
 */
public interface ImmutableCollection<T> extends Collection<T> {

    @Deprecated
    @Override
    default ImmutableCollection<T> immutableCopy() {
        return this;
    }

    @Override
    default java.util.Collection<T> javaCollectionCopy() {
        return Collections.unmodifiableCollection(Collection.super.javaCollectionCopy());
    }

}
