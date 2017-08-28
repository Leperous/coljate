package net.coljate.collection.primitive;

import java.util.Iterator;

import net.coljate.collection.Collection;

/**
 *
 * @author Ollie
 */
public interface IntegerCollection extends IntegerContainer, Collection<Integer> {

    @Override
    default boolean contains(final int i) {
        for (final IntegerIterator iterator = this.iterator(); iterator.hasNext();) {
            if (iterator.nextInt() == i) {
                return true;
            }
        }
        return false;
    }

    @Override
    default boolean contains(final Object object) {
        return IntegerContainer.super.contains(object);
    }

    @Override
    IntegerIterator iterator();

    interface IntegerIterator extends Iterator<Integer> {

        int nextInt();

        @Override
        @Deprecated
        default Integer next() {
            return this.nextInt();
        }

    }

}
