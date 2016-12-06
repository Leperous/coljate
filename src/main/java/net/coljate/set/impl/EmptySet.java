package net.coljate.set.impl;

import net.coljate.UnmodifiableIterator;
import net.coljate.set.AbstractSet;
import net.coljate.set.ImmutableSet;

/**
 *
 * @author ollie
 */
public class EmptySet<T>
        extends AbstractSet<T>
        implements ImmutableSet<T> {

    private static final EmptySet INSTANCE = new EmptySet();

    @SuppressWarnings("unchecked")
    public static <T> EmptySet<T> instance() {
        return INSTANCE;
    }

    protected EmptySet() {
    }

    @Override
    public boolean contains(final Object object) {
        return false;
    }

    @Override
    public UnmodifiableIterator<T> iterator() {
        return UnmodifiableIterator.empty();
    }

}
