package net.coljate.map;

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
