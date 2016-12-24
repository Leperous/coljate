package net.coljate.list;

/**
 *
 * @author ollie
 */
public interface ImmutableArrayTest<T> extends ArrayTest<T>, ImmutableListTest<T> {

    @Override
    ImmutableArray<T> createTestCollection();

    interface OneElementTests<T> extends ImmutableArrayTest<T>, ArrayTest.OneElementTests<T>, ImmutableListTest.OneElementTests<T> {

    }

}
