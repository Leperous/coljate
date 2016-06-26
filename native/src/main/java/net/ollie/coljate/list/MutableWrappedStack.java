package net.ollie.coljate.list;

/**
 *
 * @author Ollie
 */
public class MutableWrappedStack<T>
        extends MutableWrappedList<T>
        implements Stack<T> {

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

}
