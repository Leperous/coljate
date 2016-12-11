package net.coljate.set;

import java.util.List;

/**
 *
 * @author Ollie
 */
public interface ConcurrentSetTest<T> extends MutableSetTest<T> {

    @Override
    ConcurrentSet<T> create(List<T> elements);

    @Override
    default ConcurrentSet<T> create(final T element) {
        return this.create(java.util.Collections.singletonList(element));
    }

}
