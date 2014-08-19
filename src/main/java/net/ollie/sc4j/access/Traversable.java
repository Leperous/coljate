package net.ollie.sc4j.access;

import net.ollie.sc4j.Collection;

import javax.annotation.CheckForNull;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import java.util.function.Predicate;

import net.ollie.sc4j.imposed.Mutability;

/**
 * @author Ollie
 */
public interface Traversable<V>
        extends Collection<V> {

    @CheckForNull
    V head();

    @Nonnull
    Traversable<V> tail();

    @CheckReturnValue
    @Nonnull
    Traversable<V> filter(@Nonnull Predicate<? super V> predicate);

    @Override
    default V findOrElse(Predicate<? super V> predicate, V defaultValue) {
        Traversable<V> tail = this;
        V head;
        while ((head = tail.head()) != null) {
            if (predicate.test(head)) {
                return head;
            }
            tail = tail.tail();
        }
        return defaultValue;
    }

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
        Traversable.Immutable<V> filter(@Nonnull Predicate<? super V> predicate);

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
        default Traversable.Empty<V> filter(final Predicate<? super V> predicate) {
            return this;
        }

        @Override
        default Traversable.Empty<V> tail() {
            return this;
        }

        @Override
        default V findOrElse(final Predicate<? super V> predicate, V defaultValue) {
            return Collection.Empty.super.findOrElse(predicate, defaultValue);
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
