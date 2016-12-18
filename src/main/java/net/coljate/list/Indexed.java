package net.coljate.list;

import net.coljate.map.FastGet;
import net.coljate.feature.complexity.Complexity;
import net.coljate.feature.complexity.TimeComplexity;

/**
 *
 * @author ollie
 */
public interface Indexed<T> extends FastGet<Integer, T> {

    /**
     *
     * @param index
     * @return
     * @throws IndexOutOfBoundsException
     */
    @TimeComplexity(Complexity.CONSTANT)
    T get(int index);

    @Override
    default T get(final Integer i) {
        return this.get(i.intValue());
    }

    @Deprecated
    @Override
    default T getIfPresent(final Object object) {
        return object instanceof Integer
                ? this.get((Integer) object)
                : null;
    }

}
