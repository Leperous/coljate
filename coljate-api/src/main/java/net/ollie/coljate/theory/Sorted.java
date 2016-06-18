package net.ollie.coljate.theory;

import java.util.Comparator;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Ollie
 */
public interface Sorted<@Nullable T> {

    @NonNull
    Comparator<? super T> comparator();

}
