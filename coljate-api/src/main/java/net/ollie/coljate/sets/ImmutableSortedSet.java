package net.ollie.coljate.sets;

/**
 *
 * @author Ollie
 */
public interface ImmutableSortedSet<T> extends SortedSet<T>, ImmutableSet<T> {

    @Override
    ImmutableSortedSet<T> with(T element);

    @Override
    @Deprecated
    default ImmutableSortedSet<T> immutableCopy() {
        return this;
    }

}
