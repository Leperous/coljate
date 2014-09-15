package net.ollie.coljate.sets;

import net.ollie.coljate.imposed.Finite.Universe;
import net.ollie.coljate.sets.EnumSet.EnumUniverse;
import net.ollie.coljate.utils.numeric.NonNegativeInteger;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface EnumSet<E extends Enum<E>> extends FiniteSet<E, EnumUniverse<E>> {

    @Nonnull
    Class<E> elementType();

    @Override
    EnumSet<E> complement();

    @Override
    EnumSet<E> intersection(FiniteSet<E, EnumUniverse<E>> set);

    @Override
    EnumSet<E> union(FiniteSet<E, EnumUniverse<E>> set);

    @Override
    default EnumUniverse<E> universe() {
        return new EnumUniverse<>(this.elementType());
    }

    /**
     *
     * @param <E>
     */
    public static final class EnumUniverse<E extends Enum<E>> implements Universe<E> {

        private final Class<E> clazz;
        private final NonNegativeInteger cardinality;

        public EnumUniverse(final Class<E> clazz) {
            this.clazz = clazz;
            this.cardinality = NonNegativeInteger.of(java.util.EnumSet.allOf(clazz).size());
        }

        @Override
        public NonNegativeInteger cardinality() {
            return cardinality;
        }

        @Override
        public String toString() {
            return "{" + clazz + "}";
        }

    }

}
