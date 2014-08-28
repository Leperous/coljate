package net.ollie.sc4j.imposed;

import net.ollie.sc4j.access.Keyed;

/**
 *
 * @author Ollie
 */
public interface Mapping<F, T> extends Keyed.Single<F, T> {

    /**
     * All values are mapped-to by at least one key.
     *
     * @param <F>
     * @param <T>
     * @see <a href="http://en.wikipedia.org/wiki/Surjective_function">Surjection</a>
     */
    interface Surjective<F, T> extends Mapping<F, T> {
    }

    /**
     * All values are mapped-to by at most one key.
     *
     * @param <F>
     * @param <T>
     * @see <a href="http://en.wikipedia.org/wiki/Injective_function">Injection</a>
     */
    interface Injective<F, T> extends Mapping<F, T> {
    }

    /**
     * All values are mapped-to by exactly one key.
     *
     * @param <F>
     * @param <T>
     * @see <a href="http://en.wikipedia.org/wiki/Bijection">Bijection</a>
     */
    interface Bijective<F, T> extends Surjective<F, T>, Injective<F, T> {

        Bijective<T, F> inverse();

    }

}
