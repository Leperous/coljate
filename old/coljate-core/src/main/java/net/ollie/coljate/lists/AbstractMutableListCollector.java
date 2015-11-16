package net.ollie.coljate.lists;

import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;

/**
 *
 * @author Ollie
 */
public abstract class AbstractMutableListCollector<V, L extends List.Mutable<V>>
        implements Collector<V, L, L> {

    @Override
    public BiConsumer<L, V> accumulator() {
        return List.Mutable::suffix;
    }

    @Override
    public BinaryOperator<L> combiner() {
        return (left, right) -> {
            left.suffixAll(right);
            return left;
        };
    }

    @Override
    public Function<L, L> finisher() {
        return Function.identity();
    }

    @Override
    public java.util.Set<Characteristics> characteristics() {
        return java.util.Collections.emptySet();
    }

}
