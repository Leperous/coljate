package net.coljate.collection;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public interface EmptyTest<T> extends ImmutableCollectionTest<T>, CollectionTest.ZeroElementTests<T> {

    @Override
    Empty<T> createTestCollection();

}
