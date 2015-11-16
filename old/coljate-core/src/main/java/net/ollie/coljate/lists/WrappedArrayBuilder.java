package net.ollie.coljate.lists;

/**
 *
 * @author Ollie
 */
public abstract class WrappedArrayBuilder<V>
        extends Array.Abstract<V> {

    @Override
    public Array.Mutable<V> mutableCopy() {
        return MutableWrappedArray.copy(this);
    }

}
