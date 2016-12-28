package net.coljate.graph.impl;

import java.util.Spliterator;
import java.util.function.Predicate;

import net.coljate.collection.Empty;
import net.coljate.graph.AbstractGraph;
import net.coljate.graph.ImmutableGraph;
import net.coljate.graph.Relationship;

/**
 * An immutable graph with no vertices or relationships.
 *
 * @author ollie
 */
public class EmptyGraph<V, E>
        extends AbstractGraph<V, E>
        implements ImmutableGraph<V, E>, Empty<Relationship<V, E>> {

    private static final long serialVersionUID = 1L;

    private static final EmptyGraph INSTANCE = new EmptyGraph();

    @SuppressWarnings("unchecked")
    public static <V, E> EmptyGraph<V, E> getInstance() {
        return INSTANCE;
    }

    @Override
    @Deprecated
    public EmptyGraph<V, E> filter(final Predicate<? super Relationship<V, E>> predicate) {
        return this;
    }

    @Override
    public Spliterator<Relationship<V, E>> spliterator() {
        return Empty.super.spliterator();
    }

    @Override
    public EmptyGraph<V, E> immutableCopy() {
        return this;
    }

}
