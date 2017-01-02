package net.coljate.list.impl;

import java.util.Objects;

import net.coljate.list.ImmutableList;
import net.coljate.list.ImmutableListIterator;

/**
 *
 * @author ollie
 */
public class ImmutableJoinList<T> implements ImmutableList<T> {

    @SuppressWarnings("unchecked")
    public static <T> ImmutableList<T> of(
            final ImmutableList<? extends T> left,
            final ImmutableList<? extends T> right) {
        if (right.isEmpty()) {
            return (ImmutableList<T>) left;
        } else if (left.isEmpty()) {
            return (ImmutableList<T>) right;
        } else {
            return new ImmutableJoinList<>(left, right);
        }
    }

    private final ImmutableList<? extends T> left, right;

    protected ImmutableJoinList(
            final ImmutableList<? extends T> left,
            final ImmutableList<? extends T> right) {
        this.left = Objects.requireNonNull(left);
        this.right = Objects.requireNonNull(right);
    }

    @Override
    public ImmutableListIterator<T> iterator() {
        return new JoinListIterator();
    }

    @Override
    public T first() {
        return left.first();
    }

    @Override
    public T last() {
        return right.last();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()
                + ": [" + left
                + " union " + right
                + ']';
    }

    private final class JoinListIterator implements ImmutableListIterator<T> {

        private ImmutableListIterator<? extends T> l, r;
        private ImmutableListIterator<? extends T> current;

        ImmutableListIterator<? extends T> current() {
            if (current == null) {
                l = left.iterator();
                current = l;
            }
            if (current == l && !current.hasNext()) {
                r = right.iterator();
                current = r;
            }
            return current;
        }

        @Override
        public boolean hasPrevious() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public T previous() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean hasNext() {
            return this.current().hasNext();
        }

        @Override
        public T next() {
            return this.current().next();
        }

    }

}
