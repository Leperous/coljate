package net.coljate.set.impl;

import org.eclipse.collections.impl.set.mutable.UnifiedSet;

/**
 *
 * @author ollie
 */
public class ImmutableEclipseUnifiedSet<T> extends AbstractEclipseImmutableSet<T> {

    private final UnifiedSet<T> delegate;

    protected ImmutableEclipseUnifiedSet(final UnifiedSet<T> delegate) {
        super(delegate.toImmutable());
        this.delegate = delegate;
    }

    @Override
    public UnifiedSet<T> mutableJavaCopy() {
        return new UnifiedSet<>(delegate);
    }

    @Override
    public MutableEclipseUnifiedSet<T> mutableCopy() {
        return new MutableEclipseUnifiedSet<>(new UnifiedSet<>(delegate));
    }

}
