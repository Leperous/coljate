package net.ollie.coljate.maps;

import java.util.Iterator;

import net.ollie.coljate.utils.numeric.PositiveInteger;

/**
 * Removes a random entry if the map is too large.
 *
 * @author Ollie
 */
public class SizeLimitedCache<K, V> extends DelegatedCache<K, V> {

    /**
     *
     * @param <K>
     * @param <V>
     * @param delegate
     * @param maxSize
     * @return
     * @throws IllegalArgumentException if given zero size
     */
    public static <K, V> SizeLimitedCache<K, V> create(final Cache<K, V> delegate, final PositiveInteger maxSize) {
        return new SizeLimitedCache<>(delegate, maxSize);
    }

    private final PositiveInteger maxSize;

    protected SizeLimitedCache(final Cache<K, V> delegate, final PositiveInteger maxSize) {
        super(delegate);
        this.maxSize = maxSize;
    }

    @Override
    protected void onWrite(final K key, final V value) {
        switch (maxSize.compareTo(this.count())) {
            case BEFORE:
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
    public String toString() {
        return super.toString() + " max size=" + maxSize;
    }

}
