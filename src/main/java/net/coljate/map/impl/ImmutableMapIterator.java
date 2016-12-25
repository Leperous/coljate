package net.coljate.map.impl;

import java.util.function.Function;

import net.coljate.collection.Collection;
import net.coljate.map.Entry;
import net.coljate.map.ImmutableEntry;
import net.coljate.util.iterator.UnmodifiableIterator;

/**
 *
 * @author ollie
 */
public class ImmutableMapIterator<K, V, E extends ImmutableEntry<K, V>>
        extends MapIterator<K, V, E>
        implements UnmodifiableIterator<Entry<K, V>> {

    public ImmutableMapIterator(final Collection<K> keys, final Function<? super K, ? extends E> getEntry) {
        super(keys, getEntry);
    }

}
