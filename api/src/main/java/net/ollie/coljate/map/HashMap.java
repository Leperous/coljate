package net.ollie.coljate.map;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import net.ollie.coljate.theory.feature.ConstantContains;
import net.ollie.coljate.theory.feature.ConstantGet;

/**
 *
 * @author Ollie
 */
public interface HashMap<@NonNull K, @Nullable V> extends Map<K, V>, ConstantGet<K, V>, ConstantContains {

    static <K, V> java.util.HashMap<K, V> copyOf(final java.util.Map<? extends K, ? extends V> map) {
        return new java.util.HashMap<>(map);
    }

    static <K, V> java.util.HashMap<K, V> copyOf(final Map<? extends K, ? extends V> map) {
        final java.util.HashMap<K, V> hashMap = new java.util.HashMap<>(map.count());
        map.forEach(hashMap::put);
        return hashMap;
    }

}
