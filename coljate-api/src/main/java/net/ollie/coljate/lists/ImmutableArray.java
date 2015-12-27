package net.ollie.coljate.lists;

/**
 *
 * @author ollie
 */
public interface ImmutableArray<T>
        extends Array<T>, ImmutableList<T> {

    @Override
    @Deprecated
    default ImmutableArray<T> immutableCopy() {
        return this;
    }

    @Override
    ImmutableArray<T> tail();

}
