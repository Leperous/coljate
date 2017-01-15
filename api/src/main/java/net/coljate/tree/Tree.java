package net.coljate.tree;

import javax.annotation.CheckForNull;

import net.coljate.set.Set;

/**
 *
 * @author ollie
 */
public interface Tree<N> extends Set<N> {

    @CheckForNull
    N root();

}
