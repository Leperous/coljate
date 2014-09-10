package net.ollie.sc4j.imposed;

import net.ollie.sc4j.access.Keyed;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface Mapping<F, T> extends Keyed.Single<F, T> {

    /**
     * All values are mapped-to by at least one key. For example, a {@link Map}.
     *
     * @param <F>
     * @param <T>
     * @see <a href="http://en.wikipedia.org/wiki/Surjective_function">Surjective</a>
     */
    interface Surjective<F, T> extends Mapping<F, T> {
    }

    /**
     * All values are mapped-to by at most one key.
     *
     * @param <F>
     * @param <T>
     * @see <a href="http://en.wikipedia.org/wiki/Injective_function">Injective</a>
     */
    interface Injective<F, T> extends Mapping<F, T> {
    }

    /**
     * All values are mapped-to by exactly one key, that is they are both surjective and injective.
     *
     * @param <F>
     * @param <T>
     * @see <a href="http://en.wikipedia.org/wiki/Bijection">Bijective</a>
     */
    interface Bijective<F, T> extends Surjective<F, T>, Injective<F, T> {

        @Nonnull
        Bijective<T, F> inverse();

        @CheckForNull
        F invert(T right);

    }

}
