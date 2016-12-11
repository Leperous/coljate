package net.coljate.set.lazy;

import net.coljate.set.Set;
import net.coljate.set.impl.DelegatedSet;

/**
 *
 * @author Ollie
 */
public class LazySetWrapper<T> extends DelegatedSet<T> implements LazySet<T> {

    private static final LazySetWrapper EMPTY = new LazySetWrapper(Set.of());

    public static <T> LazySetWrapper<T> of() {
        return EMPTY;
    }

    public LazySetWrapper(final Set<? extends T> set) {
        super(set);
    }

}
