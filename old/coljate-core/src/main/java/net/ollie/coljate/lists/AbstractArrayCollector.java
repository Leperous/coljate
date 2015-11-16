package net.ollie.coljate.lists;

import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;


/**
 *
 * @author Ollie
 */
public abstract class AbstractArrayCollector<V, I extends Array.Immutable<V>> implements Collector<V, Array.Mutable<V>, I> {

    @Override
    public BiConsumer<Array.Mutable<V>, V> accumulator() {
        return Array.Mutable::suffix;
    }

    @Override
    public BinaryOperator<Array.Mutable<V>> combiner() {
        return (left, right) -> {
            left.suffixAll(right);
            return left;
        };
    }

    @Override
    public java.util.Set<Characteristics> characteristics() {
        return java.util.Collections.emptySet();
    }

}
