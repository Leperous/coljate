package net.ollie.coljate.list;

import org.checkerframework.checker.nullness.qual.NonNull;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public abstract class ImmutableListTest extends ListTest {

    @NonNull
    @Override
    public abstract ImmutableList<Object> createFrom(Object... objects);

}
