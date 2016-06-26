package net.ollie.coljate;

/**
 *
 * @author Ollie
 */
public interface ObjectCollectionBuilder extends CollectionBuilder<Object> {

    Collection<Object> createFrom(Object... objects);

    @Override
    default Collection<Object> create() {
        return this.createFrom();
    }

    @Override
    default Collection<Object> create(final Object object) {
        return this.createFrom(object);
    }

    @Override
    default Collection<Object> create(Object first, Object second) {
        return this.createFrom(first, second);
    }

}
