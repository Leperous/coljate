package net.coljate.collection;

import net.coljate.set.Set;

/**
 * Tests for collections of all sizes.
 *
 * @author ollie
 */
@SuppressWarnings("unchecked")
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

    @Override
    Collection<T> create(T... elements);

}
