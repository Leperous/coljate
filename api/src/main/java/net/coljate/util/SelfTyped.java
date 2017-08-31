package net.coljate.util;

/**
 *
 * @author Ollie
 */
public interface SelfTyped<T extends SelfTyped<T>> {

    default T self() {
        return (T) this;
    }

}
