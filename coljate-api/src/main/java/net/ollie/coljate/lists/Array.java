package net.ollie.coljate.lists;

/**
 * Same as a {@link List}, but with a fixed capacity.
 *
 * Likely backed by an actual object array.
 *
 * @author Ollie
 */
public interface Array<T> extends List<T> {

    int capacity();

    @Override
    MutableArray<T> mutableCopy();

    @Override
    ImmutableArray<T> immutableCopy();

}
