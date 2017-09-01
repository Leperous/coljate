package net.coljate.set.impl;

import net.coljate.eclipse.EclipseRichIterableCollection;
import net.coljate.set.ImmutableSet;
import net.coljate.set.MutableSet;
import net.coljate.util.iterator.UnmodifiableIterator;

/**
 *
 * @author ollie
 */
public abstract class AbstractEclipseImmutableSet<T>
        extends EclipseRichIterableCollection<T>
        implements ImmutableSet<T> {

    protected AbstractEclipseImmutableSet(final org.eclipse.collections.api.set.ImmutableSet<T> set) {
        super(set);
    }

    @Override
    public UnmodifiableIterator<T> iterator() {
        return UnmodifiableIterator.wrap(super.iterator());
    }

    @Override
    public abstract MutableSet<T> mutableCopy();

    @Override
    public ImmutableSet<T> immutableCopy() {
        return this;
    }

}
