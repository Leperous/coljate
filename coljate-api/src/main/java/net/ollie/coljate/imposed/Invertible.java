package net.ollie.coljate.imposed;

import net.ollie.coljate.maps.Map;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface Invertible<K, V> {

    @Nonnull
    Invertible<V, K> inverseCopy();

    /**
     * All values {@code V} are mapped-to by at least one key {@code K}. For example, a {@link Map}.
     *
     * @param <K>
     * @param <V>
     * @see <a href="http://en.wikipedia.org/wiki/Surjective_function">Surjective</a>
     */
    interface Surjective<K, V> extends Invertible<K, V> {
    }

    /**
     * All values {@code V} are mapped-to by at most one key {@code K}.
     *
     * @param <K>
     * @param <V>
     * @see <a href="http://en.wikipedia.org/wiki/Injective_function">Injective</a>
     */
    interface Injective<K, V> extends Invertible<K, V> {
    }

    /**
     * All values {@code V} are mapped-to by exactly one key {@code}, and vice-verse; that is, this is both surjective
     * and injective.
     *
     * @param <K>
     * @param <V>
     * @see <a href="http://en.wikipedia.org/wiki/Bijection">Bijective</a>
     */
    interface Bijective<K, V> extends Surjective<K, V>, Injective<K, V> {

        @Override
        Bijective<V, K> inverseCopy();

        @CheckForNull
        K invert(V right);

    }

}
