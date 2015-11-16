package net.ollie.coljate.sets;

import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;


/**
 *
 * @author Ollie
 */
public abstract class AbstractSetCollector<V, S extends Set.Mutable<V>, I extends Set<V>> implements Collector<V, S, I> {

    @Override
    public BiConsumer<S, V> accumulator() {
        return Set.Mutable::add;
    }

    @Override
    public BinaryOperator<S> combiner() {
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
