package net.coljate.set;

import net.coljate.Collection;

/**
 *
 * @author ollie
 */
public interface Set<T> extends Collection<T> {

    @Override
    MutableSet<T> mutableCopy();

    @Override
    ImmutableSet<T> immutableCopy();

    @Override
    default java.util.Set<T> javaCollectionCopy() {
        final java.util.Set<T> set = new java.util.HashSet<>(this.count());
        this.forEach(set::add);
        return set;
    }

}
