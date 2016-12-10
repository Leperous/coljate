package net.coljate.list.impl;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Predicate;

import net.coljate.list.AbstractList;
import net.coljate.list.ListIterator;
import net.coljate.list.MutableList;
import net.coljate.util.Arrays;
import net.coljate.util.Functions;

/**
 *
 * @author Ollie
 */
public class MutableLinkedList<T>
        extends AbstractList<T>
        implements MutableList<T> {

    @SafeVarargs
    public static <T> MutableLinkedList<T> copyOf(final T... elements) {
        final MutableLinkedList<T> list = new MutableLinkedList<>();
        Arrays.consume(elements, list::suffix);
        return list;
    }

    private Node first, last;

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public T first() {
        return Functions.ifNonNull(first, Node::value);
    }

    @Override
    public T last() {
        return Functions.ifNonNull(last, Node::value);
    }

    @Override
    public void prefix(final T element) {
        first = new Node(element).insertBefore(first);
    }

    @Override
    public void suffix(final T element) {
        last = new Node(element).insertAfter(last);
    }

    @Override
    public void clear() {
        first = last = null;
    }

    @Override
    public boolean removeFirst(final Object element) {
        return this.anyNode(node -> node.removeIfHasValue(element));
    }

    @Override
    public boolean removeAll(final Object element) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public ListIterator<T> iterator() {
        return new LinkedListIterator();
    }

    private boolean anyNode(final Predicate<Node> perNode) {
        boolean any = false;
        Node node = first;
        while (node != null) {
            any &= perNode.test(node);
            node = node.next;
        }
        return any;
    }

    private final class Node {

        private Node prior, next;
        private T value;

        Node(final T value) {
            this.value = value;
        }

        T value() {
            return value;
        }

        Node insertBefore(final Node that) {
            if (that == null) {
                last = this;
            } else {
                final Node lastPrior = that.prior;
                if (lastPrior != null) {
                    lastPrior.setNext(this);
                }
                this.setNext(that);
            }
            return this;
        }

        Node insertAfter(final Node that) {
            if (that == null) {
                first = this;
            } else {
                final Node lastNext = that.next;
                that.setNext(this);
                this.setNext(lastNext);
            }
            return this;
        }

        void setNext(final Node that) {
            if (that != null) {
                this.next = that;
                that.prior = this;
            }
        }

        boolean removeIfHasValue(final Object value) {
            if (!Objects.equals(this.value, value)) {
                return false;
            }
            if (prior == null) {
                first = next;
            } else {
                prior.setNext(next);
            }
            return true;
        }

    }

    private final class LinkedListIterator implements ListIterator<T> {

        private Node current = first;

        @Override
        public boolean hasPrevious() {
            return current != null && current.prior != null;
        }

        @Override
        public T previous() {
            throw new UnsupportedOperationException(); //TODO
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            final T next = current.value;
            current = current.next;
            return next;
        }

    }

}
