package net.ollie.sc4j;

import net.ollie.sc4j.imposed.Ordered;

/**
 *
 * @author Ollie
 */
public interface Interval<V>
        extends Ordered<V> {

    boolean minInclusive();

    boolean maxInclusive();

    V last();

}
