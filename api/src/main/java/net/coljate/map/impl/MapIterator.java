package net.coljate.map.impl;

import java.util.Iterator;
import java.util.function.Function;

import net.coljate.collection.Collection;
import net.coljate.map.Entry;
import net.coljate.util.iterator.CovariantIterator;

/**
 *
 * @author ollie
 */
public class MapIterator<K, V, E extends Entry<K, V>> implements CovariantIterator<Entry<K, V>, E> {

    private final Function<? super K, ? extends E> getEntry;
    private final Iterator<K> keys;

    public MapIterator(final Collection<K> keys, final Function<? super K, ? extends E> getEntry) {
        this.getEntry = getEntry;
        this.keys = keys.iterator();
    }

    @Override
    public boolean hasNext() {
        return keys.hasNext();
    }

    @Override
    public E next() {
        return getEntry.apply(keys.next());
    }

}
