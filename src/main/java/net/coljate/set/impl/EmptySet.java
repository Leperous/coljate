package net.coljate.set.impl;

import net.coljate.UnmodifiableIterator;
import net.coljate.set.ImmutableSet;

/**
 *
 * @author ollie
 */
public class EmptySet<T> implements ImmutableSet<T> {

    @Override
    public UnmodifiableIterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
