package net.ollie.coljate.maps;

import net.ollie.coljate.AbstractMutableMapTest;
import net.ollie.coljate.Map;

/**
 *
 * @author Ollie
 */
public class MutableWrappedHashMapTest
        extends AbstractMutableMapTest<Map.Mutable<Object, Object>> {

    @Override
    protected Map.Mutable<Object, Object> create(final Iterable<Map.Entry<Object, Object>> entries) {
        final Map.Mutable<Object, Object> map = MutableWrappedHashMap.create();
        for (final Map.Entry<Object, Object> entry : entries) {
            map.put(entry.key(), entry.value());
        }
        return map;
    }

}
