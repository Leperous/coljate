package net.coljate.feature;

/**
 * Has a specific order.
 *
 * @author ollie
 */
public interface Ordered<T> {

    /**
     *
     * @return the first element in this ordered collection, or {@code null} if empty.
     */
    @Complexity(Complexity.Order.CONSTANT)
    T first();

}
