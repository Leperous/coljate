package net.ollie.sc4j.imposed;


import javax.annotation.CheckForNull;

/**
 * Values are ordered in some manner, for example by insertion order.
 *
 * @author Ollie
 * @see Sorted
 */
public interface Ordered<V> {

    @CheckForNull
    V first();

}
