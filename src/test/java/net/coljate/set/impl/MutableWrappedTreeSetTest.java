package net.coljate.set.impl;

import java.util.Comparator;

import net.coljate.set.MutableSetTest;
import net.coljate.util.Arrays;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MutableWrappedTreeSetTest extends MutableSetTest {

    @Override
    protected <T> MutableWrappedTreeSet<T> create(final T... elements) {
        return MutableWrappedTreeSet.copyOf(new ArrayIndexComparator<>(elements), elements);
    }

    private static final class ArrayIndexComparator<T> implements Comparator<T> {

        private final T[] array;

        ArrayIndexComparator(final T[] array) {
            this.array = array;
        }

        @Override
        public int compare(final T o1, final T o2) {
            return Integer.compare(Arrays.indexOf(array, o1), Arrays.indexOf(array, o2));
        }

    }

}
