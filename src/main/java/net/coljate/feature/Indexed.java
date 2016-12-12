package net.coljate.feature;

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

}
