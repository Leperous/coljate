package net.coljate.set.lazy;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;

import net.coljate.collection.Collection;
import net.coljate.set.AbstractSet;
import net.coljate.set.Set;
import net.coljate.set.impl.EmptySet;

/**
 *
 * @author Ollie
 */
public class LazyIntersectionSet<T>
        extends AbstractSet<T>
        implements LazySet<T> {

    public static <T> LazySet<T> of(final Set<? extends T> s1, final Set<? extends T> s2) {
        if (s1 == s2 || s1 instanceof EmptySet) {
            return new FakeLazySet<>(s1);
        } else if (s2 instanceof EmptySet) {
            return new FakeLazySet<>(s2);
        } else {
            return new LazyIntersectionSet<>(s1, s2);
        }
    }

    private final Set<? extends T> s1, s2;

    protected LazyIntersectionSet(final Set<? extends T> s1, final Set<? extends T> s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public boolean contains(final Object object) {
        return s1.contains(object) && s2.contains(object);
    }

    @Override
    public Iterator<T> iterator() {
        return new IntersectionIterator();
    }

    @Override
    protected boolean equals(final Set<?> that) {
        throw new UnsupportedOperationException(); //TODO
    }

    private final class IntersectionIterator implements Iterator<T> {

        private final Iterator<? extends T> iterator = s1.iterator();
        private boolean needsNext = true;
        private T next;

        @Override
        public boolean hasNext() {
            if (!needsNext) {
                return true;
            }
            while (iterator.hasNext()) {
                final T next = iterator.next();
                if (s2.contains(next)) {
                    this.next = next;
                    needsNext = false;
                    return true;
                }
            }
            return false;
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            needsNext = true;
            return next;
        }

    }

}
