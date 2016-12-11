package net.coljate.set.impl;

import java.util.Iterator;
import java.util.Objects;

import net.coljate.set.AbstractSet;
import net.coljate.set.Set;

/**
 *
 * @author Ollie
 */
public class DelegatedSet<T> extends AbstractSet<T> {

    private final Set<? extends T> delegate;

    protected DelegatedSet(final Set<? extends T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean contains(final Object object) {
        return delegate.contains(object);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterator<T> iterator() {
        return (Iterator<T>) delegate.iterator();
    }

    @Override
    protected boolean equals(final Set<?> that) {
        return that instanceof DelegatedSet
                && Objects.equals(delegate, ((DelegatedSet) that).delegate);
    }

}
