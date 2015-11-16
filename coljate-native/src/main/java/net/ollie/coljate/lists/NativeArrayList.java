package net.ollie.coljate.lists;

/**
 *
 * @author Ollie
 */
public abstract class NativeArrayList<T> extends NativeList<T> {

    protected NativeArrayList(final java.util.ArrayList<T> delegate) {
        super(delegate);
    }

}
