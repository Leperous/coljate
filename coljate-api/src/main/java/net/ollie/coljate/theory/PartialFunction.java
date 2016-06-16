package net.ollie.coljate.theory;

import java.util.Optional;
import java.util.function.Function;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Ollie
 * @see <a href="https://en.wikipedia.org/wiki/Partial_function">Partial
 * function</a>
 */
public interface PartialFunction<@Nullable F, @Nullable T> {

    boolean inDomain(F input);

    T apply(F input);

    @NonNull
    default Function<F, Optional<T>> toTotalFunction() {
        return element -> this.inDomain(element)
                ? Optional.ofNullable(this.apply(element))
                : Optional.empty();
    }

}
