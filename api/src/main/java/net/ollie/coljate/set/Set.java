package net.ollie.coljate.set;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.ollie.coljate.Collection;
import net.ollie.coljate.theory.Finite;

/**
 *
 * @author Ollie
 * @param <T>
 */
public interface Set<@Nullable T>
        extends Collection<T> {

    @Override
    Set<T> tail();

    @Override
    MutableSet<T> mutableCopy();

    @Override
    ImmutableSet<T> immutableCopy();

    static int hash(final Set<?> list) {
        return Finite.sumHash(list);
    }

    static boolean elementsEqual(final Set<?> l1, final Set<?> l2) {
        return Finite.elementsEqual(l1, l2);
    }

}
