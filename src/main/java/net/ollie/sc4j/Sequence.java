package net.ollie.sc4j;

import java.util.function.Function;
import java.util.function.Predicate;

import net.ollie.sc4j.imposed.Sorted;

/**
 *
 * @author Ollie
 */
public interface Sequence<V>
        extends Sorted<V> {

    @Override
    Sequence<V> tail();

    @Override
    <V2> Sequence<V2> map(Function<? super V, ? extends V2> function);

    @Override
    Sequence<V> filter(Predicate<? super V> predicate);

    @Override
    Sequence.Mutable<V> mutableCopy();

    @Override
    Sequence.Immutable<V> immutableCopy();

    interface Mutable<V>
            extends Sequence<V>, Sorted.Mutable<V> {

    }

    interface Immutable<V>
            extends Sequence<V>, Sorted.Immutable<V> {

        @Override
        Sequence.Immutable<V> tail();

        @Override
        default Sequence.Immutable<V> immutableCopy() {
            return this;
        }

    }

    interface Empty<V>
            extends net.ollie.sc4j.imposed.Empty<V>, Sequence.Immutable<V> {

        @Override
        @SuppressWarnings("unchecked")
        default <V2> Sequence.Empty<V2> map(Function<? super V, ? extends V2> function) {
            return (Sequence.Empty<V2>) this;
        }

        @Override
        default Sequence.Empty<V> filter(final Predicate<? super V> predicate) {
            return this;
        }

    }

}
