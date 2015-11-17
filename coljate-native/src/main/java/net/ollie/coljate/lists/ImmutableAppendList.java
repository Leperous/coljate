package net.ollie.coljate.lists;

import static java.util.Objects.requireNonNull;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import net.ollie.coljate.UnmodifiableIterator;
import net.ollie.coljate.lists.mixin.ImmutableNativeListMixin;

/**
 *
 * @author Ollie
 */
public class ImmutableAppendList<@Nullable T> implements ImmutableNativeListMixin<T> {

    public static <T> ImmutableList<T> of(@NonNull final ImmutableList<? extends T> list, @Nullable final T right) {
        return list.isEmpty()
                ? ImmutableArrayList.of(right)
                : new ImmutableAppendList<>(list, right);
    }

    private final ImmutableList<? extends T> left;
    private final T right;

    ImmutableAppendList(final ImmutableList<? extends T> left, final T right) {
        this.left = requireNonNull(left);
        this.right = right;
    }

    @Override
    public ImmutableList<T> tail() {
        return of(left.tail(), right);
    }

    @Override
    public T get(final int index) {
        return index == left.size()
                ? right
                : left.get(index);
    }

    @Override
    public int size() {
        return left.size() + 1;
    }

    @Override
    public UnmodifiableIterator<T> iterator() {
        throw new UnsupportedOperationException(); //TODO
    }

}
