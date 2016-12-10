package net.coljate.feature;

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
    T get(int index);

    @Override
    default T get(final Integer i) {
        return this.get(i.intValue());
    }

}
