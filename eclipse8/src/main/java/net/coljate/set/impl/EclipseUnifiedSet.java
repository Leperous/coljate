package net.coljate.set.impl;

import org.eclipse.collections.impl.set.mutable.UnifiedSet;

/**
 *
 * @author ollie
 */
public class EclipseUnifiedSet<T> extends WrappedSet<T> {

    public EclipseUnifiedSet() {
        this(new UnifiedSet<>());
    }

    public EclipseUnifiedSet(final UnifiedSet<? extends T> delegate) {
        super(delegate);
    }

}
