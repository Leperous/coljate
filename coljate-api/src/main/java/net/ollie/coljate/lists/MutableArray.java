package net.ollie.coljate.lists;

/**
 *
 * @author Ollie
 */
public interface MutableArray<T> extends Array<T>, MutableList<T> {

    void setCapacity(int capacity);

    default void ensureCapacity(final int capacity) {
        this.setCapacity(Math.max(this.capacity(), capacity));
    }

}
