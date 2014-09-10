package net.ollie.coljate;

import java.util.Iterator;

import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.utils.numeric.NonNegativeInteger;

/**
 *
 *
 * @author Ollie
 * @see AbstractDelegatedFinite
 */
public abstract class AbstractNativelyDelegatedStreamable<V>
        extends AbstractNativelyDelegatedTraversable<V>
        implements Streamable<V> {

    @Override
    public NonNegativeInteger count() {
        return NonNegativeInteger.of(this.delegate().size());
    }

    @Override
    public Object[] toRawArray() {
        return this.delegate().toArray();
    }

    @Override
    public Iterator<V> iterator() {
        return this.delegate().iterator();
    }

}
