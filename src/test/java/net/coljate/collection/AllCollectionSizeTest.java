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
    default Collection<T> create() {
        return this.create(java.util.Collections.emptyList());
    }

    @Override
    default Collection<T> create(final T element) {
        return this.create(java.util.Arrays.asList(element));
    }

    @Override
    Collection<T> create(java.util.List<T> elements);

}
