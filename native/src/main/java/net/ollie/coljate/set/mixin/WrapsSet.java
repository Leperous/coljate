package net.ollie.coljate.set.mixin;

import net.ollie.coljate.set.Set;

/**
 * Some {@link Set} that wraps a {@link java.util.Set}.
 *
 * @author Ollie
 */
public interface WrapsSet<T> extends Set<T> {

    java.util.Set<T> copyDelegate();

}
