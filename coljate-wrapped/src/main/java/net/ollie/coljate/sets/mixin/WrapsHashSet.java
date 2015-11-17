package net.ollie.coljate.sets.mixin;

import net.ollie.coljate.sets.MutableSet;
import net.ollie.coljate.sets.MutableWrappedHashSet;

/**
 * Some {@link Set} that wraps a {@link java.util.HashSet}.
 *
 * @author Ollie
 */
public interface WrapsHashSet<T> extends WrapsSet<T> {

    @Override
    java.util.HashSet<T> copyDelegate();

    @Override
    default MutableSet<T> mutableCopy() {
        return MutableWrappedHashSet.viewOf(this.copyDelegate());
    }

}
