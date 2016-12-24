package net.coljate.collection;

/**
 *
 * @author ollie
 */
public interface ImmutableCollectionTest<T> extends CollectionTest<T> {

    interface OneElementTests<T> extends ImmutableCollectionTest<T>, CollectionTest.OneElementTests<T> {

    }

}
