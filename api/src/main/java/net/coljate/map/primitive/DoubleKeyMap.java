package net.coljate.map.primitive;

import javax.annotation.CheckForNull;

import net.coljate.map.Entry;
import net.coljate.map.Map;
import net.coljate.set.primitive.DoubleSet;

/**
 *
 * @author Ollie
 */
public interface DoubleKeyMap<V> extends Map<Double, V> {

    @CheckForNull
    DoubleKeyEntry<V> getEntry(double key);

    @Override
    @Deprecated
    default DoubleKeyEntry<V> getEntry(final Object key) {
        return key instanceof Double
                ? this.getEntry((double) key)
                : null;
    }

    @Override
    DoubleSet keys();

    interface DoubleKeyEntry<V> extends Entry<Double, V> {

        double doubleKey();

        @Override
        @Deprecated
        default Double key() {
            return this.doubleKey();
        }

    }

}
