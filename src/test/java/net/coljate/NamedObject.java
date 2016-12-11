package net.coljate;

/**
 *
 * @author Ollie
 */
public class NamedObject extends Object {

    private final String name;

    public NamedObject(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
