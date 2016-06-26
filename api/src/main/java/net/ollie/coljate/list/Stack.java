package net.ollie.coljate.list;

import org.checkerframework.checker.nullness.qual.NonNull;

/**
 *
 * @author Ollie
 * @see java.util.Stack
 */
public interface Stack<T> extends MutableList<T> {

    default T push(@NonNull final T element) {
        this.suffix(element);
        return element;
    }

    default T peek() {
        return this.last();
    }

    T pop();

}
