package net.coljate.table;

import net.coljate.set.AbstractSet;

/**
 *
 * @author ollie
 */
public abstract class AbstractTable<R, C, V>
        extends AbstractSet<Cell<R, C, V>>
        implements Table<R, C, V> {

    @Override
    public boolean contains(Object object) {
        return Table.super.contains(object);
    }

}
