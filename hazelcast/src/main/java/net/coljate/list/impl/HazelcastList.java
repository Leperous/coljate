package net.coljate.list.impl;

import com.hazelcast.core.IList;

/**
 *
 * @author ollie
 */
public class HazelcastList<T> extends MutableWrappedList<T> {

    public HazelcastList(final IList<T> delegate) {
        super(delegate);
    }

}
