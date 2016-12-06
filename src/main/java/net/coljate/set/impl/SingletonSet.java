package net.coljate.set.impl;

import java.io.Serializable;

import net.coljate.collection.impl.SingletonCollection;
import net.coljate.set.ImmutableSet;
import net.coljate.set.MutableSet;

/**
 *
 * @author ollie
 */
public class SingletonSet<T>
        extends SingletonCollection<T>
        implements ImmutableSet<T>, Serializable {

    private static final long serialVersionUID = 1L;

    public static <T> SingletonSet<T> of(final T element) {
        return new SingletonSet<>(element);
    }

    protected SingletonSet(final T element) {
        super(element);
    }

    @Override
    public ImmutableSet<T> with(final T element) {
        return ImmutableSet.super.with(element);
    }

    @Override
    public MutableSet<T> mutableCopy() {
        return ImmutableSet.super.mutableCopy();
    }

    @Override
    @Deprecated
    public SingletonSet<T> immutableCopy() {
        return this;
    }

}
