package net.ollie.coljate.sets;

/**
 *
 * @author Ollie
 */
public abstract class WrappedTreeSetBuilder<V> extends SortedSet.Abstract<V> {

    @Override
    public SortedSet.Immutable<V> immutableCopy() {
        return ImmutableWrappedTreeSet.copy(this, this.sorter());
    }

    protected SortedSet.Empty<V> emptyCopy() {
        return ImmutableWrappedTreeSet.create(this.sorter());
    }

    @Override
    public SortedSet.Mutable<V> mutableCopy() {
        return MutableWrappedTreeSet.copy(this, this.sorter());
    }

}
