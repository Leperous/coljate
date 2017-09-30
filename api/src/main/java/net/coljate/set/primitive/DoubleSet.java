package net.coljate.set.primitive;

import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;

import net.coljate.collection.primitive.DoubleCollection;
import net.coljate.set.Set;

/**
 *
 * @author Ollie
 */
public interface DoubleSet extends Set<Double>, DoubleCollection {

    @Override
    MutableDoubleSet mutableCopy();

    @Override
    ImmutableDoubleSet immutableCopy();

    @Override
    @Deprecated
    default Double reduce(BinaryOperator<Double> operator) {
        return DoubleCollection.super.reduce(operator);
    }

    default double reduce(final DoubleBinaryOperator operator) {
        return this.reduce(operator, 0d);
    }

    default double reduce(final DoubleBinaryOperator operator, final double initialValue) {
        final DoubleIterator iterator = this.iterator();
        double current = initialValue;
        while (iterator.hasNext()) {
            current = operator.applyAsDouble(current, iterator.nextDouble());
        }
        return current;
    }

    default double sum() {
        return this.reduce((final double d1, final double d2) -> d1 + d2, 0d);
    }

    default double product() {
        return this.reduce((final double d1, final double d2) -> d1 + d2, 1d);
    }

}
