package net.ollie.coljate.map;

import java.util.Objects;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Ollie
 */
public interface MapEntry<@NonNull K, @Nullable V> {

    K key();

    V value();

    static int hashCode(@NonNull final MapEntry<?, ?> entry) {
        return Objects.hash(entry.key(), entry.value());
    }

    static boolean equal(final MapEntry<?, ?> left, final MapEntry<?, ?> right) {
        return left == null || right == null
                ? left == right
                : Objects.equals(left.key(), right.key()) && Objects.equals(left.value(), right.value());
    }

    static String toString(final MapEntry<?, ?> entry) {
        return entry.key() + "=" + entry.value();
    }

}
