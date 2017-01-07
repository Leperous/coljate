package net.coljate.set.primitive;

import gnu.trove.impl.unmodifiable.TUnmodifiableDoubleSet;
import gnu.trove.set.TDoubleSet;

/**
 *
 * @author Ollie
 */
public class MutableTroveDoubleSet
        extends TroveDoubleSet
        implements MutableDoubleSet {

    private final TDoubleSet set;

    protected MutableTroveDoubleSet(final TDoubleSet set) {
        super(set);
        if (set instanceof TUnmodifiableDoubleSet) {
            throw new IllegalArgumentException("Set is not modifiable!");
        }
        this.set = set;
    }

    @Override
    public boolean add(final double d) {
        return set.add(d);
    }

    @Override
    public boolean remove(final double d) {
        return set.remove(d);
    }

    @Override
    public void clear() {
        set.clear();
    }

}
