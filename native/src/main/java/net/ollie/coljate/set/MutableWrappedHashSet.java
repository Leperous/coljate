package net.ollie.coljate.set;

import net.ollie.coljate.Collection;
import static net.ollie.coljate.set.mixin.WrapsHashSet.copyIntoHashSet;

/**
 *
 * @author Ollie
 */
public class MutableWrappedHashSet<T> extends MutableWrappedSet<T> implements HashSet<T> {

    @SafeVarargs
    public static <T> MutableWrappedHashSet<T> copyOf(final T... array) {
        return viewOf(copyIntoHashSet(array));
    }

    public static <T> MutableWrappedHashSet<T> copyOf(final java.util.Collection<? extends T> collection) {
        return viewOf(copyIntoHashSet(collection));
    }

    public static <T> MutableWrappedHashSet<T> copyOf(final Collection<? extends T> collection) {
        return viewOf(copyIntoHashSet(collection));
    }

    public static <T> MutableWrappedHashSet<T> viewOf(final java.util.HashSet<T> set) {
        return new MutableWrappedHashSet<>(set);
    }

    public MutableWrappedHashSet() {
        this(new java.util.HashSet<>());
    }

    MutableWrappedHashSet(final java.util.HashSet<T> delegate) {
        super(delegate);
    }

}
