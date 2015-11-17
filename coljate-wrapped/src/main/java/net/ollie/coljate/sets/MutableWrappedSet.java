package net.ollie.coljate.sets;

import net.ollie.coljate.sets.mixin.WrapsMutableSet;

/**
 *
 * @author Ollie
 */
public class MutableWrappedSet<T> extends WrappedSet<T> implements WrapsMutableSet<T> {

    public MutableWrappedSet(final java.util.Set<T> delegate) {
        super(delegate);
    }

    @Override
    public java.util.Set<T> delegate() {
        return super.delegate();
    }

}
