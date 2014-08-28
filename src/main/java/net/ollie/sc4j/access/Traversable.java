package net.ollie.sc4j.access;

import java.util.function.Predicate;

import net.ollie.sc4j.Collection;
import net.ollie.sc4j.imposed.Mutability;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 * An element {@code V} can be accessed through inspecting the head or tail elements.
 *
 * This interface introduces the {@link #stream} method.
 *
 * @author Ollie
 */
public interface Traversable<V>
        extends Collection<V> {

    @CheckForNull
    V head();

    @Nonnull
    Traversable<V> tail();

    Traversable.Mutable<V> mutableCopy();

    Traversable.Immutable<V> immutableCopy();

    @javax.annotation.concurrent.NotThreadSafe
    interface Mutable<V>
            extends Traversable<V>, Mutability.Mutable {

    }

    @javax.annotation.concurrent.Immutable
    interface Immutable<V>
            extends Traversable<V>, Mutability.Immutable {

        @Override
        Traversable.Immutable<V> tail();

        @Override
        default Traversable.Immutable<V> immutableCopy() {
            return this;
        }

    }

    @javax.annotation.concurrent.Immutable
    interface Empty<V>
            extends Traversable.Immutable<V>, Collection.Empty<V> {

        @Override
        default V head() {
            return null;
        }

        @Override
        default Traversable.Empty<V> tail() {
            return this;
        }

    }

    @javax.annotation.concurrent.Immutable
    interface Singleton<V>
            extends Traversable.Immutable<V>, Collection.Singleton<V> {

        @Override
        default V head() {
            return this.value();
        }

        @Override
        Traversable.Empty<V> tail();

        @Override
        default V findOrElse(Predicate<? super V> predicate, V defaultValue) {
            return Collection.Singleton.super.findOrElse(predicate, defaultValue);
        }

    }

}
