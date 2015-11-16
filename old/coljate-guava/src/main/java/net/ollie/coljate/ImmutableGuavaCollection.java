package net.ollie.coljate;

import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.lists.MutableWrappedArray;
import net.ollie.coljate.streams.DefaultStream;
import net.ollie.coljate.utils.Arrays;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import java.io.Serializable;

/**
 *
 * @author Ollie
 */
public class ImmutableGuavaCollection<V>
        extends Streamable.Abstract<V>
        implements Streamable.Immutable<V>, Serializable {

    private static final long serialVersionUID = 1L;

    @SafeVarargs
    public static <V> ImmutableGuavaCollection<V> create(final V... elements) {
        return build(ImmutableList.<V>builder().addAll(Arrays.iterator(elements)));
    }

    public static <V> ImmutableGuavaCollection<V> copy(final Iterable<? extends V> iterable) {
        return view(ImmutableList.copyOf(iterable));
    }

    public static <V> ImmutableGuavaCollection<V> build(final ImmutableCollection.Builder<? extends V> builder) {
        return view(builder.build());
    }

    public static <V> ImmutableGuavaCollection<V> view(final ImmutableCollection<? extends V> collection) {
        return new ImmutableGuavaCollection<>(collection);
    }

    private final ImmutableCollection<? extends V> underlying;

    protected ImmutableGuavaCollection(final ImmutableCollection<? extends V> underlying) {
        this.underlying = underlying;
    }

    @Override
    public Streamable.Immutable<V> tail() {
        throw new UnsupportedOperationException("tail not supported yet!");
    }

    @Override
    public Streamable.Mutable<V> mutableCopy() {
        return MutableWrappedArray.copy(underlying);
    }

    @Override
    public boolean isEmpty() {
        return underlying.isEmpty();
    }

    @Override
    public boolean contains(final Object object) {
        return underlying.contains(object);
    }

    @Override
    public Stream<V, ? extends Streamable<V>> stream() {
        return DefaultStream.create(underlying.iterator(), DefaultStreamable::collector);
    }

}
