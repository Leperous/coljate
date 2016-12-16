package net.coljate.list;

/**
 *
 * @author Ollie
 */
public interface ImmutableArrayTest<T> extends ArrayTest<T>, ImmutableListTest<T> {

    @Override
    ImmutableArray<T> create(java.util.List<T> elements);

    @Override
    default ImmutableArray<T> create() {
        return this.create(emptyList());
    }

    @Override
    default ImmutableArray<T> create(final T element) {
        return this.create(singletonList(element));
    }

}
