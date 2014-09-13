package net.ollie.coljate.sets;

import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;

import net.ollie.coljate.Set;

/**
 *
 * @author Ollie
 */
public abstract class AbstractSetCollector<V, I extends Set<V>> implements Collector<V, Set.Mutable<V>, I> {

    @Override
    public BiConsumer<Set.Mutable<V>, V> accumulator() {
        return Set.Mutable::add;
    }

    @Override
    public BinaryOperator<Set.Mutable<V>> combiner() {
        return (left, right) -> {
            left.addAll(right);
            return left;
        };
    }

    @Override
    public java.util.Set<Characteristics> characteristics() {
        return java.util.Collections.singleton(Characteristics.UNORDERED);
    }

}
