package net.ollie.sc4j.access;

import java.util.function.Predicate;

import net.ollie.sc4j.Collection;
import net.ollie.sc4j.imposed.Mutability;

/**
 *
 * @author Ollie
 */
public interface Traversable<V>
        extends Collection<V> {

    V head();

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
        default Traversable.Immutable<V> immutableCopy() {
            return this;
        }

    }

}
