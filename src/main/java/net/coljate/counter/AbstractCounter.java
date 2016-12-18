package net.coljate.counter;

import net.coljate.collection.AbstractCollection;
import net.coljate.util.Equality;

/**
 *
 * @author ollie
 */
public abstract class AbstractCounter<T>
        extends AbstractCollection<T> {

    @Override
    protected boolean equals(AbstractCollection<?> that) {
        return that instanceof AbstractCounter
                && this.equals((AbstractCounter<?>) that);
    }

    protected boolean equals(final AbstractCounter<?> that) {
        return this.getClass() == that.getClass()
                && Equality.unorderedEquals(this, that);
    }

}
