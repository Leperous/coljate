package net.coljate.collection.lazy;

import net.coljate.collection.Collection;
import net.coljate.collection.ImmutableCollection;
import net.coljate.collection.MutableCollection;

/**
 *
 * @author ollie
 */
public interface LazyCollection<T> extends Collection<T> {

    @Override
    default MutableCollection<? extends T> mutableCopy() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    default ImmutableCollection<? extends T> immutableCopy() {
        throw new UnsupportedOperationException(); //TODO
    }

}
