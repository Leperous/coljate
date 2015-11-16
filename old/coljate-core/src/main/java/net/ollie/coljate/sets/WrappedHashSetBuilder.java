package net.ollie.coljate.sets;

/**
 *
 * @author Ollie
 */
public abstract class WrappedHashSetBuilder<V> extends Set.Abstract<V> {

    @Override
    public Set.Mutable<V> mutableCopy() {
        return MutableWrappedHashSet.copy(this);
    }

    @Override
    public Set.Immutable<V> immutableCopy() {
        return ImmutableWrappedHashSet.copy(this);
    }

}
