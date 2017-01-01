package net.coljate.map;

/**
 *
 * @author Ollie
 */
public interface BidirectionalMapTest<K, V> extends MapTest<K, V> {

    @Override
    BidirectionalMap<K, V> createTestCollection();

    interface ZeroEntryTests<K, V> extends BidirectionalMapTest<K, V>, MapTest.ZeroEntryTests<K, V> {

    }

    interface OneEntryTests<K, V> extends BidirectionalMapTest<K, V>, MapTest.OneEntryTests<K, V> {

    }

}
