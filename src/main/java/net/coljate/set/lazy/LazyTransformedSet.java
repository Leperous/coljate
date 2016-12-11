package net.coljate.set.lazy;

import java.util.Iterator;
import java.util.function.Function;

import net.coljate.collection.lazy.LazyTransformedCollection;
import net.coljate.set.MutableSet;
import net.coljate.set.Set;
import net.coljate.util.Iterators;

/**
 *
 * @author Ollie
 */
public class LazyTransformedSet<F, T>
        extends LazyTransformedCollection<F, T>
        implements LazySet<T> {

    public LazyTransformedSet(final Set<F> set, final Function<? super F, ? extends T> transformation) {
        super(set, transformation);
    }

    @Override
    public Iterator<T> iterator() {
        final MutableSet<T> seen = MutableSet.createHashSet(LazyTransformedSet.this.count());
        return Iterators.filter(super.iterator(), seen::add);
    }

}
