package net.ollie.coljate.maps;

import java.util.Iterator;

import static net.ollie.coljate.utils.Conditions.checkIsFalse;
import net.ollie.coljate.utils.numeric.NonNegativeInteger;

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
    public static <K, V> SizeLimitedCache<K, V> create(final Cache<K, V> delegate, final NonNegativeInteger maxSize) {
        return new SizeLimitedCache<>(delegate, maxSize);
    }

    private final NonNegativeInteger maxSize;

    protected SizeLimitedCache(final Cache<K, V> delegate, final NonNegativeInteger maxSize) {
        super(delegate);
        this.maxSize = checkIsFalse(maxSize, NonNegativeInteger::isZero, "Passed zero size!");
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
