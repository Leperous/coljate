package net.ollie.coljate.map;

import java.util.Iterator;

import net.ollie.coljate.Collection;
import net.ollie.coljate.ImmutableCollection;
import net.ollie.coljate.MutableCollection;
import net.ollie.coljate.utils.Iterators;

/**
 *
 * @author Ollie
 */
public class MapValues<V> implements Collection<V> {

    private final Map<?, V> map;

    protected MapValues(final Map<?, V> map) {
        this.map = map;
    }

    @Override
    public Iterator<V> iterator() {
        return Iterators.transform(map.iterator(), KeyValue::value);
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public Collection<V> tail() {
        return map.tail().values();
    }

    @Override
    public MutableCollection<V> mutableCopy() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public ImmutableCollection<V> immutableCopy() {
        throw new UnsupportedOperationException(); //TODO
    }

}
