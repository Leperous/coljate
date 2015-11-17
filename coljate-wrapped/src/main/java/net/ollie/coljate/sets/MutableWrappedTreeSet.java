package net.ollie.coljate.sets;

import java.util.Comparator;
import static java.util.Objects.requireNonNull;
import java.util.TreeSet;

import net.ollie.coljate.sets.mixin.GenericMutableWrappedSet;
import net.ollie.coljate.utils.Objects;

/**
 *
 * @author Ollie
 */
public class MutableWrappedTreeSet<T> extends WrappedSortedSet<T> implements MutableSortedSet<T>, GenericMutableWrappedSet<T> {

    public static <T extends Comparable<? super T>> MutableSortedSet<T> viewOf(final TreeSet<T> set) {
        return new MutableWrappedTreeSet<>(set, Objects.firstNonNull(set.comparator(), Comparator::naturalOrder));
    }

    private final java.util.TreeSet<T> delegate;

    MutableWrappedTreeSet(final java.util.TreeSet<T> delegate, final Comparator<? super T> comparator) {
        super(delegate, comparator);
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public java.util.TreeSet<T> delegate() {
        return delegate;
    }

}
