package net.coljate.graph.impl;

import java.util.Spliterator;
import java.util.function.Predicate;

import net.coljate.collection.Empty;
import net.coljate.graph.AbstractGraph;
import net.coljate.graph.ImmutableGraph;
import net.coljate.graph.MutableGraph;
import net.coljate.graph.Relationship;

/**
 *
 * @author ollie
 */
public class EmptyGraph<V, E>
        extends AbstractGraph<V, E>
        implements ImmutableGraph<V, E>, Empty<Relationship<V, E>> {

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
    public MutableGraph<V, E> mutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public EmptyGraph<V, E> immutableCopy() {
        return this;
    }

}
