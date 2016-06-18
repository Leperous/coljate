package net.ollie.coljate.set;

import net.ollie.coljate.MutableCollection;

import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Ollie
 */
public interface MutableSet<@Nullable T> extends Set<T>, MutableCollection<T> {

}
