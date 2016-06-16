package net.ollie.coljate.theory;

import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.function.Function;

import net.ollie.coljate.theory.feature.ConstantContains;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Ollie
 * @param <T>
 */
public interface Finite<@Nullable T> extends Traversable<T> {

    /**
     *
     * @return the number of non-null elements in this collection.
     */
    int count();

    @Override
    Finite<T> tail();

    @NonNull
    default Object[] toArray() {
        final Object[] array = new Object[this.count()];
        int index = 0;
        for (final Object object : this) {
            array[index++] = object;
        }
        return array;
    }

    static boolean sequenceEquals(final Finite<?> left, final Finite<?> right) {
        if (left.count() != right.count()) {
            return false;
        }
        for (final Iterator<?> l = left.iterator(), r = right.iterator(); l.hasNext() && r.hasNext();) {
            if (!Objects.equals(l.next(), r.next())) {
                return false;
            }
        }
        return true;
    }

    static int productHash(final Finite<?> list) {
        int hashCode = 1;
        for (final Object element : list) {
            hashCode = 31 * hashCode + (element == null ? 0 : element.hashCode());
        }
        return hashCode;
    }

    static boolean elementsEqual(final Finite<?> c1, final Finite<?> c2) {
        if (c1.count() != c2.count()) {
            return false;
        }
        final Finite<?> slow, fast;
        if (c1 instanceof ConstantContains) {
            fast = c1;
            slow = c2;
        } else {
            fast = c2;
            slow = c1;
        }
        for (final Object e1 : slow) {
            if (!fast.contains(e1)) {
                return false;
            }
        }
        return true;
    }

    static int sumHash(final Finite<?> finite) {
        return finite.serialStream().mapToInt(Object::hashCode).sum();
    }

    static String toString(final Finite<?> finite) {
        return toString(finite, element -> element == finite ? "(collection)" : String.valueOf(element));
    }

    static <T> String toString(final Finite<T> finite, final Function<? super T, ? extends CharSequence> eachToString) {
        final StringJoiner joiner = new StringJoiner("[", ",", "]");
        finite.forEach(element -> joiner.add(eachToString.apply(element)));
        return joiner.toString();
    }

}
