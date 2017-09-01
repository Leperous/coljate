package net.coljate.collection.impl;

import com.hazelcast.core.ICollection;

/**
 *
 * @author ollie
 */
public class HazelcastCollection<T> extends MutableWrappedCollection<T> {

    public HazelcastCollection(final ICollection<T> delegate) {
        super(delegate);
    }
    
}
