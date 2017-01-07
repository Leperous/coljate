package net.coljate.set.primitive;

import net.coljate.collection.primitive.TroveDoubleCollection;

import gnu.trove.set.TDoubleSet;
import gnu.trove.set.hash.TDoubleHashSet;

/**
 *
 * @author Ollie
 */
public class TroveDoubleSet
        extends TroveDoubleCollection
        implements DoubleSet {

    public TroveDoubleSet(final TDoubleSet set) {
        super(set);
    }

    @Override
    public TDoubleSet mutableTroveCopy() {
        return this.mutableTroveCopy(TDoubleHashSet::new);
    }

    @Override
    public MutableDoubleSet mutableCopy() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public ImmutableTroveDoubleSet immutableCopy() {
        return ImmutableTroveDoubleSet.copyOf(this);
    }

}
