package net.coljate.set.impl;

import com.hazelcast.core.ISet;

/**
 *
 * @author ollie
 */
public class HazelcastSet<T> extends MutableWrappedSet<T> {

    public HazelcastSet(final ISet<T> delegate) {
        super(delegate);
    }

}
