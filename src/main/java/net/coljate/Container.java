package net.coljate;

/**
 * An object that can "contain" other objects.
 *
 * @author ollie
 * @see Collection
 */
public interface Container {

    boolean contains(Object object);

    boolean isEmpty();

}
