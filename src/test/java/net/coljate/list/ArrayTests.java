package net.coljate.list;

/**
 *
 * @author Ollie
 */
public interface ArrayTests<T> extends ListTests<T> {

    @Override
    Array<T> create(T... elements);

}
