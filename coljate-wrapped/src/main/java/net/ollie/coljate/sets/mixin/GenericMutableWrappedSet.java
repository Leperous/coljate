package net.ollie.coljate.sets.mixin;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.ollie.coljate.GenericMutableWrappedCollection;
import net.ollie.coljate.sets.MutableSet;

/**
 *
 * @author Ollie
 */
public interface GenericMutableWrappedSet<@Nullable T> extends MutableSet<T>, GenericMutableWrappedCollection<T> {

    @Override
    java.util.Set<T> delegate();

}
