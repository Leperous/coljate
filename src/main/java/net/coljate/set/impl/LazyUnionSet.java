package net.coljate.set.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import net.coljate.collection.LazyCollection;
import net.coljate.set.AbstractSet;
import net.coljate.set.Set;

/**
 * View of the union of two (possibly mutable) sets.
 *
 * @author ollie
 */
public class LazyUnionSet<T>
        extends AbstractSet<T>
        implements LazyCollection<T> {

    @SuppressWarnings("unchecked")
    public static <T> Set<T> viewOf(final Set<? extends T> s1, final Set<? extends T> s2) {
        if (s1 instanceof EmptySet) {
            return (Set<T>) s2;
        } else if (s2 instanceof EmptySet) {
            return (Set<T>) s1;
        } else {
            return new LazyUnionSet<>(s1, s2);
        }
    }

    private final Set<? extends T> s1, s2;

    protected LazyUnionSet(final Set<? extends T> s1, Set<? extends T> s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public boolean contains(final Object object) {
        return s1.contains(object) || s2.contains(object);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            boolean hasNext;
            T next;
            final Iterator<? extends T> i1 = s1.iterator();
            Iterator<? extends T> i2;

            @Override
            public boolean hasNext() {
                if (i1.hasNext()) {
                    next = i1.next();
                    hasNext = true;
                    return true;
                }
                hasNext = false;
                if (i2 == null) {
                    i2 = s2.iterator();
                }
                while (i2.hasNext()) {
                    final T n = i2.next();
                    if (!s1.contains(n)) {
                        hasNext = true;
                        next = n;
                    }
                }
                return hasNext;
            }

            @Override
            public T next() {
                if (!hasNext) {
                    throw new NoSuchElementException();
                }
                return next;
            }

        };
    }

    @Override
    protected boolean equals(final Set<?> that) {
        return that instanceof LazyUnionSet
                && this.equals((LazyUnionSet) that);
    }

    protected boolean equals(final LazyUnionSet<?> that) {
        return Objects.equals(s1, that.s1)
                && Objects.equals(s2, that.s2);
    }

}
