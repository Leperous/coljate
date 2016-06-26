package net.ollie.coljate.set;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.ollie.coljate.MutableCollection;

/**
 *
 * @author Ollie
 */
public interface MutableSet<@Nullable T> extends Set<T>, MutableCollection<T> {

    @Override
    @Deprecated
    default boolean removeAll(final Object element) {
        return this.removeOnce(element);
    }

}
