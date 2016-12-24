package net.coljate.list;

/**
 *
 * @author ollie
 */
public interface MutableArrayTest<T> extends ArrayTest<T>, MutableListTest<T> {

    @Override
    MutableArray<T> createTestCollection();

    interface ZeroElementTests<T> extends MutableArrayTest<T>, ArrayTest.ZeroElementTests<T>, MutableListTest.ZeroElementTests<T> {

    }

    interface OneElementTests<T> extends MutableArrayTest<T>, ArrayTest.OneElementTests<T>, MutableListTest.OneElementTests<T> {

    }

}
