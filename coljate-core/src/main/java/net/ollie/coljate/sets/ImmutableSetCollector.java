package net.ollie.coljate.sets;

import java.util.function.Function;
import java.util.function.Supplier;


/**
 *
 * @author Ollie
 */
public class ImmutableSetCollector<V, S extends Set.Immutable<V>>
        extends AbstractSetCollector<V, Set.Mutable<V>, S> {

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
    public Function<Set.Mutable<V>, S> finisher() {
        return finisher;
    }

    @Override
    public java.util.Set<Characteristics> characteristics() {
        return java.util.Collections.singleton(Characteristics.UNORDERED);
    }

}
