package net.ollie.sc4j.utils;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.function.Predicate;

import net.ollie.sc4j.Collection;
import net.ollie.sc4j.access.Iteratable;

import javax.annotation.CheckReturnValue;

/**
 *
 * @author Ollie
 */
public final class Iterables {

    private Iterables() {
    }

    public static int count(final Iterable<?> iterable) {
        final OptionalInt count = maybeCount(iterable);
        return count.isPresent()
                ? count.getAsInt()
                : doCount(iterable, o -> true);
    }

    public static int doCount(final Iterable<?> iterable) {
        return doCount(iterable, o -> true);
    }

    public static <T> int doCount(final Iterable<T> iterable, final Predicate<? super T> predicate) {
        int size = 0;
        for (final T element : iterable) {
            if (predicate.test(element)) {
                size += 1;
            }
        }
        return size;
    }

    public static OptionalInt maybeCount(final Iterable<?> iterable) {
        if (iterable instanceof Iteratable) {
            return OptionalInt.of(((Iteratable) iterable).count());
        } else if (iterable instanceof java.util.Collection) {
            return OptionalInt.of(((java.util.Collection) iterable).size());
        } else if (!iterable.iterator().hasNext()) {
            return OptionalInt.of(0);
        } else {
            return OptionalInt.empty();
        }
    }

    public static boolean containsAny(final Collection<?> container, final Iterable<?> target) {
        for (final Object object : target) {
            if (container.contains(object)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsAll(final Collection<?> container, final Iterable<?> iterable) {
        for (final Object object : iterable) {
            if (!container.contains(object)) {
                return false;
            }
        }
        return true;
    }

    public static <V> java.util.Collection<V> toCollection(final Iterable<V> iterable) {
        if (iterable instanceof java.util.Collection) {
            return (java.util.Collection<V>) iterable;
        } else {
            return new IterableCollection<>(iterable);
        }
    }

    public static <V1, V2> Iterable<V2> apply(final Iterable<V1> iterable, final Function<? super V1, ? extends V2> function) {
        throw new UnsupportedOperationException(); //TODO map iterable
    }

    @CheckReturnValue
    public static <V> Iterable<V> filter(final Iterable<V> iterable, final Predicate<? super V> predicate) {
        return new FilteredIterable<>(predicate, iterable);
    }

    public static OptionalInt indexOf(final Iterable<?> iterable, final Object target) {
        int index = 0;
        for (final Iterator<?> iterator = iterable.iterator(); iterator.hasNext();) {
            if (Objects.equals(iterator.next(), target)) {
                return OptionalInt.of(index);
            } else {
                index += 1;
            }
        }
        return OptionalInt.empty();
    }

    public static boolean equals(final Iterable<?> iterable1, final Iterable<?> iterable2) {
        if (iterable1 == null || iterable2 == null) {
            return false;
        } else if (iterable1 == iterable2) {
            return true;
        }
        final Iterator<?> i1 = iterable1.iterator();
        final Iterator<?> i2 = iterable2.iterator();
        while (i1.hasNext() && i2.hasNext()) {
            if (!Objects.equals(i1.next(), i2.next())) {
                return false;
            }
        }
        return !(i1.hasNext() || i2.hasNext());
    }

    /**
     *
     * @param iterable
     * @return
     * @see java.util.Arrays#hashCode(java.lang.Object[])
     */
    public static int productHashCode(final Iterable<?> iterable) {
        int hash = 1;
        for (final Object element : iterable) {
            hash = 31 * hash + (element == null ? 0 : element.hashCode());
        }
        return hash;
    }

    public static int sumHashCode(final Iterable<?> iterable) {
        int hash = 0;
        for (final Object element : iterable) {
            hash += (element == null ? 0 : element.hashCode());
        }
        return hash;
    }

    public static String safelyToString(final Iterable<?> iterable, final Object caller) {
        final StringBuilder sb = new StringBuilder();
        final Iterator<?> iterator = iterable.iterator();
        if (iterator.hasNext()) {
            sb.append('[');
        } else {
            return "[]";
        }
        while (iterator.hasNext()) {
            final Object next = iterator.next();
            if (Objects.equals(next, caller)) {
                sb.append("(this)");
            } else {
                sb.append(next);
            }
            if (iterator.hasNext()) {
                sb.append(',');
            }
        }
        sb.append(']');
        return sb.toString();
    }

    private static final class IterableCollection<V> extends AbstractCollection<V> {

        private final Iterable<V> iterable;

        IterableCollection(final Iterable<V> iterable) {
            this.iterable = iterable;
        }

        @Override
        public Iterator<V> iterator() {
            return iterable.iterator();
        }

        @Override
        public int size() {
            int size = 0;
            for (final V element : this) {
                size += 1;
            }
            return size;
        }

    }

    private static final class FilteredIterable<V> implements Iterable<V> {

        private final Predicate<? super V> predicate;
        private final Iterable<V> source;

        FilteredIterable(final Predicate<? super V> predicate, final Iterable<V> source) {
            this.predicate = predicate;
            this.source = source;
        }

        @Override
        public Iterator<V> iterator() {
            return new Iterator<V>() {

                final Iterator<V> iterator = source.iterator();
                V next;
                boolean hasNext;

                @Override
                public boolean hasNext() {
                    while (iterator.hasNext()) {
                        final V v = iterator.next();
                        if (predicate.test(v)) {
                            hasNext = true;
                            next = v;
                            return true;
                        }
                    }
                    return false;
                }

                @Override
                public V next() {
                    if (!hasNext) {
                        throw new NoSuchElementException();
                    }
                    hasNext = false;
                    return next;
                }

            };
        }

    }

}
