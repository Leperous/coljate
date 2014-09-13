package net.ollie.coljate;

import net.ollie.coljate.imposed.Mapping.Bijective;

/**
 *
 * @author Ollie
 */
public interface BiMap<L, R>
        extends Map<L, R>, Bijective<L, R> {

    @Override
    Set<R> values();

    @Override
    BiMap<R, L> inverse();

    @Override
    default boolean contains(final Object object) {
        return this.keys().contains(object) || this.values().contains(object);
    }

    @Override
    BiMap.Mutable<L, R> mutableCopy();

    @Override
    BiMap.Immutable<L, R> immutableCopy();

    /**
     * A mutable bi-map.
     *
     * @param <L>
     * @param <R>
     */
    interface Mutable<L, R>
            extends BiMap<L, R>, Map.Mutable<L, R> {

        @Override
        View<R, L> inverse();

        @Override
        R put(L left, R right) throws DuplicateValueException;

        interface View<L, R>
                extends BiMap.Mutable<L, R> {

        }

    }

    interface Immutable<L, R>
            extends BiMap<L, R>, Map.Immutable<L, R> {

        @Override
        Set.Immutable<R> values();

        @Override
        BiMap.Immutable<R, L> inverse();

        @Override
        BiMap.Immutable<L, R> with(L left, R right) throws DuplicateValueException;

        @Override
        default BiMap.Immutable<L, R> immutableCopy() {
            return this;
        }

    }

    class DuplicateValueException extends IllegalArgumentException {

        private static final long serialVersionUID = 1L;

    }

}
