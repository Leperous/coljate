package net.ollie.coljate.set;

/**
 *
 * @author Ollie
 */
public interface Trie extends Set<String> {

    boolean isEndOfWord();

    Set<Trie> children();

    @Override
    @Deprecated
    default boolean contains(final Object object) {
        return object instanceof String && this.contains((String) object);
    }

    boolean contains(String string);

    @Override
    default boolean isEmpty() {
        return this.children().isEmpty();
    }

}
