package net.ollie.coljate.utils;

import net.ollie.coljate.utils.iterators.Iterables;

import java.util.Collection;
import java.util.HashSet;
import java.util.OptionalInt;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public final class HashSets {

    private HashSets() {
    }

    @SafeVarargs
    public static <V> HashSet<V> copy(final V... array) {
        final HashSet<V> set = new HashSet<>(array.length);
        for (final V element : array) {
            set.add(element);
        }
        return set;
    }

    public static <V> HashSet<V> copy(@Nonnull final Iterable<? extends V> iterable) {
        if (iterable == null) {
            throw new IllegalArgumentException("Passed null iterable!");
        } else if (iterable instanceof Collection) {
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
