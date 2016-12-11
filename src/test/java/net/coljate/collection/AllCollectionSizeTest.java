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
    Collection<T> create(java.util.List<T> elements);
    
}
