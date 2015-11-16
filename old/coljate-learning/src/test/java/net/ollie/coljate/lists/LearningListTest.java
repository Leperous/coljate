package net.ollie.coljate.lists;

import org.junit.Test;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"rawtypes", "unchecked", "null"})
public class LearningListTest {

    public LearningListTest() {
    }

    @Test
    public void testSomeMethod() {
        final List list1 = this.makeSomething();
        final List list2 = this.makeSomethingElse();
        final List list3 = this.makeSomething();
    }

    private List makeSomething() {
        return LearningList.create(() -> {});
    }

    private List makeSomethingElse() {
        return LearningList.create(() -> {});
    }

}
