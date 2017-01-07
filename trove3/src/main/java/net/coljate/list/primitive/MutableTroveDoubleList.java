package net.coljate.list.primitive;

import net.coljate.list.ListIterator;

import gnu.trove.list.TDoubleList;

/**
 *
 * @author Ollie
 */
public class MutableTroveDoubleList
        extends TroveDoubleList
        implements MutableDoubleList {

    private final TDoubleList list;

    public MutableTroveDoubleList(final TDoubleList list) {
        super(list);
        this.list = list;
    }

    @Override
    public void prefix(final double d) {
        list.insert(0, d);
    }

    @Override
    public void suffix(final double d) {
        list.add(d);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean removeFirst(double d) {
        return list.remove(d);
    }

    @Override
    public ListIterator<Double> reverseIterator() {
        throw new UnsupportedOperationException(); //TODO
    }

}
