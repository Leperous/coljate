package net.ollie.sc4j.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.OptionalInt;

import net.ollie.sc4j.utils.Iterables;

/**
 *
 * @author Ollie
 */
public final class ArrayLists {

    private ArrayLists() {
    }

    public static <V> ArrayList<V> copy(final Iterable<? extends V> iterable) {
        if (iterable instanceof Collection) {
            return new ArrayList<>((Collection<? extends V>) iterable);
        } else {
            final Iterator<? extends V> iterator = iterable.iterator();
            final OptionalInt size = iterator.hasNext() ? Iterables.maybeCount(iterable) : OptionalInt.of(0);
            final ArrayList<V> newList = new ArrayList<>(size.isPresent() ? size.getAsInt() : 8);
            while (iterator.hasNext()) {
                newList.add(iterator.next());
            }
            return new ArrayList<>(newList);
        }
    }

    public static <V> ArrayList<V> castOrCopy(final Iterable<V> list) {
        if (list instanceof ArrayList) {
            return (ArrayList<V>) list;
        } else {
            return copy(list);
        }
    }

}
