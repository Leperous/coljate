package net.coljate.list.impl;

import java.io.Serializable;

/**
 *
 * @author ollie
 */
public class MutableWrappedLinkedList<T>
        extends MutableWrappedList<T>
        implements Serializable {

    protected MutableWrappedLinkedList(final java.util.LinkedList<T> delegate) {
        super(delegate);
    }

}
