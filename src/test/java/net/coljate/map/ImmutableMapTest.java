package net.coljate.map;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import net.coljate.collection.ImmutableCollectionTest;

/**
 *
 * @author Ollie
 */
public interface ImmutableMapTest<K, V> extends MapTest<K, V>, ImmutableCollectionTest<Entry<K, V>> {

    @Override
    ImmutableMap<K, V> create(java.util.List<Entry<K, V>> elements);

    default ImmutableMap<K, V> create() {
        return this.create(java.util.Collections.emptyList());
    }

    @Test
    default void testWith_Empty() {
        final ImmutableMap<K, V> empty = this.create();
        final Entry<K, V> entry = this.createObject();
        final ImmutableMap<K, V> singleton = empty.with(entry);
        assertThat(singleton, not(empty));
        assertFalse(empty.containsEntry(entry));
        assertTrue(singleton.containsEntry(entry));
    }

}
