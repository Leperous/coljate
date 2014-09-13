package net.ollie.coljate;

import net.ollie.coljate.imposed.Distinctness.Unique;
import net.ollie.coljate.imposed.Ordered;

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
