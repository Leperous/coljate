package net.ollie.coljate.utils;

import java.util.function.Supplier;

/**
 *
 * @author Ollie
 */
public interface Lazy<T> extends Supplier<T> {

    static <T> Lazy<T> nonNull(final Supplier<T> supplier) {
        return new Lazy<T>() {

            private transient T got;

            @Override
            public T get() {
                return got == null ? got = supplier.get() : got;
            }

        };
    }

}
