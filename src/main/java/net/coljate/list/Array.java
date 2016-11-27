package net.coljate.list;

import net.coljate.feature.FastGet;

/**
 *
 * @author ollie
 */
public interface Array<T> extends List<T>, FastGet<Integer, T> {

    /**
     *
     * @param index
     * @return
     */
    T get(int index);

    @Override
    default T get(final Integer i) {
        return this.get(i.intValue());
    }

    /**
     * @return the length of this array. It will be equal to or greater than the
     * {@link #count}.
     */
    int length();

    @Override
    default T last() {
        return this.get(this.count());
    }

    @Override
    ImmutableArray<T> immutableCopy();

}
