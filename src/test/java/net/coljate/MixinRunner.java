package net.coljate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import net.coljate.set.MutableSet;
import net.coljate.set.Set;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.JUnit4;
import org.junit.runners.model.FrameworkField;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.TestClass;

/**
 *
 * @author ollie
 * @see JUnit4
 */
public class MixinRunner extends BlockJUnit4ClassRunner {

    public MixinRunner(final Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected final TestClass createTestClass(final Class<?> testClass) {
        return new MixinDetectingTestClass(testClass);
    }

    protected static final class MixinDetectingTestClass extends TestClass {

        MixinDetectingTestClass(final Class<?> clazz) {
            super(clazz);
        }

        @Override
        protected void scanAnnotatedMembers(
                final Map<Class<? extends Annotation>, List<FrameworkMethod>> methodsForAnnotations,
                final Map<Class<? extends Annotation>, List<FrameworkField>> fieldsForAnnotations) {
            super.scanAnnotatedMembers(methodsForAnnotations, fieldsForAnnotations);
            for (final Class<?> interfaceClass : getInterfaces(this.getJavaClass(), MutableSet.createHashSet(4))) {
                for (final Method method : interfaceClass.getDeclaredMethods()) {
                    if (method.isDefault()) {
                        addToAnnotationLists(new FrameworkMethod(method), methodsForAnnotations);
                    }
                }
            }
        }

        private static Set<Class<?>> getInterfaces(final Class<?> clazz, final MutableSet<Class<?>> allInterfaces) {
            for (final Class<?> interfaceClass : clazz.getInterfaces()) {
                if (allInterfaces.add(interfaceClass)) {
                    getInterfaces(interfaceClass, allInterfaces);
                }
            }
            return allInterfaces;
        }

    }

}
