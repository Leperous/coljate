package net.coljate.counter;

import net.coljate.collection.AbstractCollection;

/**
 *
 * @author ollie
 */
public abstract class AbstractCounter<T>
        extends AbstractCollection<T> {

    @Override
    protected boolean equals(AbstractCollection<?> that) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
