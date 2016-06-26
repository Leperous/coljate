package net.ollie.coljate;

/**
 *
 * @author Ollie
 */
public interface CollectionBuilder<T> {

    Collection<T> create();

    Collection<T> create(T element);

    Collection<T> create(T first, T second);

}
