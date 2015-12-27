package net.ollie.coljate.lists;

import java.util.Objects;
import java.util.OptionalInt;
import net.ollie.coljate.Collection;
import net.ollie.coljate.theory.PartialFunction;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Ollie
 */
public interface List<@Nullable T>
        extends Collection<T>, PartialFunction<Integer, T> {

    @Nullable
    T get(int index);

    @NonNull
    default OptionalInt indexOf(final Object target) {
        int index = 0;
        for (final T element : this) {
            if (Objects.equals(element, target)) {
                return OptionalInt.of(index);
            }
            index++;
        }
        return OptionalInt.empty();
    }

    @Override
    default boolean contains(final Object target) {
        return this.indexOf(target).isPresent();
    }

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
        return this.isEmpty() ? null : this.get(0);
    }

    @Override
    public default boolean inDomain(final Integer input) {
        return input != null && this.inDomain(input.intValue());
    }

    default boolean inDomain(final int index) {
        return index >= 0 && index < this.count();
    }

}
