package net.ollie.coljate.set.mixin;

import net.ollie.coljate.set.Set;
import net.ollie.coljate.set.SortedSet;

/**
 * Some {@link Set} that wraps a {@link java.util.TreeSet}.
 *
 * @author Ollie
 */
public interface WrapsTreeSet<T> extends WrapsSet<T>, SortedSet<T> {

    @Override
    java.util.TreeSet<T> copyDelegate();

}
