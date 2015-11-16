package net.ollie.coljate.maps;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import com.google.common.collect.ImmutableMap;

/**
 *
 * @author Ollie
 */
public class ImmutableMapCollector<K, V>
        implements Collector<java.util.Map.Entry<K, V>, ImmutableMap.Builder<K, V>, ImmutableMap<K, V>> {

    @SuppressWarnings("rawtypes")
    private static final ImmutableMapCollector INSTANCE = new ImmutableMapCollector();

    @SuppressWarnings("unchecked")
    public static <K, V> Collector<java.util.Map.Entry<K, V>, ?, ImmutableMap<K, V>> collector() {
        return INSTANCE;
    }

    protected ImmutableMapCollector() {
    }

    @Override
    public Supplier<ImmutableMap.Builder<K, V>> supplier() {
        return ImmutableMap::builder;
    }

    @Override
    public BiConsumer<ImmutableMap.Builder<K, V>, Map.Entry<K, V>> accumulator() {
        return ImmutableMap.Builder::put;
    }

    @Override
    public BinaryOperator<ImmutableMap.Builder<K, V>> combiner() {
        return (left, right) -> {
            left.putAll(right.build());
            return left;
        };
    }

    @Override
    public Function<ImmutableMap.Builder<K, V>, ImmutableMap<K, V>> finisher() {
        return ImmutableMap.Builder::build;
    }

    @Override
    public java.util.Set<Characteristics> characteristics() {
        return java.util.Collections.singleton(Characteristics.UNORDERED);
    }

}
