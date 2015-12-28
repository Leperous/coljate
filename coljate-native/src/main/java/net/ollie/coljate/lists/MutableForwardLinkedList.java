package net.ollie.coljate.lists;

import java.util.Iterator;
import java.util.NoSuchElementException;
import static java.util.Objects.requireNonNull;

import net.ollie.coljate.utils.Iterators;

/**
 *
 * @author ollie
 */
public class MutableForwardLinkedList<T>
        extends AbstractList<T>
        implements MutableList<T> {

    @SuppressWarnings("unchecked")
    public static <T> MutableForwardLinkedList<T> empty() {
        return new MutableForwardLinkedList<>(RootNode.get());
    }

    @SafeVarargs
    public static <T> MutableForwardLinkedList<T> copyOf(final T... elements) {
        final MutableForwardLinkedList<T> list = empty();
        for (int i = elements.length - 1; i >= 0; i--) {
            list.prefix(elements[i]);
        }
        return list;
    }

    private Node<T> head;

    private MutableForwardLinkedList(final Node<T> head) {
        this.head = requireNonNull(head);
    }

    @Override
    public int count() {
        return head.count();
    }

    @Override
    public Iterator<T> iterator() {
        return head.iterator(this);
    }

    @Override
    public T get(final int index) {
        return head.descend(index).value();
    }

    @Override
    public void prefix(final T element) {
        head = head.prefix(element);
    }

    @Override
    public void suffix(final T element) {
        head = head.suffix(element);
    }

    @Override
    public T set(final int index, final T element) {
        return head.descend(index).set(element);
    }

    @Override
    public void clear() {
        head = RootNode.get();
    }

    @Override
    public MutableList<T> tail() {
        return new MutableForwardLinkedList<>(head.next());
    }

    @Override
    public ImmutableList<T> immutableCopy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MutableList<T> mutableCopy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private interface Node<T> {

        boolean isRoot();

        T value();

        int count();

        default Node<T> next() {
            return this.descend(1);
        }

        void deleteNext();

        Node<T> descend(int index);

        T set(T element);

        default Node<T> prefix(final T element) {
            return new ElementNode<>(element, this);
        }

        Node<T> suffix(T element);

        default Iterator<T> iterator(MutableForwardLinkedList<T> list) {
            return new Iterator<T>() {

                private Node<T> previousPrevious = null;
                private Node<T> previous = null;
                private Node<T> current = Node.this;

                @Override
                public boolean hasNext() {
                    return !current.isRoot();
                }

                @Override
                public T next() {
                    if (!this.hasNext()) {
                        throw new NoSuchElementException();
                    }
                    this.previousPrevious = this.previous;
                    this.previous = this.current;
                    this.current = current.next();
                    return previous.value();
                }

                @Override
                public void remove() {
                    if (previous == null) {
                        throw new IllegalStateException();
                    } else if (previousPrevious == null) {
                        list.head = current;
                    } else {
                        previousPrevious.deleteNext();
                    }
                }

            };
        }

    }

    private static final class RootNode<T> implements Node<T> {

        static final RootNode INSTANCE = new RootNode();

        @SuppressWarnings("unchecked")
        static <T> RootNode<T> get() {
            return INSTANCE;
        }

        @Override
        public Node<T> next() {
            return this;
        }

        @Override
        public boolean isRoot() {
            return true;
        }

        @Override
        public T value() {
            throw new IndexOutOfBoundsException();
        }

        @Override
        public int count() {
            return 0;
        }

        @Override
        public Node<T> descend(final int index) {
            if (index == 0) {
                return this;
            }
            throw new IndexOutOfBoundsException();
        }

        @Override
        public Iterator<T> iterator(final MutableForwardLinkedList<T> list) {
            return Iterators.none();
        }

        @Override
        public T set(final T element) {
            throw new IndexOutOfBoundsException();
        }

        @Override
        public Node<T> suffix(final T element) {
            return this.prefix(element);
        }

        @Override
        public void deleteNext() {
        }

    }

    private static final class ElementNode<T> implements Node<T> {

        private T element;
        private Node<T> next;

        ElementNode(final T element, final Node<T> next) {
            this.element = element;
            this.next = next;
        }

        @Override
        public boolean isRoot() {
            return false;
        }

        @Override
        public T value() {
            return element;
        }

        @Override
        public int count() {
            return 1 + next.count();
        }

        @Override
        public Node<T> descend(final int count) {
            return count == 0
                    ? this
                    : next.descend(count - 1);
        }

        @Override
        public T set(final T element) {
            final T was = this.element;
            this.element = element;
            return was;
        }

        @Override
        public Node<T> suffix(final T element) {
            next = next.suffix(element);
            return this;
        }

        @Override
        public void deleteNext() {
            next = next.next();
        }

    }

}
