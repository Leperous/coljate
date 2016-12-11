package net.coljate.set.lazy;

import net.coljate.set.Set;
import net.coljate.set.impl.DelegatedSet;

/**
 *
 * @author Ollie
 */
public class FakeLazySet<T> extends DelegatedSet<T> implements LazySet<T> {

    public FakeLazySet(final Set<? extends T> set) {
        super(set);
    }

}
