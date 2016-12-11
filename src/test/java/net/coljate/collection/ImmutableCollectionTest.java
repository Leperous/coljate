package net.coljate.collection;

/**
 *
 * @author ollie
 */
public interface ImmutableCollectionTest<T> extends CollectionTest<T> {

    @Override
    ImmutableCollection<T> create(java.util.List<T> elements);

}
