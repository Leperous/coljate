package net.coljate.list;

import java.util.Comparator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ollie
 */
public interface SortedArrayTest<T> extends ArrayTest<T> {

    @Override
    default SortedArray<T> create(final java.util.List<T> elements) {
        return this.create(elements, new IndexComparator<>(elements));
    }

    SortedArray<T> create(java.util.List<T> elements, Comparator<? super T> comparator);

    @Override
    default SortedArray<T> create() {
        return this.create(emptyList());
    }

    @Test
    default void testComparator() {
        final T o1 = this.createObject(), o2 = this.createObject();
        final Comparator<T> comparator = (l, r) -> {
            if (l == o1) {
                return +1;
            }
            if (l == o2) {
                return -1;
            }
            return 0;
        };
        final SortedArray<T> array = this.create(java.util.Arrays.asList(o1, o2), comparator);
        assertThat(array.comparator(), is(comparator));
        assertThat(array.get(0), is(o2));
        assertThat(array.get(1), is(o1));
    }

    class IndexComparator<T> implements Comparator<T> {

        private final java.util.List<T> list;

        IndexComparator(final java.util.List<T> list) {
            this.list = list;
        }

        @Override
        public int compare(final Object o1, final Object o2) {
            return Integer.compare(list.indexOf(o1), list.indexOf(o2));
        }

    }

}
