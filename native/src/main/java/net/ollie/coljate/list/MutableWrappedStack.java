package net.ollie.coljate.list;

import java.io.Serializable;

/**
 *
 * @author Ollie
 */
public class MutableWrappedStack<T>
        extends MutableWrappedList<T>
        implements Stack<T>, Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    private final java.util.Stack<T> delegate;

    public MutableWrappedStack() {
        this(new java.util.Stack<>());
    }

    public MutableWrappedStack(final java.util.Stack<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public T push(final T element) {
        return delegate.push(element);
    }

    @Override
    public T pop() {
        return delegate.pop();
    }

    @Override
    public T peek() {
        return delegate.peek();
    }

    @Override
    @SuppressWarnings("unchecked")
    public java.util.Stack<T> copyDelegate() {
        return (java.util.Stack<T>) delegate.clone();
    }

    @Override
    public MutableWrappedStack<T> mutableCopy() {
        return new MutableWrappedStack<>(this.copyDelegate());
    }

    @Override
    @SuppressWarnings("CloneDoesntCallSuperClone")
    public MutableWrappedStack<T> clone() {
        return this.mutableCopy();
    }

}
