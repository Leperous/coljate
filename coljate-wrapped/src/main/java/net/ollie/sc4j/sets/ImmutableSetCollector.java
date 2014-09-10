package net.ollie.sc4j.sets;

import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import net.ollie.sc4j.Set;

/**
 *
 * @author Ollie
 */
public class ImmutableSetCollector<V, S extends Set.Immutable<V>>
        implements Collector<V, Set.Mutable<V>, S> {

    private final Supplier<Set.Mutable<V>> supplier;
    private final Function<Set.Mutable<V>, S> finisher;

    protected ImmutableSetCollector(final Supplier<Set.Mutable<V>> supplier, final Function<Set.Mutable<V>, S> finisher) {
        this.supplier = supplier;
        this.finisher = finisher;
    }

    @Override
    public Supplier<Set.Mutable<V>> supplier() {
        return supplier;
    }

    @Override
    public BiConsumer<Set.Mutable<V>, V> accumulator() {
        return (set, element) -> set.add(element);
    }

    @Override
    public BinaryOperator<Set.Mutable<V>> combiner() {
        return (left, right) -> {
            left.addAll(right);
            return left;
        };
    }

    @Override
    public Function<Set.Mutable<V>, S> finisher() {
        return finisher;
    }

    @Override
    public java.util.Set<Characteristics> characteristics() {
        return java.util.Collections.singleton(Characteristics.UNORDERED);
    }

}
