package com.hiatus.eqhash;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A class that provides a method to test your equals() and hashCode() methods, easily and thoroughly.
 * A simple way to get good coverage and a high mutation score for your object with PIT etc.
 * 
 * @author Andrew Regan
 */
public final class EqHash {

    private EqHash() {}

    /**
     * Test your equals() and hashCode() methods.
     * 
     * @param inObj an instance of your class
     * @param inCopy another instance that you assert is a copy of inObj
     * @param inDifferentObjects an array of objects you assert are all different from inObj
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void testEqualsHashcode( final Object inObj, final Object inCopy, final Object... inDifferentObjects)
    {
        // First up, check the types match...
        assertThat((Class) inObj.getClass(), allOf( equalTo((Class) inCopy.getClass() ) ));

        assertThat( inObj.equals(null), is(false));  // NOPMD
        assertThat( inObj.equals((Object) new CompletelyDifferentClass()), is(false));
        assertThat( inObj, equalTo(inObj));
        assertThat( "Copy object should not == source", inCopy, not( sameInstance(inObj) ));
        assertThat( "Source object should equal the Copy object", inObj, equalTo(inCopy));

        assertThat( "Source.hashCode() should equal itself", inObj.hashCode(), equalTo( inObj.hashCode() ));
        assertThat( "Source.hashCode() should equal the Copy.hashCode()", inObj.hashCode(), equalTo( inCopy.hashCode() ));

        final Set<Object> diffObjects = new HashSet<Object>();
        diffObjects.addAll( Arrays.asList(inDifferentObjects) );
        assertThat( "Duplicate Different objects are present: counts differ", inDifferentObjects.length, is(diffObjects.size()));

        for ( Object eachDiffObject : inDifferentObjects) {
            assertThat((Class) inObj.getClass(), equalTo((Class) eachDiffObject.getClass() ));
            assertThat( "Different object should not == source", eachDiffObject, not( sameInstance(inObj) ));
            assertThat( "Different object should not equal the Source/Copy object", inObj, not( equalTo(eachDiffObject) ));
            assertThat( "Source.hashCode() should (probably) not equal the Different.hashCode()", inObj.hashCode(), not( equalTo( eachDiffObject.hashCode() ))); // (AGR) ;-)
        }
    }

    private static class CompletelyDifferentClass {
        // NOOP
    }
}