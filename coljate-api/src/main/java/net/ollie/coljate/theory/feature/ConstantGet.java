package net.ollie.coljate.theory.feature;

import net.ollie.coljate.theory.Associative;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * An {@link Associative associative collection} where {@link #get} is typically
 * {@code O(1)}.
 *
 * @author ollie
 */
public interface ConstantGet<@NonNull K, @Nullable V>
        extends Associative<K, V>, java.util.RandomAccess {

}
