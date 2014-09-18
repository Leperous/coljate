package net.ollie.coljate.maps;

import java.util.Iterator;

import net.ollie.coljate.utils.numeric.NonNegativeInteger;

/**
 * Removes a random entry if the map is too large.
 *
 * @author Ollie
 */
public class SizeLimitedCache<K, V> extends DelegatedCache<K, V> {

    public static <K, V> SizeLimitedCache<K, V> create(final Cache<K, V> delegate, final NonNegativeInteger maxSize) {
        return new SizeLimitedCache<>(delegate, maxSize);
    }

    private final NonNegativeInteger maxSize;

    protected SizeLimitedCache(final Cache<K, V> delegate, final NonNegativeInteger maxSize) {
        super(delegate);
        this.maxSize = maxSize;
    }

    @Override
    protected void onWrite(final K key, final V value) {
        switch (maxSize.compareTo(this.count())) {
            case AFTER:
                final Iterator<K> iterator = this.keys().iterator();
                while (iterator.hasNext()) {
                    if (!key.equals(iterator.next())) {
                        iterator.remove();
                        break;
                    }
                }
                break;
        }
    }

    @Override
    protected void onRead(final K key) {
    }

    @Override
    protected void onRemove(final Object key) {
    }

}
