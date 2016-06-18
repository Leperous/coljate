package net.ollie.coljate.list;

/**
 *
 * @author Ollie
 */
public interface MutableArray<T> extends Array<T>, MutableList<T> {

    /**
     * Will pad with nulls, or truncate.
     *
     * @param capacity
     */
    void setCapacity(int capacity);

    default void ensureCapacity(final int capacity) {
        this.setCapacity(Math.max(this.capacity(), capacity));
    }

}
