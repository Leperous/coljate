package net.ollie.coljate.sets;

import java.util.Set;

import net.ollie.coljate.sets.mixin.MutableNativeSetMixin;

/**
 *
 * @author Ollie
 */
public class MutableNativeSet<T> extends NativeSet<T> implements MutableNativeSetMixin<T> {

    public MutableNativeSet(final java.util.Set<T> delegate) {
        super(delegate);
    }

    @Override
    public Set<T> delegate() {
        return delegate;
    }

}
