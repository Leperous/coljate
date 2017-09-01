package net.coljate.set.impl;

import org.eclipse.collections.impl.set.mutable.UnifiedSet;

/**
 *
 * @author ollie
 */
public class MutableEclipseUnifiedSet<T> extends MutableWrappedSet<T> {

    private final UnifiedSet<T> delegate;

    public MutableEclipseUnifiedSet() {
        this(new UnifiedSet<>());
    }

    public MutableEclipseUnifiedSet(final UnifiedSet<T> delegate) {
        super(delegate);
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

    @Override
    public ImmutableEclipseUnifiedSet<T> immutableCopy() {
        return new ImmutableEclipseUnifiedSet<>(delegate);
    }

}
