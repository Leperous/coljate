package net.coljate.util;

/**
 *
 * @author ollie
 */
public interface SelfTyped<T extends SelfTyped<T>> {

    default T self() {
        return (T) this;
    }

}
