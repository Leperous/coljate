package net.ollie.coljate.lists;

import java.util.OptionalInt;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import net.ollie.coljate.Collection;
import net.ollie.coljate.theory.PartialFunction;

/**
 *
 * @author Ollie
 */
public interface List<@Nullable T> extends Collection<T>, PartialFunction<Integer, T> {

    @Nullable
    T get(int index);

    @NonNull
    OptionalInt indexOf(Object element);

    @Override
    List<T> tail();

    @Override
    ImmutableList<T> immutableCopy();

    @Override
    MutableList<T> mutableCopy();

    @Override
    default T apply(final Integer index) {
        return this.get(index);
    }

    @Override
    default T head() {
        return this.get(0);
    }

    @Override
    public default boolean inDomain(final Integer input) {
        return input != null && this.inDomain(input.intValue());
    }

    default boolean inDomain(final int index) {
        return index >= 0 && index < this.size();
    }

}
