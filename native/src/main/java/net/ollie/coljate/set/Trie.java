package net.ollie.coljate.set;

/**
 *
 * @author Ollie
 */
public interface Trie extends Set<String> {

    boolean isEndOfWord();

    @Override
    @Deprecated
    default boolean contains(final Object object) {
        return object instanceof String && this.contains((String) object);
    }

    boolean contains(String string);

}
