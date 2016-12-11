package net.coljate.set.lazy;

import net.coljate.set.Set;
import net.coljate.set.impl.DelegatedSet;
import net.coljate.set.impl.EmptySet;

/**
 *
 * @author Ollie
 */
public class LazySetWrapper<T> extends DelegatedSet<T> implements LazySet<T> {

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static final LazySetWrapper EMPTY = new LazySetWrapper(Set.of());

    @SuppressWarnings("unchecked")
    public static <T> LazySetWrapper<T> of() {
        return EMPTY;
    }

    @SuppressWarnings("unchecked")
    public static <T> LazySet<T> of(final Set<? extends T> set) {
        if (set instanceof LazySet) {
            return (LazySet<T>) set;
        } else if (set instanceof EmptySet) {
            return of();
        } else {
            return new LazySetWrapper<>(set);
        }
    }

    protected LazySetWrapper(final Set<? extends T> set) {
        super(set);
    }

}
