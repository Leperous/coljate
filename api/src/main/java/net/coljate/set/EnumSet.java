package net.coljate.set;

import net.coljate.set.impl.LargeEnumSet;

/**
 *
 * @author Ollie
 */
public interface EnumSet<E extends Enum<E>> extends Set<E> {

    boolean contains(E e);

    Class<E> enumClass();

    @Override
    @SuppressWarnings("unchecked")
    default boolean contains(final Object object) {
        return object != null
                && this.enumClass().isAssignableFrom(object.getClass())
                && this.contains((E) object);
    }

    static <E extends Enum<E>> EnumSet<E> noneOf(final Class<E> enumClass) {
        return LargeEnumSet.noneOf(enumClass);
    }

    @SafeVarargs
    static <E extends Enum<E>> EnumSet<E> of(final E first, final E... rest) {
        return LargeEnumSet.of(first, rest);
    }

    static <E extends Enum<E>> EnumSet<E> allOf(final Class<E> enumClass) {
        return LargeEnumSet.allOf(enumClass);
    }

}
