package net.coljate;

import net.coljate.counter.Counter;
import net.coljate.counter.impl.CommonsBagCounter;

/**
 * Extensible utility class for Commons Collections.
 *
 * @author Ollie
 * @since 1.0
 */
public class Commons {

    protected Commons() {
    }

    public static <T> Counter<T> viewOf(final org.apache.commons.collections4.Bag<T> bag) {
        return new CommonsBagCounter<>(bag);
    }

}
