package net.coljate.set.primitive;

import net.coljate.collection.primitive.TroveDoubleCollection;
import net.coljate.collection.primitive.TroveDoubleIterator;
import net.coljate.set.ImmutableSet;
import net.coljate.util.iterator.UnmodifiableIterator;

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
        implements ImmutableSet<Double> {

    public static ImmutableTroveDoubleSet copyIntoHashSet(final TroveDoubleCollection collection) {
        final TDoubleSet set = collection.mutableTroveCopy(TDoubleHashSet::new);
        return new ImmutableTroveDoubleSet(new TUnmodifiableDoubleSet(set));
    }

    protected ImmutableTroveDoubleSet(final TUnmodifiableDoubleSet set) {
        super(set);
    }

    @Override
    public ImmutableTroveDoubleSetIterator iterator() {
        return new ImmutableTroveDoubleSetIterator(this.troveIterator());
    }

    public static class ImmutableTroveDoubleSetIterator extends TroveDoubleIterator implements UnmodifiableIterator<Double> {

        ImmutableTroveDoubleSetIterator(final TDoubleIterator iterator) {
            super(iterator);
        }

    }

}
