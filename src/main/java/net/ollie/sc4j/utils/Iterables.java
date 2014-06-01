package net.ollie.sc4j.utils;

import java.util.Iterator;
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

    public static int countAll(final Iterable<?> iterable) {
        final OptionalInt count = maybeCount(iterable);
        return count.isPresent()
                ? count.getAsInt()
                : doCount(iterable, Object -> true);
    }

    public static <T> int doCount(final Iterable<T> iterable) {
        return doCount(iterable, Object -> true);
    }

    public static <T> int doCount(final Iterable<T> iterable, final Predicate<? super T> predicate) {
        int size = 0;
        final Iterator<T> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            if (predicate.test(iterator.next())) {
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
            throw new UnsupportedOperationException(); //TODO iterable -> collection
        }
    }

    public static <V1, V2> Iterable<V2> apply(final Iterable<V1> iterable, final Function<? super V1, ? extends V2> function) {
        throw new UnsupportedOperationException(); //TODO map iterable
    }

    @CheckReturnValue
    public static <V> Iterable<V> filter(final Iterable<V> iterable, final Predicate<? super V> predicate) {
        throw new UnsupportedOperationException(); //TODO filter iterable
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

}
