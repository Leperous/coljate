package net.ollie.coljate.map;

import javax.annotation.CheckForNull;

import org.checkerframework.checker.nullness.qual.NonNull;

import net.ollie.coljate.set.SortedSet;
import net.ollie.coljate.theory.Sorted;

/**
 *
 * @author Ollie
 */
public interface SortedMap<@NonNull K, V> extends Map<K, V>, Sorted<K> {

    @CheckForNull
    K maxKey();

    @CheckForNull
    K minKey();

    @Override
    SortedSet<K> keys();

    @Override
    SortedMap<K, V> tail();

}
