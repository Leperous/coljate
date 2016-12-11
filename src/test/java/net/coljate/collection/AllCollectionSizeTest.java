package net.coljate.collection;

/**
 * Tests for collections of all sizes.
 *
 * @author ollie
 */
@SuppressWarnings("unchecked")
public interface AllCollectionSizeTest<T>
        extends EmptyCollectionTest<T>, SingletonCollectionTest<T>, CollectionTest<T> {

    @Override
    default Collection<T> createEmpty() {
        return this.create();
    }

    @Override
    default Collection<T> createSingleton(final T element) {
        return this.create(element);
    }

    @Override
    Collection<T> create(T... elements);

}
