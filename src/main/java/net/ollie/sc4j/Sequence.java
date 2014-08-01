package net.ollie.sc4j;

import java.util.function.Function;
import java.util.function.Predicate;

import net.ollie.sc4j.access.Traversable;
import net.ollie.sc4j.imposed.Ordered;

/**
 * A sorted collection of objects, that may or may not be infinite in length.
 *
 * @author Ollie
 */
public interface Sequence<V>
        extends Ordered<V>, Traversable<V> {

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
            extends Sequence<V>, Traversable.Mutable<V> {

    }

    interface Immutable<V>
            extends Sequence<V>, Traversable.Immutable<V> {

        @Override
        Sequence.Immutable<V> tail();

        @Override
        default Sequence.Immutable<V> immutableCopy() {
            return this;
        }

    }

    interface Empty<V>
            extends Ordered.Empty<V>, Sequence.Immutable<V>, Traversable.Empty<V> {

        @Override
        default V head() {
            return Traversable.Empty.super.head();
        }

        @Override
        default <V2> Sequence.Empty<V2> map(Function<? super V, ? extends V2> function) {
            return (Sequence.Empty<V2>) this;
        }

        @Override
        default Sequence.Empty<V> filter(final Predicate<? super V> predicate) {
            return this;
        }

        @Override
        default Sequence.Empty<V> tail() {
            return this;
        }

        @Override
        default Sequence.Empty<V> immutableCopy() {
            return this;
        }

        @Override
        default V first() {
            return null;
        }

        @Override
        default V lastOrElse(final Predicate<? super V> predicate, final V defaultValue) {
            return defaultValue;
        }

    }

}
