package net.ollie.coljate.map.mixin;

import net.ollie.coljate.map.ImmutableMap;
import net.ollie.coljate.map.ImmutableWrappedHashMap;
import net.ollie.coljate.map.Map;
import net.ollie.coljate.map.MutableMap;
import net.ollie.coljate.map.MutableWrappedHashMap;

/**
 *
 * @author Ollie
 */
public interface CopiedToHashMap<K, V> extends Map<K, V> {

    @Override
    default MutableMap<K, V> mutableCopy() {
        return MutableWrappedHashMap.copyOf(this);
    }

    @Override
    default ImmutableMap<K, V> immutableCopy() {
        return ImmutableWrappedHashMap.copyOf(this);
    }

}
