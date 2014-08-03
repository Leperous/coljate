package net.ollie.sc4j.utils.numeric;

/**
 *
 * @author Ollie
 */
public class NonNegativeException extends IndexOutOfBoundsException {

    private static final long serialVersionUID = 1L;

    public NonNegativeException(final Number value) {
        super("Value is negative: " + value);
    }

}
