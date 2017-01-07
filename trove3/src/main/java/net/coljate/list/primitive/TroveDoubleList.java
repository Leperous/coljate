package net.coljate.list.primitive;

import net.coljate.collection.primitive.TroveDoubleIterator;
import net.coljate.collection.primitive.TroveDoubleCollection;
import net.coljate.list.ImmutableList;
import net.coljate.list.MutableList;

import gnu.trove.iterator.TDoubleIterator;
import gnu.trove.list.TDoubleList;
import gnu.trove.list.array.TDoubleArrayList;

/**
 *
 * @author Ollie
 */
public class TroveDoubleList
        extends TroveDoubleCollection
        implements DoubleList {

    private final TDoubleList list;

    public TroveDoubleList(final TDoubleList list) {
        super(list);
        this.list = list;
    }

    @Override
    public TDoubleList mutableTroveCopy() {
        return this.mutableTroveCopy(TDoubleArrayList::new);
    }

    @Override
    public DoubleListIterator iterator() {
        return new TroveDoubleListIterator(list.iterator());
    }

    @Override
    public Double last() {
        return list.get(list.size() - 1);
    }

    @Override
    public MutableList<Double> mutableCopy() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ImmutableList<Double> immutableCopy() {
        throw new UnsupportedOperationException();
    }

    private final class TroveDoubleListIterator extends TroveDoubleIterator implements DoubleListIterator {

        private int index;

        TroveDoubleListIterator(final TDoubleIterator iterator) {
            super(iterator);
        }

        @Override
        public double nextDouble() {
            final double next = super.nextDouble();
            index++;
            return next;
        }

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public double previousDouble() {
            return list.get(--index);
        }

    }

}
