package net.coljate.set.impl;

import java.io.Serializable;

import net.coljate.collection.Empty;
import net.coljate.set.AbstractSet;
import net.coljate.set.ImmutableSet;
import net.coljate.set.Set;

/**
 *
 * @author ollie
 */
public class EmptySet<T>
        extends AbstractSet<T>
        implements Empty<T>, ImmutableSet<T>, Serializable {

    private static final long serialVersionUID = 1L;
    private static final EmptySet INSTANCE = new EmptySet();

    @SuppressWarnings("unchecked")
    public static <T> EmptySet<T> instance() {
        return INSTANCE;
    }

    protected EmptySet() {
    }

    @Override
    public boolean contains(final Object object) {
        return Empty.super.contains(object);
    }

    @Override
    public boolean equals(final Set<?> that) {
        return that instanceof EmptySet;
    }

}
