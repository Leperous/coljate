package net.ollie.sc4j;

import net.ollie.sc4j.imposed.Distinctness.Unique;
import net.ollie.sc4j.imposed.Ordered;

import javax.annotation.CheckForNull;

/**
 *
 * @author Ollie
 */
public interface Interval<V>
        extends Collection<V>, Ordered<V>, Unique<V> {

    boolean firstInclusive();

    boolean lastInclusive();

    @CheckForNull
    V last();

}
