package net.ollie.coljate.sets.mixin;

import net.ollie.coljate.sets.Set;
import net.ollie.coljate.sets.SortedSet;

/**
 * Some {@link Set} that wraps a {@link java.util.TreeSet}.
 *
 * @author Ollie
 */
public interface WrapsTreeSet<T> extends WrapsSet<T>, SortedSet<T> {

    @Override
    java.util.TreeSet<T> copyDelegate();

}
