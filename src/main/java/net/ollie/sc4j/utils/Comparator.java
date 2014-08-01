package net.ollie.sc4j.utils;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface Comparator<T>
        extends java.util.Comparator<T> {

    @Override
    default int compare(final T o1, final T o2) {
        return this.comparison(o1, o2).value();
    }

    @Nonnull
    Comparison comparison(T t1, T t2);

    public enum Comparison {

        BEFORE(-1),
        SAME(0),
        AFTER(1);

        private final int value;

        Comparison(final int comp) {
            this.value = comp;
        }

        public int value() {
            return value;
        }

        public static Comparison from(final int comparison) {
            return comparison == 0
                    ? SAME
                    : (comparison > 0 ? AFTER : BEFORE);
        }

    }

}
