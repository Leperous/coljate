package net.ollie.coljate.list;

import java.util.Optional;

import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * A stack is a ``FIFO'' (first-in, first-out) collection of elements typically written and read with {@link #push} and
 * {@link #pop}.
 *
 * @author Ollie
 * @see java.util.Stack
 */
public interface Stack<T> extends MutableList<T> {

    default T push(@NonNull final T element) {
        this.suffix(element);
        return element;
    }

    T pop();

    default T peek() {
        return this.last();
    }

    default Optional<T> maybePop() {
        return this.isEmpty() ? Optional.empty() : Optional.ofNullable(this.pop());
    }

}
