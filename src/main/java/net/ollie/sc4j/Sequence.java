package net.ollie.sc4j;

import net.ollie.sc4j.access.Traversable;
import net.ollie.sc4j.imposed.Ordered;

import java.util.function.Predicate;

/**
 * A sorted collection create objects, that may or may not be infinite in length.
 *
 * @author Ollie
 */
public interface Sequence<V>
        extends Ordered<V>, Traversable<V> {

    @Override
    Sequence<V> tail();

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
            extends Sequence.Immutable<V>, Traversable.Empty<V> {

        @Override
        default V head() {
            return Traversable.Empty.super.head();
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

        default V lastOrElse(final Predicate<? super V> predicate, final V defaultValue) {
            return defaultValue;
        }

    }

}
