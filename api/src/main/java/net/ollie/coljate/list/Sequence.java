package net.ollie.coljate.list;

import java.util.function.Function;

import net.ollie.coljate.theory.Container;
import net.ollie.coljate.theory.Streamable;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Ollie
 * @param <N>
 * @param <T>
 */
public interface Sequence<@NonNull N, @Nullable T>
        extends Function<N, T>, Streamable<T>, Container {

    T at(N index);

    @Override
    @Deprecated
    default T apply(final N index) {
        return this.at(index);
    }

}
