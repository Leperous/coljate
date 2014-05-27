package net.ollie.sc4j.imposed;

/**
 *
 * @author Ollie
 */
public interface ThreadSafe {

    interface Locking extends ThreadSafe {
    }

    interface Concurrent extends ThreadSafe {
    }

}
