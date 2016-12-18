package net.coljate.table;

/**
 *
 * @author ollie
 * @see Table
 */
public interface Cell<R, C, V> {

    R rowKey();

    C columnKey();

    V value();

}
