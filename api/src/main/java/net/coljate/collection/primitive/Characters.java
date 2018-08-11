package net.coljate.collection.primitive;

public enum Characters implements LongContainer {

    ALL(c -> true),
    DIGITS(Character::isDigit),
    LOWERCASE(Character::isLowerCase),
    UPPERCASE(Character::isUpperCase),
    WHITESPACE(Character::isWhitespace),
    NONE(c -> false) {
        @Override
        public boolean isEmpty() {
            return true;
        }
    };

    private final CharacterPredicate predicate;

    Characters(final CharacterPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean contains(long i) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public interface CharacterPredicate {

        boolean testChar(char c);

    }

}
