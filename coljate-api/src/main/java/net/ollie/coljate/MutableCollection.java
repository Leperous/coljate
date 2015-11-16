package net.ollie.coljate;

import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Ollie
 */
public interface MutableCollection<@Nullable T> extends Collection<T> {

    boolean add(T element);

    boolean remove(Object element);

}
