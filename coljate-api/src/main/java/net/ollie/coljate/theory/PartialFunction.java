package net.ollie.coljate.theory;

import java.util.Optional;
import java.util.function.Function;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 * @see <a href="https://en.wikipedia.org/wiki/Partial_function">Partial function</a>
 */
public interface PartialFunction<F, T> {

    boolean inDomain(@Nonnull F input);

    @CheckForNull
    T apply(F input);

    @Nonnull
    default Function<F, Optional<T>> total() {
        return element -> this.inDomain(element)
                ? Optional.ofNullable(this.apply(element))
                : Optional.empty();
    }

}
