package net.ollie.coljate.set;

import net.ollie.coljate.ObjectCollectionBuilder;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MutableWrappedHashSetTest extends MutableSetTest<Object> implements ObjectCollectionBuilder {
    
    @Override
    public MutableWrappedHashSet<Object> create() {
        return this.createFrom();
    }
    
    @Override
    public MutableWrappedHashSet<Object> createFrom(final Object... objects) {
        return MutableWrappedHashSet.copyOf(objects);
    }
    
    @Override
    protected Object randomValue() {
        return new Object();
    }
    
}
