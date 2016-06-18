package net.ollie.coljate.set.mixin;

import net.ollie.coljate.WrapsMutableCollection;
import net.ollie.coljate.set.MutableSet;
import net.ollie.coljate.set.Set;

import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Some {@link MutableSet} that wraps a {@link java.util.Set}.
 *
 * @author Ollie
 * @see Set
 */
public interface WrapsMutableSet<@Nullable T>
        extends MutableSet<T>, WrapsMutableCollection<T>, WrapsSet<T> {

    @Override
    java.util.Set<T> delegate();

    @Override
    default java.util.Set<T> copyDelegate() {
        return new java.util.HashSet<>(this.delegate());
    }

}
