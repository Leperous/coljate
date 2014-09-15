package net.ollie.coljate.sets;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import net.ollie.coljate.sets.EnumSet.EnumUniverse;
import net.ollie.coljate.streams.DefaultStream;
import net.ollie.coljate.utils.Enums;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public class MutableWrappedEnumSet<E extends Enum<E>>
        extends AbstractMutableWrappedSet<E>
        implements EnumSet<E> {

    @Nonnull
    public static <E extends Enum<E>> MutableWrappedEnumSet<E> none(final Class<E> clazz) {
        return view(java.util.EnumSet.noneOf(clazz));
    }

    @Nonnull
    public static <E extends Enum<E>> MutableWrappedEnumSet<E> all(final Class<E> clazz) {
        return view(java.util.EnumSet.allOf(clazz));
    }

    @Nonnull
    public static <E extends Enum<E>> MutableWrappedEnumSet<E> create(final E element) {
        return view(java.util.EnumSet.of(element));
    }

    @Nonnull
    public static <E extends Enum<E>> MutableWrappedEnumSet<E> copy(final Class<E> clazz, final Iterable<E> iterable) {
        final java.util.EnumSet<E> set = java.util.EnumSet.noneOf(clazz);
        iterable.forEach(e -> set.add(e));
        return view(set);
    }

    @Nonnull
    public static <E extends Enum<E>> MutableWrappedEnumSet<E> copy(final java.util.EnumSet<E> set) {
        return view(set.clone());
    }

    @Nonnull
    public static <E extends Enum<E>> MutableWrappedEnumSet<E> view(final java.util.EnumSet<E> set) {
        return new MutableWrappedEnumSet<>(set);
    }

    @Nonnull
    public static <E extends Enum<E>> Collector<E, ?, MutableWrappedEnumSet<E>> collector(final Class<E> clazz) {
        return new EnumSetCollector<>(clazz);
    }

    private final java.util.EnumSet<E> delegate;
    private transient Class<E> elementType;
    private transient EnumUniverse<E> universe;

    protected MutableWrappedEnumSet(final java.util.EnumSet<E> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Class<E> elementType() {
        return elementType == null ? (elementType = Enums.getElementType(delegate)) : elementType;
    }

    @Override
    protected java.util.EnumSet<E> delegate() {
        return delegate;
    }

    @Override
    public MutableWrappedEnumSet<E> complement() {
        return copy(java.util.EnumSet.complementOf(delegate));
    }

    @Override
    public EnumUniverse<E> universe() {
        return universe == null ? (universe = new EnumUniverse<>(this.elementType())) : universe;
    }

    @Override
    public EnumSet<E> intersection(final FiniteSet<E, EnumUniverse<E>> that) {
        final java.util.EnumSet<E> clone = delegate.clone();
        that.forEach(e -> clone.remove(e));
        return view(clone);
    }

    @Override
    public EnumSet<E> union(final FiniteSet<E, EnumUniverse<E>> that) {
        final java.util.EnumSet<E> clone = delegate.clone();
        that.forEach(e -> clone.add(e));
        return view(clone);
    }

    @Override
    public Set.Mutable<E> mutableCopy() {
        return copy(delegate);
    }

    @Override
    public Set.Immutable<E> immutableCopy() {
        return ImmutableWrappedHashSet.copy(this);
    }

    @Override
    public Stream<E, ? extends MutableWrappedEnumSet<E>> stream() {
        return DefaultStream.create(this, () -> MutableWrappedEnumSet.collector(this.elementType()));
    }

    private static final class EnumSetCollector<E extends Enum<E>>
            extends AbstractSetCollector<E, MutableWrappedEnumSet<E>, MutableWrappedEnumSet<E>> {

        private final Class<E> clazz;

        EnumSetCollector(final Class<E> clazz) {
            this.clazz = clazz;
        }

        @Override
        public Supplier<MutableWrappedEnumSet<E>> supplier() {
            return () -> MutableWrappedEnumSet.none(clazz);
        }

        @Override
        public Function<MutableWrappedEnumSet<E>, MutableWrappedEnumSet<E>> finisher() {
            return Function.identity();
        }

    }

}
