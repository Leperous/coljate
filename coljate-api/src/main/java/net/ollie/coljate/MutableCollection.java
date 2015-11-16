package net.ollie.coljate;

/**
 *
 * @author Ollie
 */
public interface MutableCollection<T> extends Collection<T> {

    boolean add(T element);

    boolean remove(Object element);

}
