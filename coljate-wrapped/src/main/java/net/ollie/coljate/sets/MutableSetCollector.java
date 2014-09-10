package net.ollie.coljate.sets;

import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import net.ollie.coljate.Set;

/**
 *
 * @author Ollie
 */
public class MutableSetCollector<V, S extends Set.Mutable<V>>
        implements Collector<V, S, S> {

    public static <V, S extends Set.Mutable<V>> Collector<V, ?, S> create(final Supplier<S> supplier) {
        return new MutableSetCollector<>(supplier);
    }

    private final Supplier<S> supplier;

    protected MutableSetCollector(final Supplier<S> supplier) {
        this.supplier = supplier;
    }

    @Override
    public Supplier<S> supplier() {
        return supplier;
    }

    @Override
    public BiConsumer<S, V> accumulator() {
        return (set, value) -> set.add(value);
    }

    @Override
    public BinaryOperator<S> combiner() {
        return (left, right) -> {
            left.addAll(right);
            return left;
        };
    }

    @Override
    public Function<S, S> finisher() {
        return Function.identity();
    }

    @Override
    public java.util.Set<Characteristics> characteristics() {
        return java.util.Collections.singleton(Characteristics.UNORDERED);
    }

}
