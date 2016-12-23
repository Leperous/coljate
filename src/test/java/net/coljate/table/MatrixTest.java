package net.coljate.table;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public interface MatrixTest<T> extends TableTest<Integer, Integer, T> {

    @Override
    Matrix<T> create(java.util.List<Cell<Integer, Integer, T>> cells);

    @Override
    @Deprecated
    default Cell<Integer, Integer, T> createObject() {
        return this.createObject(0, 0);
    }

    Cell<Integer, Integer, T> createObject(int row, int column);

    @Test
    default void testRowMatrix() {
        final Matrix<T> matrix = this.create(Arrays.asList(this.createObject(0, 0), this.createObject(0, 1)));
        assertThat(matrix.width(), is(2));
        assertThat(matrix.height(), is(1));
    }

}
