package net.ollie.sc4j.access;

import java.util.function.Predicate;

import net.ollie.sc4j.Collection;
import net.ollie.sc4j.imposed.Mutability;

import javax.annotation.CheckForNull;

/**
 *
 * @author Ollie
 */
public interface Traversable<V>
        extends Collection<V> {

    @CheckForNull
    V head();

    @CheckForNull
    Traversable<V> tail();

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

    interface Mutable<V>
            extends Traversable<V>, Mutability.Mutable {

    }

    interface Immutable<V>
            extends Traversable<V>, Mutability.Immutable {

        @Override
        Traversable.Immutable<V> tail();

        @Override
        default Traversable.Immutable<V> immutableCopy() {
            return this;
        }

    }

    interface Empty<V>
            extends Collection.Empty<V>, Traversable.Immutable<V> {

        @Override
        default V head() {
            return null;
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

}
