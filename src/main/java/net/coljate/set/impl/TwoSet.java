package net.coljate.set.impl;

import java.util.Objects;

import net.coljate.set.AbstractSet;
import net.coljate.set.ImmutableSet;
import net.coljate.util.iterator.UnmodifiableIterator;

/**
 *
 * @author ollie
 */
public class TwoSet<T>
        extends AbstractSet<T>
        implements ImmutableSet<T> {

    public static <T> ImmutableSet<T> of(final T a, final T b) {
        return Objects.equals(a, b)
                ? SingletonSet.of(a)
                : new TwoSet<>(a, b);
    }

    private final T a, b;

    protected TwoSet(final T a, final T b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean contains(final Object object) {
        return Objects.equals(a, object) || Objects.equals(b, object);
    }

    @Override
    public UnmodifiableIterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
