package net.ollie.coljate;

/**
 *
 * @author Ollie
 */
public interface CollectionBuilder<T> {

    T randomValue();

    Collection<T> create();

    Collection<T> create(T element);

    Collection<T> create(T first, T second);

}
