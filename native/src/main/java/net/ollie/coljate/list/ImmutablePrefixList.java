package net.ollie.coljate.list;

import java.util.Iterator;
import java.util.Objects;

import net.ollie.coljate.UnmodifiableIterator;
import net.ollie.coljate.list.mixin.CopiedToList;
import net.ollie.coljate.utils.Iterators;

/**
 *
 * @author Ollie
 */
public class ImmutablePrefixList<T>
        extends AbstractList<T>
        implements ImmutableList<T>, CopiedToList<T> {

    public static <T> ImmutablePrefixList<T> of(final T element) {
        return new ImmutablePrefixList<>(ImmutableEmptyArray.get(), element, ImmutableEmptyArray.get());
    }

    public static <T> ImmutablePrefixList<T> prefix(final T prefix, final ImmutableList<T> suffix) {
        return new ImmutablePrefixList<>(ImmutableEmptyArray.get(), prefix, suffix);
    }

    public static <T> ImmutablePrefixList<T> suffix(final ImmutableList<T> prefix, final T suffix) {
        return new ImmutablePrefixList<>(prefix, suffix, ImmutableEmptyArray.get());
    }

    private final ImmutableList<T> prefix;
    private final T element;
    private final ImmutableList<T> suffix;

    ImmutablePrefixList(
            final ImmutableList<T> prefix,
            final T element,
            final ImmutableList<T> suffix) {
        this.prefix = prefix;
        this.element = element;
        this.suffix = suffix;
    }

    @Override
    public ImmutableList<T> prefixed(final T element) {
        return prefix(element, this);
    }

    @Override
    public ImmutablePrefixList<T> suffixed(final T element) {
        return suffix(this, element);
    }

    @Override
    public T head() {
        return prefix.isEmpty()
                ? element
                : prefix.head();
    }

    @Override
    public ImmutableList<T> tail() {
        return prefix.isEmpty()
                ? suffix
                : new ImmutablePrefixList<>(prefix.tail(), element, suffix);
    }

    @Override
    public T get(final int index) {
        final int p = prefix.count();
        if (index < p) {
            return prefix.get(index);
        } else if (index == p) {
            return element;
        } else {
            return suffix.get(index - p);
        }
    }

    @Override
    @Deprecated
    public ImmutablePrefixList<T> immutableCopy() {
        return this;
    }

    public ImmutableList<T> squash() {
        return ImmutableWrappedArrayList.copyOf(this);
    }

    @Override
    public MutableList<T> mutableCopy() {
        return CopiedToList.super.mutableCopy();
    }

    @Override
    public int count() {
        return prefix.count() + 1 + suffix.count();
    }

    @Override
    public boolean contains(final Object object) {
        return Objects.equals(element, object)
                || prefix.contains(object)
                || suffix.contains(object);
    }

    @Override
    public UnmodifiableIterator<T> iterator() {
        return new ImmutablePrefixListIterator();
    }

    private final class ImmutablePrefixListIterator
            extends UnmodifiableIterator<T> {

        private final Iterator<T> l = prefix.iterator();
        private final Iterator<T> m = Iterators.of(element);
        private Iterator<T> current = l;

        @Override
        public boolean hasNext() {
            if (!current.hasNext()) {
                if (current == l) {
                    current = m;
                } else if (current == m) {
                    current = suffix.iterator();
                }
            }
            return current.hasNext();
        }

        @Override
        public T next() {
            return current.next();
        }

    }

}
