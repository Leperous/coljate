package net.coljate.set.primitive;

import net.coljate.collection.primitive.TroveDoubleCollection;
import net.coljate.collection.primitive.TroveDoubleIterator;

import gnu.trove.impl.unmodifiable.TUnmodifiableDoubleSet;
import gnu.trove.iterator.TDoubleIterator;
import gnu.trove.set.TDoubleSet;
import gnu.trove.set.hash.TDoubleHashSet;

/**
 *
 * @author Ollie
 */
public class ImmutableTroveDoubleSet
        extends TroveDoubleSet
        implements ImmutableDoubleSet {

    public static ImmutableTroveDoubleSet copyIntoHashSet(final TroveDoubleCollection collection) {
        final TDoubleSet set = collection.mutableTroveCopy(TDoubleHashSet::new);
        return new ImmutableTroveDoubleSet(new TUnmodifiableDoubleSet(set));
    }

    protected ImmutableTroveDoubleSet(final TUnmodifiableDoubleSet set) {
        super(set);
    }

    @Override
    @Deprecated
    public ImmutableTroveDoubleSet immutableCopy() {
        return this;
    }

    @Override
    public ImmutableTroveDoubleSetIterator iterator() {
        return new ImmutableTroveDoubleSetIterator(this.troveIterator());
    }

    public static class ImmutableTroveDoubleSetIterator extends TroveDoubleIterator implements UnmodifiableDoubleIterator {

        ImmutableTroveDoubleSetIterator(final TDoubleIterator iterator) {
            super(iterator);
        }

    }

}
