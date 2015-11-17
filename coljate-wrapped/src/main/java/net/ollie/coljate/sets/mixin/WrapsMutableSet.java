package net.ollie.coljate.sets.mixin;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.ollie.coljate.WrapsMutableCollection;
import net.ollie.coljate.sets.MutableSet;
import net.ollie.coljate.sets.Set;

/**
 *
 * @author Ollie
 * @see Set
 */
public interface WrapsMutableSet<@Nullable T> extends MutableSet<T>, WrapsMutableCollection<T>, WrapsSet<T> {

    @Override
    java.util.Set<T> delegate();

    @Override
    default java.util.Set<T> copyDelegate() {
        return new java.util.HashSet<>(this.delegate());
    }

}
