package net.ollie.coljate.maps;

import net.ollie.coljate.AbstractImmutableMapTest;
import net.ollie.coljate.Map;

import com.google.common.collect.ImmutableMap;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"rawtypes", "unchecked", "null"})
public class ImmutableGuavaMapTest extends AbstractImmutableMapTest<ImmutableGuavaMap<Object, Object>> {

    @Override
    protected ImmutableGuavaMap<Object, Object> create(final Iterable<Map.Entry<Object, Object>> entries) {
        final ImmutableMap.Builder<Object, Object> builder = ImmutableMap.builder();
        for(final Map.Entry<Object, Object> entry : entries) {
            builder.put(entry.key(), entry.value());
        }
        return ImmutableGuavaMap.build(builder);
    }

}
