package net.coljate.map;

import net.coljate.set.MutableSetTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Ollie
 */
public interface MutableMapTest<K, V>
        extends MapTest<K, V>, MutableSetTest<Entry<K, V>> {

    @Override
    MutableMap<K, V> create(java.util.List<Entry<K, V>> entries);

    @Override
    default MutableMap<K, V> create(final Entry<K, V> entry) {
        return this.create(singletonList(entry));
    }

    @Override
    default MutableMap<K, V> create() {
        return this.create(emptyList());
    }

    @Test
    default void testAddEntry_Empty() {
        final MutableMap<K, V> map = this.create();
        final Entry<K, V> entry = this.createObject();
        assertTrue(map.add(entry));
        assertFalse(map.add(entry));
    }

    @Test
    default void testAddKeyValue_Empty() {
        final MutableMap<K, V> map = this.create();
        final Entry<K, V> entry = this.createObject();
        assertTrue(map.add(entry.key(), entry.value()));
        assertFalse(map.add(entry.key(), entry.value()));
    }

    @Test
    default void testRemoveEntry_Singleton() {
        final Entry<K, V> entry = this.createObject();
        final MutableMap<K, V> map = this.create(entry);
        assertTrue(map.remove(entry));
        assertFalse(map.remove(this.createObject()));
    }

    @Test
    default void testEvictKey_Singleton() {
        final Entry<K, V> entry = this.createObject();
        final MutableMap<K, V> map = this.create(entry);
        assertThat(map.get(entry.key()), is(entry.value()));
        final V removedValue = map.evict(entry.key());
        assertThat("Remove should return " + entry.value(), removedValue, is(entry.value()));
    }

}
