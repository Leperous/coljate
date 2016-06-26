package net.ollie.coljate.theory;

/**
 *
 * @author Ollie
 */
public interface Invertible<F, T> {

    Invertible<T, F> inverse();

}
