package net.ollie.coljate.sets;

import java.util.EnumSet;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import net.ollie.coljate.Set;
import net.ollie.coljate.streams.DefaultStream;
import net.ollie.coljate.utils.EnumSets;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public class MutableWrappedEnumSet<E extends Enum<E>>
        extends AbstractMutableWrappedSet<E> {

    @Nonnull
    public static <E extends Enum<E>> MutableWrappedEnumSet<E> none(final Class<E> clazz) {
        return view(EnumSet.noneOf(clazz));
    }

    @Nonnull
    public static <E extends Enum<E>> MutableWrappedEnumSet<E> all(final Class<E> clazz) {
        return view(EnumSet.allOf(clazz));
    }

    @Nonnull
    public static <E extends Enum<E>> MutableWrappedEnumSet<E> create(final E element) {
        return view(EnumSet.of(element));
    }

    @Nonnull
    public static <E extends Enum<E>> MutableWrappedEnumSet<E> copy(final EnumSet<E> set) {
        return view(set.clone());
    }

    @Nonnull
    public static <E extends Enum<E>> MutableWrappedEnumSet<E> view(final EnumSet<E> set) {
        return new MutableWrappedEnumSet<>(set);
    }

    @Nonnull
    public static <E extends Enum<E>> Collector<E, ?, MutableWrappedEnumSet<E>> collector(final Class<E> clazz) {
        return new EnumSetCollector<>(clazz);
    }

    private final EnumSet<E> delegate;
    private transient Class<E> elementType;

    protected MutableWrappedEnumSet(final EnumSet<E> delegate) {
        this.delegate = delegate;
    }

    public Class<E> elementType() {
        return elementType == null ? (elementType = EnumSets.getElementType(delegate)) : elementType;
    }

    @Override
    protected EnumSet<E> delegate() {
        return delegate;
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
        return DefaultStream.create(this, () -> MutableWrappedEnumSet.collector(elementType));
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
