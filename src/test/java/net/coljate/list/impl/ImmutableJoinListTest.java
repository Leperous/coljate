package net.coljate.list.impl;

import net.coljate.list.ImmutableArray;
import net.coljate.list.ImmutableList;
import net.coljate.list.ImmutableListTest;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class ImmutableJoinListTest extends ImmutableListTest {

    @Override
    protected <T> ImmutableList<T> create(final T... elements) {
        ImmutableList<T> join = ImmutableArray.of();
        for (final T element : elements) {
            join = ImmutableJoinList.of(join, ImmutableArray.of(element));
        }
        return join;
    }

}
