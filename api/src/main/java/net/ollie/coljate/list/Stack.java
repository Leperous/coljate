package net.ollie.coljate.list;

/**
 *
 * @author Ollie
 * @see java.util.Stack
 */
public interface Stack<T> extends MutableList<T> {

    default T push(final T element) {
        this.suffix(element);
        return element;
    }

    default T peek() {
        return this.last();
    }

    T pop();

}
