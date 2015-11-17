package net.ollie.coljate.sets;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.ollie.coljate.MutableCollection;

/**
 *
 * @author Ollie
 */
public interface MutableSet<@Nullable T> extends Set<T>, MutableCollection<T> {

}
