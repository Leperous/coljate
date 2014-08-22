package net.ollie.sc4j.imposed;

import java.util.function.Function;

import net.ollie.sc4j.Collection;
import net.ollie.sc4j.access.Keyed;
import net.ollie.sc4j.imposed.Distinctness.Unique;

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

        default <R> Surjective<F, R> compose(final Surjective<? extends T, R> that) {
            return new Composition<>(this, that);
        }

        class Composition<F, T, R> implements Surjective<F, R> {

            private final Surjective<F, T> left;
            private final Surjective<? extends T, R> right;

            Composition(final Surjective<F, T> left, final Surjective<? extends T, R> right) {
                this.left = left;
                this.right = right;
            }

            @Override
            public R get(final Object key) {
                final T got = left.get(key);
                return got == null ? null : right.get(got);
            }

            @Override
            public Unique<F> keys() {
                return left.keys();
            }

            @Override
            public <K2> Keyed.Single<K2, R> mapKeys(final Function<? super F, ? extends K2> function) {
                final Keyed.Single<K2, T> keyed = left.mapKeys(function);
                return keyed instanceof Surjective
                        ? ((Surjective<K2, T>) keyed).compose(right)
                        : null;
            }

            @Override
            public Collection<R> values() {
                return right.values();
            }

            @Override
            public <V2> Keyed<V2> mapValues(final Function<? super R, ? extends V2> function) {
                throw new UnsupportedOperationException("mapValues not supported yet!");
            }

        }

    }

    /**
     * All values are mapped-to by at most one key.
     *
     * @param <F>
     * @param <T>
     * @see <a href="http://en.wikipedia.org/wiki/Injective_function">Injection</a>
     */
    interface Injective<F, T> extends Mapping<F, T> {

        <R> Injective<F, R> compose(Injective<? extends T, ? extends R> that);

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
