package net.ollie.coljate.lists;

import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import net.ollie.coljate.List;

/**
 *
 * @author Ollie
 */
public class ImmutableListCollector<V>
        implements Collector<V, List.Mutable<V>, List.Immutable<V>> {

    private final Supplier<List.Mutable<V>> supplier;

    public ImmutableListCollector(final Supplier<List.Mutable<V>> supplier) {
        this.supplier = supplier;
    }

    @Override
    public Supplier<List.Mutable<V>> supplier() {
        return supplier;
    }

    @Override
    public BiConsumer<List.Mutable<V>, V> accumulator() {
        return (list, value) -> list.suffix(value);
    }

    @Override
    public BinaryOperator<List.Mutable<V>> combiner() {
        return (left, right) -> {
            left.suffixAll(right);
            return left;
        };
    }

    @Override
    public Function<List.Mutable<V>, List.Immutable<V>> finisher() {
        return List.Mutable::immutableCopy;
    }

    @Override
    public java.util.Set<Characteristics> characteristics() {
        return java.util.Collections.emptySet();
    }

}
