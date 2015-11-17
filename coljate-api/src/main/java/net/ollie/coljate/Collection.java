package net.ollie.coljate;

import java.util.StringJoiner;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import net.ollie.coljate.theory.Container;
import net.ollie.coljate.theory.Finite;

/**
 *
 * @author Ollie
 */
public interface Collection<@Nullable T> extends Finite<T>, Container {

    @Override
    Collection<T> tail();

    @NonNull
    MutableCollection<T> mutableCopy();

    @NonNull
    ImmutableCollection<T> immutableCopy();

    static String toString(final Collection<?> collection) {
        final StringJoiner joiner = new StringJoiner("[", ",", "]");
        collection.iterator().forEachRemaining(element -> joiner.add(element == collection ? "(collection)" : String.valueOf(element)));
        return joiner.toString();
    }

}
