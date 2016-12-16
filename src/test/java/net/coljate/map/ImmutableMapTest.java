package net.coljate.map;

import net.coljate.collection.ImmutableCollectionTest;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Ollie
 */
public interface ImmutableMapTest<K, V> extends MapTest<K, V>, ImmutableCollectionTest<Entry<K, V>> {

    @Override
    ImmutableMap<K, V> create(java.util.List<Entry<K, V>> elements);

    @Override
    default ImmutableMap<K, V> create() {
        return this.create(java.util.Collections.emptyList());
    }

    @Override
    default ImmutableMap<K, V> create(final Entry<K, V> entry) {
        return this.create(singletonList(entry));
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
