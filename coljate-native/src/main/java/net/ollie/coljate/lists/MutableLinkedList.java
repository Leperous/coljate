package net.ollie.coljate.lists;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collector;

import net.ollie.coljate.streams.DefaultStream;
import net.ollie.coljate.utils.Arrays;

import javax.annotation.CheckForNull;

/**
 *
 * @author Ollie
 * @see java.util.LinkedList
 */
public class MutableLinkedList<V>
        extends List.Abstract<V>
        implements List.Mutable<V> {

    public static <V> MutableLinkedList<V> create() {
        return new MutableLinkedList<>();
    }

    @SafeVarargs
    public static <V> MutableLinkedList<V> create(final V... elements) {
        final MutableLinkedList<V> list = new MutableLinkedList<>();
        list.suffixAll(Arrays.asList(elements));
        return list;
    }

    public static <V> MutableLinkedList<V> create(final Iterator<? extends V> iterator) {
        final MutableLinkedList<V> list = new MutableLinkedList<>();
        while (iterator.hasNext()) {
            list.suffix(iterator.next());
        }
        return list;
    }

    public static <V> MutableLinkedList<V> copy(final Iterable<? extends V> iterable) {
        return create(iterable.iterator());
    }

    public static <V> Collector<V, ?, MutableLinkedList<V>> collector() {
        return MutableLinkedListCollector.INSTANCE;
    }

    @CheckForNull
    private Node<V> head;

    protected MutableLinkedList() {
    }

    @Override
    public V head() {
        return head == null ? null : head.value();
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean contains(final Object object) {
        return head != null && head.contains(object);
    }

    @Override
    public V last() {
        return head == null ? null : head.last();
    }

    @Override
    public List<V> tail() {
        return head == null ? null : create(new NodeIterator<>(head.next));
    }

    @Override
    public void prefix(final V value) {
        head = new Node<>(value, head);
    }

    @Override
    public void prefixAll(final Iterable<? extends V> values) {
        final MutableLinkedList<V> newList = MutableLinkedList.copy(values);
        newList.suffixAll(this);
        this.head = newList.head;
    }

    @Override
    public void suffix(final V value) {
        if (head == null) {
            head = new Node<>(value);
        } else {
            head.suffix(value);
        }
    }

    @Override
    public void clear() {
        head = null;
    }

    @Override
    public Iterator<V> iterator() {
        return new NodeIterator<>(head);
    }

    @Override
    public Stream<V, ? extends List<V>> stream() {
        return DefaultStream.create(this, MutableLinkedList::collector);
    }

    @Override
    public List.Mutable<V> reverseCopy() {
        final MutableLinkedList<V> newList = new MutableLinkedList<>();
        this.forEach(newList::prefix);
        return newList;
    }

    @Override
    public List.Mutable<V> mutableCopy() {
        return copy(this);
    }

    @Override
    public List.Immutable<V> immutableCopy() {
        return ImmutableWrappedList.copy(this);
    }

    private static final class Node<V> {

        private final V value;
        private Node<V> next;

        Node(final V value) {
            this(value, null);
        }

        Node(final V value, final Node<V> next) {
            this.value = value;
            this.next = next;
        }

        V value() {
            return value;
        }

        V last() {
            return next == null ? value : next.last();
        }

        boolean contains(final Object value) {
            return Objects.equals(this.value, value) || (next != null && next.contains(value));
        }

        Node<V> suffix(final V value) {
            if (next == null) {
                next = new Node<>(value);
                return this;
            } else {
                return next.suffix(value);
            }
        }

        void delete() {
            if (next != null) {
                next = next.next;
            }
        }

    }

    private static class NodeIterator<V> implements Iterator<V> {

        private Node<V> next;
        private Node<V> previous = null;

        NodeIterator(final Node<V> next) {
            this.next = next;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public V next() {
            if (next == null) {
                throw new NoSuchElementException();
            }
            previous = next;
            next = next.next;
            return previous.value();
        }

        @Override
        public void remove() {
            previous.delete();
        }

    }

    private static final class MutableLinkedListCollector<V>
            extends AbstractMutableListCollector<V, MutableLinkedList<V>> {

        static final MutableLinkedListCollector INSTANCE = new MutableLinkedListCollector();

        @Override
        public Supplier<MutableLinkedList<V>> supplier() {
            return MutableLinkedList::create;
        }

    }

}
