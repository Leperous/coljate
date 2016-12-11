package net.coljate.set.lazy;

//package net.coljate.set.impl;
//
//import java.util.Iterator;
//import java.util.NoSuchElementException;
//import java.util.Objects;
//
//import net.coljate.collection.LazyCollection;
//import net.coljate.set.AbstractSet;
//import net.coljate.set.Set;
//
///**
// *
// * @author ollie
// */
//public class LazyIntersectionSet<T>
//        extends AbstractSet<T>
//        implements LazyCollection<T> {
//    
//    public static <T> Set<T> viewOf(final Set<? extends T> s1, final Set<? extends T> s2) {
//        if (s1 instanceof EmptySet || s2 instanceof EmptySet) {
//            return Set.of();
//        }
//        return new LazyIntersectionSet<>(s1, s2);
//    }
//    
//    private final Set<? extends T> s1, s2;
//    
//    protected LazyIntersectionSet(final Set<? extends T> s1, final Set<? extends T> s2) {
//        this.s1 = s1;
//        this.s2 = s2;
//    }
//    
//    @Override
//    public boolean contains(final Object object) {
//        return s1.contains(object) && s2.contains(object);
//    }
//    
//    @Override
//    public Iterator<T> iterator() {
//        return new Iterator<T>() {
//            
//            private boolean hasNext;
//            private T next;
//            private final Iterator<? extends T> iterator = s1.iterator();
//            
//            @Override
//            public boolean hasNext() {
//                while (iterator.hasNext()) {
//                    next = iterator.next();
//                    if (s2.contains(next)) {
//                        hasNext = true;
//                        return true;
//                    }
//                }
//                return false;
//            }
//            
//            @Override
//            public T next() {
//                if (!hasNext) {
//                    throw new NoSuchElementException();
//                }
//                hasNext = false;
//                return next;
//            }
//            
//        };
//    }
//    
//    @Override
//    protected boolean equals(final Set<?> that) {
//        return that instanceof LazyIntersectionSet
//                && this.equals((LazyIntersectionSet) that);
//    }
//    
//    protected boolean equals(final LazyIntersectionSet<?> that) {
//        return Objects.equals(this.s1, that.s1)
//                && Objects.equals(this.s2, that.s2);
//    }
//    
//}
