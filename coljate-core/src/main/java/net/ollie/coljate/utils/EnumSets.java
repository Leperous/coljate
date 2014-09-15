package net.ollie.coljate.utils;

import java.util.EnumSet;

import java.lang.reflect.Field;

/**
 *
 * @author Ollie
 */
public final class EnumSets {

    private static final Field elementTypeField;

    static {
        try {
            elementTypeField = EnumSet.class.getField("elementType");
            elementTypeField.setAccessible(true);
        } catch (final NoSuchFieldException | SecurityException ex) {
            throw new Error(ex);
        }
    }

    private EnumSets() {
    }

    public static <E extends Enum<E>> Class<E> getElementType(final EnumSet<E> set) {
        try {
            return (Class<E>) elementTypeField.get(set);
        } catch (final IllegalAccessException ex) {
            throw new UnsupportedOperationException(ex);
        }
    }

}
