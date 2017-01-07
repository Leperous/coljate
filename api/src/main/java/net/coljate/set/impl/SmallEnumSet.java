package net.coljate.set.impl;

import java.util.Objects;

import net.coljate.set.AbstractSet;
import net.coljate.set.EnumSet;

/**
 *
 * @author Ollie
 * @see java.util.RegularEnumSet
 */
public class SmallEnumSet<E extends Enum<E>>
        extends AbstractSet<E>
        implements EnumSet<E> {

    public static <E extends Enum<E>> SmallEnumSet<E> noneOf(final Class<E> enumClass) {
        return new SmallEnumSet<>(enumClass, 0L);
    }

    private final Class<E> enumClass;
    private final long value;

    protected SmallEnumSet(final Class<E> enumClass, final long initialValue) {
        this.enumClass = Objects.requireNonNull(enumClass);
        if (enumClass.getEnumConstants().length > 64) {
            throw new IllegalArgumentException("Too many enum values in " + enumClass);
        }
        this.value = initialValue;
    }

    @Override
    public Class<E> enumClass() {
        return enumClass;
    }

    protected long value() {
        return value;
    }

    @Override
    public boolean contains(final E e) {
        return (value & (1 << e.ordinal())) != 0;
    }

    @Override
    public boolean contains(final Object object) {
        return EnumSet.super.contains(object);
    }

}
