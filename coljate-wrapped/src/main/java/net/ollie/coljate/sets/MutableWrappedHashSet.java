package net.ollie.coljate.sets;

import net.ollie.coljate.Collection;

/**
 *
 * @author Ollie
 */
public class MutableWrappedHashSet<T> extends MutableWrappedSet<T> {

    public static <T> MutableSet<T> copyOf(final java.util.Collection<? extends T> collection) {
        return viewOf(new java.util.HashSet<>(collection));
    }

    public static <T> MutableSet<T> copyOf(final Collection<? extends T> collection) {
        throw new UnsupportedOperationException(); //TODO
    }

    public static <T> MutableSet<T> viewOf(final java.util.HashSet<T> set) {
        return new MutableWrappedHashSet<>(set);
    }

    MutableWrappedHashSet(final java.util.HashSet<T> delegate) {
        super(delegate);
    }

}
