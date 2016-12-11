package net.coljate.collection;

/**
 *
 * @author ollie
 */
public interface ImmutableCollectionTest<T> extends CollectionTest<T> {

    @Override
    ImmutableCollection<T> create(T... elements);

}
