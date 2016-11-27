package net.coljate.list;

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

}
