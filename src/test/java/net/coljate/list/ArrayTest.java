package net.coljate.list;

/**
 *
 * @author Ollie
 */
public interface ArrayTest<T> extends ListTest<T> {

    @Override
    Array<T> create(T... elements);

}
