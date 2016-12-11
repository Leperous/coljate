package net.coljate.list;

/**
 *
 * @author Ollie
 */
public interface ImmutableArrayTests<T> extends ArrayTests<T>, ImmutableListTests<T> {

    @Override
    ImmutableArray<T> create(T... elements);

}
