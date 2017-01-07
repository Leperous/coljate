package net.coljate.set.primitive;

import net.coljate.collection.primitive.TroveDoubleCollection;
import net.coljate.set.MutableSet;

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

    public TDoubleSet mutableTroveCopy() {
        return this.mutableTroveCopy(TDoubleHashSet::new);
    }

    @Override
    public MutableSet<Double> mutableCopy() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ImmutableTroveDoubleSet immutableCopy() {
        return new ImmutableTroveDoubleSet(this.mutableTroveCopy());
    }

}
