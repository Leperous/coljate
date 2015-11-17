package net.ollie.coljate.sets;


import java.util.Set;

import net.ollie.coljate.sets.mixin.GenericMutableWrappedSet;

/**
 *
 * @author Ollie
 */
public class MutableWrappedSet<T> extends WrappedSet<T> implements GenericMutableWrappedSet<T> {

    public MutableWrappedSet(final java.util.Set<T> delegate) {
        super(delegate);
    }

    @Override
    public Set<T> delegate() {
        return delegate;
    }

}
