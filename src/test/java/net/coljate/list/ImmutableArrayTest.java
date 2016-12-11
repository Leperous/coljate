package net.coljate.list;

/**
 *
 * @author Ollie
 */
public interface ImmutableArrayTest<T> extends ArrayTest<T>, ImmutableListTest<T> {

    @Override
    ImmutableArray<T> create(java.util.List<T> elements);

}
