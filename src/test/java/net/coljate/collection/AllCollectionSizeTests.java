package net.coljate.collection;

/**
 *
 * @author ollie
 */
public interface AllCollectionSizeTests<T>
        extends EmptyCollectionTests<T>, SingletonCollectionTests<T>, CollectionTests<T> {

    @Override
    default Collection<T> createEmpty() {
        return this.create();
    }

    @Override
    default Collection<T> createSingleton(final T element) {
        return this.create(element);
    }

}
