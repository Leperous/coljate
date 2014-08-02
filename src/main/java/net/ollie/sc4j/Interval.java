package net.ollie.sc4j;

import net.ollie.sc4j.imposed.Ordered;

import javax.annotation.CheckForNull;

/**
 *
 * @author Ollie
 * @param <V> a non-parametric type.
 */
public interface Interval<V>
        extends Ordered<V> {

    boolean minInclusive();

    boolean maxInclusive();

    @CheckForNull
    V last();

}
