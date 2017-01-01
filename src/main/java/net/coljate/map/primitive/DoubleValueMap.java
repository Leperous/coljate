package net.coljate.map.primitive;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.DoubleSupplier;

import net.coljate.map.Map;

/**
 *
 * @author Ollie
 */
public interface DoubleValueMap<K> extends Map<K, Double> {

    double getDouble(K key, DoubleSupplier ifNull);

    OptionalDouble maybeGetDouble(K key);

    @Override
    @Deprecated
    default Optional<Double> maybeGet(final K key) {
        final OptionalDouble optional = this.maybeGetDouble(key);
        return optional.isPresent()
                ? Optional.of(optional.getAsDouble())
                : Optional.empty();
    }

}
