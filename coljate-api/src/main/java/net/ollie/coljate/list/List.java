package net.ollie.coljate.list;

import java.util.Objects;
import java.util.OptionalInt;

import net.ollie.coljate.Collection;
import net.ollie.coljate.theory.Associative;
import net.ollie.coljate.theory.Finite;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Ollie
 * @param <T>
 */
public interface List<@Nullable T>
        extends Collection<T>, Associative<Integer, T> {

    T get(int index);

    @Override
    default T get(final Integer index) {
        return this.get(index.intValue());
    }

    @NonNull
    default OptionalInt indexOf(@NonNull final Object target) {
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
    default T head() {
        return this.isEmpty() ? null : this.get(0);
    }

    @Override
    default boolean inDomain(final Integer input) {
        return input != null && this.inDomain(input.intValue());
    }

    default boolean inDomain(final int index) {
        return index >= 0 && index < this.count();
    }

    static int hashCode(final List<?> list) {
        return Finite.productHash(list);
    }

    static boolean elementsEqual(final List<?> l1, final List<?> l2) {
        return Finite.elementsEqual(l1, l2);
    }

}
