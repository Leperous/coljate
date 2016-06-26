package net.ollie.coljate.set;

/**
 *
 * @author Ollie
 */
public interface CountingSet<T> extends Set<T> {

    int count(Object object);

    @Override
    default boolean contains(final Object object) {
        return this.count(object) >= 1;
    }

    Set<T> unique();

}
