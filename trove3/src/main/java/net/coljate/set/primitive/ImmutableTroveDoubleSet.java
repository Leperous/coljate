package net.coljate.set.primitive;

import net.coljate.collection.primitive.TroveDoubleIterator;
import net.coljate.set.ImmutableSet;
import net.coljate.util.iterator.UnmodifiableIterator;

import gnu.trove.iterator.TDoubleIterator;
import gnu.trove.set.TDoubleSet;

/**
 *
 * @author Ollie
 */
public class ImmutableTroveDoubleSet
        extends TroveDoubleSet
        implements ImmutableSet<Double> {

    protected ImmutableTroveDoubleSet(final TDoubleSet set) {
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
