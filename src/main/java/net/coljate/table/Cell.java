package net.coljate.table;

/**
 *
 * @author ollie
 * @see Table
 */
public interface Cell<R, C, V> {

    R rowKey();

    C columnKey();

    V value();

    default ImmutableCell<R, C, V> immutableCopy() {
        return new ImmutableCell<>(this.rowKey(), this.columnKey(), this.value());
    }

}
