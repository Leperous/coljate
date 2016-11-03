package net.coljate.list;

import net.coljate.Collection;

/**
 *
 * @author ollie
 */
public interface List<T> extends Collection<T> {

    /**
     *
     * @return the length of this list. It will be equal to or greater than the
     * {@link #count}.
     */
    int length();

    @Override
    MutableList<T> mutableCopy();

    @Override
    ImmutableList<T> immutableCopy();

    @Override
    @Deprecated
    default java.util.List<T> javaCollectionCopy() {
        final java.util.List<T> list = new java.util.ArrayList<>(this.count());
        this.forEach(list::add);
        return list;
    }

}
