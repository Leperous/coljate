package net.coljate.list.impl;

import java.util.OptionalInt;
import java.util.concurrent.ConcurrentLinkedDeque;

import net.coljate.list.ConcurrentList;
import net.coljate.util.Functions;

/**
 *
 * @author ollie
 * @see ConcurrentLinkedDeque
 */
public class WrappedConcurrentLinkedDeque<T>
        extends WrappedDeque<T>
        implements ConcurrentList<T> {

    protected WrappedConcurrentLinkedDeque(final java.util.concurrent.ConcurrentLinkedDeque<T> deque) {
        super(deque, OptionalInt.empty());
    }

    @Override
    public Element<T> poll() {
        return Functions.ifNonNull(this.nativePoll(), Element::of);
    }

    @Override
    public Element<T> peek() {
        return Functions.ifNonNull(this.nativePeek(), Element::of);
    }

    @Override
    public boolean add(final T element) {
        if (element == null) {
            return false;
        }
        return super.add(element);
    }

    @Override
    public WrappedConcurrentLinkedDeque<T> mutableCopy() {
        return new WrappedConcurrentLinkedDeque<>(this.mutableJavaCopy(i -> new ConcurrentLinkedDeque<>()));
    }

}
