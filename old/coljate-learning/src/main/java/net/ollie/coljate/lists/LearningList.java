package net.ollie.coljate.lists;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Predicate;

/**
 *
 * @author Ollie
 */
public class LearningList<V> implements List.Mutable<V> {

    public static <V> List.Mutable<V> create(final CallSite callSite) {
        return new LearningList<>(callSite);
    }

    @FunctionalInterface
    public interface CallSite {

        void doNothing();

        default <V> List.Mutable<V> arrayList() {
            return MutableWrappedArray.create();
        }

        default <V> List.Mutable<V> linkedList() {
            return MutableWrappedLinkedList.create();
        }

    }

    private final CallSite callSite;

    protected LearningList(final CallSite callSite) {
        System.out.println("CREATED USING [" + callSite + "]!");
        this.callSite = callSite;
    }

    @Override
    public void prefix(final V value) {
        throw new UnsupportedOperationException("prefix not supported yet!");
    }

    @Override
    public void prefixAll(Iterable<? extends V> values) {
        throw new UnsupportedOperationException("prefixAll not supported yet!");
    }

    @Override
    public void suffix(V value) {
        throw new UnsupportedOperationException("suffix not supported yet!");
    }

    @Override
    public List<V> tail() {
        throw new UnsupportedOperationException("tail not supported yet!");
    }

    @Override
    public Stream<V, ? extends List<V>> stream() {
        throw new UnsupportedOperationException("stream not supported yet!");
    }

    @Override
    public V last() {
        throw new UnsupportedOperationException("last not supported yet!");
    }

    @Override
    public V head() {
        throw new UnsupportedOperationException("head not supported yet!");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("isEmpty not supported yet!");
    }

    @Override
    public boolean contains(Object object) {
        throw new UnsupportedOperationException("contains not supported yet!");
    }

    @Override
    public V first() {
        throw new UnsupportedOperationException("first not supported yet!");
    }

    @Override
    public Iterator<V> iterator() {
        throw new UnsupportedOperationException("iterator not supported yet!");
    }

    @Override
    public Optional<V> findAny(Predicate<? super V> predicate) {
        throw new UnsupportedOperationException("findAny not supported yet!");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("clear not supported yet!");
    }

    @Override
    public List.Mutable<V> reverseCopy() {
        throw new UnsupportedOperationException("reverseCopy not supported yet!");
    }

    @Override
    public List.Mutable<V> mutableCopy() {
        throw new UnsupportedOperationException("mutableCopy not supported yet!");
    }

    @Override
    public List.Immutable<V> immutableCopy() {
        throw new UnsupportedOperationException("immutableCopy not supported yet!");
    }

}
