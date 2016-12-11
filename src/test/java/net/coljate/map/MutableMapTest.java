package net.coljate.map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import net.coljate.collection.MutableCollectionTest;

/**
 *
 * @author Ollie
 */
public interface MutableMapTest<K, V> extends MapTest<K, V>, MutableCollectionTest<Entry<K, V>> {

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
    default void testRemove_Singleton() {
        final Entry<K, V> entry = this.createObject();
        final MutableMap<K, V> map = this.create(entry);
        assertTrue(map.remove(entry));
        assertFalse(map.remove(this.createObject()));
    }

    @Test
    default void testRemoveKey_Singleton() {
        final Entry<K, V> entry = this.createObject();
        final MutableMap<K, V> map = this.create(entry);
        assertThat(map.get(entry.key()), is(entry.value()));
        final V removedValue = map.removeValue(entry.key());
        assertThat("Remove should return " + entry.value(), removedValue, is(entry.value()));
    }

}
