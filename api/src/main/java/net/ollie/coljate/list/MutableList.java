package net.ollie.coljate.list;

import java.util.Objects;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.ollie.coljate.MutableCollection;

/**
 *
 * @author Ollie
 * @param <T>
 * @see ImmutableList
 */
public interface MutableList<@Nullable T>
        extends List<T>, MutableCollection<T> {

    @Override
    @Deprecated
    default boolean add(final T element) {
        this.suffix(element);
        return true;
    }

    void prefix(T element);

    void suffix(T element);

    /**
     *
     * @param index
     * @param element
     * @return the previous value.
     */
    @Nullable
    T set(int index, T element);

    @Nullable
    default boolean replace(int index, T expected, T replacement) {
        return Objects.equals(this.get(index), expected)
                ? this.set(index, replacement) != null
                : false;
    }

    /**
     *
     * @return a view onto the tail of this list.
     */
    @Override
    MutableList<T> tail();

}
