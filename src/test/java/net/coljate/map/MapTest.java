package net.coljate.map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

import net.coljate.collection.CollectionTest;

/**
 *
 * @author Ollie
 */
public interface MapTest<K, V> extends CollectionTest<Entry<K, V>> {

    @Override
    Map<K, V> create(java.util.List<Entry<K, V>> entries);

    @Override
    default Map<K, V> create(final Entry<K, V> entry) {
        return this.create(java.util.Arrays.asList(entry));
    }

    @Override
    default Map<K, V> create() {
        return this.create(java.util.Collections.emptyList());
    }

    @Test
    default void testEntry_Singleton() {
        final Entry<K, V> entry = this.createObject();
        final Map<K, V> map = this.create(entry);
        final Entry<K, V> got = map.entry(entry.key());
        assertThat(got.key(), is(entry.key()));
        assertThat(got.value(), is(entry.value()));
        assertThat(got, is(entry));
    }

}
