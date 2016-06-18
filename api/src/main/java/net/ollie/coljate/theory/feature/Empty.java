package net.ollie.coljate.theory.feature;

import java.util.Iterator;

import net.ollie.coljate.theory.Finite;
import net.ollie.coljate.utils.Iterators;

import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author ollie
 */
public interface Empty<@Nullable T> extends Finite<T> {

    @Override
    @Deprecated
    default int count() {
        return 0;
    }

    @Override
    @Deprecated
    default boolean isEmpty() {
        return true;
    }

    @Override
    @Deprecated
    default Empty<T> tail() {
        return this;
    }

    @Override
    @Deprecated
    default boolean contains(final Object object) {
        return false;
    }

    @Override
    @Deprecated
    default Iterator<T> iterator() {
        return Iterators.none();
    }

}
