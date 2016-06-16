package net.ollie.coljate.set;

import net.ollie.coljate.set.mixin.WrapsMutableSet;

/**
 *
 * @author Ollie
 */
public class MutableWrappedSet<T>
        extends WrappedSet<T>
        implements WrapsMutableSet<T> {

    public MutableWrappedSet(final java.util.Set<T> delegate) {
        super(delegate);
    }

    @Override
    public java.util.Set<T> delegate() {
        return super.delegate();
    }

}
