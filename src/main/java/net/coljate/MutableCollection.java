package net.coljate;

/**
 *
 * @author ollie
 */
public interface MutableCollection<T> extends Collection<T> {

    boolean removeFirst(T element);

    boolean removeAll(T element);

}
