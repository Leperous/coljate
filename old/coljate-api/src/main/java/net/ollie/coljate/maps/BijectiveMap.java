package net.ollie.coljate.maps;

import net.ollie.coljate.imposed.Cached;
import net.ollie.coljate.imposed.Invertible.Bijective;
import net.ollie.coljate.sets.Set;

/**
 * An invertible one-to-one mapping.
 *
 * @author Ollie
 * @see InvertibleMap
 * @see MultiMap
 */
public interface BijectiveMap<L, R>
        extends Cached<L, R>, Bijective<L, R> {

    @Override
    Set<R> values();

    L inverse(R value);

    @Override
    BijectiveMap<R, L> inverseCopy();

    @Override
    default boolean contains(final Object object) {
        return this.keys().contains(object) || this.values().contains(object);
    }

    @Override
    BijectiveMap.Mutable<L, R> mutableCopy();

    @Override
    BijectiveMap.Immutable<L, R> immutableCopy();

    /**
     * A mutable bi-map.
     *
     * @param <L>
     * @param <R>
     */
    interface Mutable<L, R>
            extends BijectiveMap<L, R>, Cached.Mutable<L, R> {

        @Override
        View<R, L> inverseCopy();

        @Override
        R put(L left, R right) throws DuplicateValueException;

        interface View<L, R>
                extends BijectiveMap.Mutable<L, R> {

        }

    }

    interface Immutable<L, R>
            extends BijectiveMap<L, R>, Cached.Immutable<L, R> {

        @Override
        Set.Immutable<R> values();

        @Override
        BijectiveMap.Immutable<R, L> inverseCopy();

        @Override
        BijectiveMap.Immutable<L, R> with(L left, R right) throws DuplicateValueException;

        @Override
        default BijectiveMap.Immutable<L, R> immutableCopy() {
            return this;
        }

    }

    class DuplicateValueException extends IllegalArgumentException {

        private static final long serialVersionUID = 1L;

    }

}
