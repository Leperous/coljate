package net.coljate.set.impl;

import org.eclipse.collections.impl.set.mutable.UnifiedSet;

/**
 *
 * @author ollie
 */
public class EclipseUnifiedSet<T> extends MutableWrappedSet<T> {

    private final UnifiedSet<T> delegate;

    public EclipseUnifiedSet() {
        this(new UnifiedSet<>());
    }

    public EclipseUnifiedSet(final UnifiedSet<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public UnifiedSet<T> mutableJavaCopy() {
        return new UnifiedSet<>(delegate);
    }

    @Override
    public EclipseUnifiedSet<T> mutableCopy() {
        return new EclipseUnifiedSet<>(new UnifiedSet<>(delegate));
    }

    @Override
    public EclipseImmutableSet<T> immutableCopy() {
        return new EclipseImmutableSet<>(delegate.toImmutable());
    }

}
