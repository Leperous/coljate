package net.coljate.list;

/**
 *
 * @author ollie
 */
public abstract class ImmutableArrayTest extends ImmutableListTest {

    protected abstract <T> ImmutableArray<T> create(T... Elements);

}
