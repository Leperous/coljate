package net.coljate.list;

import java.util.Objects;

/**
 *
 * @author ollie
 */
public class ImmutableJoinList<T> implements ImmutableList<T> {

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

    private final class JoinListIterator implements ImmutableListIterator<T> {

        private boolean exhaustedLeft = false;
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
