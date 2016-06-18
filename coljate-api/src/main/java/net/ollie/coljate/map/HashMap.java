package net.ollie.coljate.map;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.ollie.coljate.theory.feature.ConstantGet;

/**
 *
 * @author Ollie
 */
public interface HashMap<K, @Nullable V> extends Map<K, V>, ConstantGet<K, V> {

}
