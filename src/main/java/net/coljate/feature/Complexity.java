package net.coljate.feature;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Ollie
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Complexity {

    /**
     * @return average or expected complexity.
     */
    Order value() default Order.UNKNOWN;

    Order bestCase() default Order.UNKNOWN;

    Order worstCase() default Order.UNKNOWN;

    /**
     * @return true if the complexity is determined from the method call(s) made, which should be between the worst-best
     * and worst-worst case.
     */
    boolean computed() default false;

    enum Order {

        UNKNOWN,
        CONSTANT,
        LOGARITHMIC,
        LINEAR,
        LINEARITHMIC,
        QUADRATIC,
        EXPONENTIAL;

    }

}
