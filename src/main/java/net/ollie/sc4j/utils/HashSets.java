package net.ollie.sc4j.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.OptionalInt;

/**
 *
 * @author Ollie
 */
public final class HashSets {

    private HashSets() {
    }

    public static <V> HashSet<V> copy(final Iterable<? extends V> iterable) {
        if (iterable instanceof Collection) {
            return new HashSet<>((Collection<? extends V>) iterable);
        } else {
            final OptionalInt size = Iterables.maybeCount(iterable);
            final HashSet<V> set = new HashSet<>(size.isPresent() ? size.getAsInt() : 10);
            for (final V element : iterable) {
                set.add(element);
            }
            return set;
        }
    }

    public static <V> HashSet<V> castOrCopy(final Iterable<V> iterable) {
        if (iterable instanceof HashSet) {
            return (HashSet<V>) iterable;
        } else {
            return copy(iterable);
        }
    }

}
