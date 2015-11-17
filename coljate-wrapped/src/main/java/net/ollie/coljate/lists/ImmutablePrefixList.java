package net.ollie.coljate.lists;

import java.util.stream.Stream;

import net.ollie.coljate.ImmutableCollection;
import net.ollie.coljate.UnmodifiableIterator;

/**
 *
 * @author Ollie
 */
public class ImmutablePrefixList<T>
        extends AbstractList<T>
        implements ImmutableList<T> {

    public static <T> ImmutableList<T> of(final T element, final ImmutableList<T> right) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public ImmutableList<T> prefix(T element) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public ImmutableList<T> suffix(T element) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public ImmutableList<T> tail() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public T get(int index) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public ImmutableList<T> immutableCopy() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public MutableList<T> mutableCopy() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public int count() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public Stream<T> serialStream() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public Stream<T> parallelStream() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public boolean contains(Object object) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public boolean inDomain(Integer input) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public T apply(Integer input) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public ImmutableCollection<T> with(T element) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public UnmodifiableIterator<T> iterator() {
        throw new UnsupportedOperationException(); //TODO
    }

}
