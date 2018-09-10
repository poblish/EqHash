package org.hiatusuk.eqhash;

import java.util.Arrays;
import java.util.Objects;

import org.testng.annotations.Test;

public class EqHashTest {

    @Test
    public void testNumbers() {
        Long orig = 1234L;
        Long copy = 1234L;
        EqHash.testEqualsHashcode( orig, copy, 1235L, 1236L);
    }

    @Test
    public void testCustomObject() {
        final TestContainer orig = new TestContainer( 1, "a", new byte[]{46});
        final TestContainer copy = new TestContainer( 1, "a", new byte[]{46});
        final TestContainer diff1 = new TestContainer( 1, "a", new byte[]{46,47});
        final TestContainer diff2 = new TestContainer( 2, "a", new byte[]{46});
        final TestContainer diff3 = new TestContainer( 1, "b", new byte[]{46,47});
        EqHash.testEqualsHashcode( orig, copy, diff1, diff2, diff3);
    }

    private static class TestContainer {
        private final int x;
        private final String y;
        private final byte[] z;

        TestContainer(int x, String y, byte[] z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public int hashCode() {
            return Objects.hash( x, y, Arrays.hashCode(z));
        }
        @Override
        public boolean equals( Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (!(obj instanceof TestContainer)) {
                return false;
            }
            TestContainer other = (TestContainer) obj;
            return equal( x, other.x) && equal( y, other.y) && Arrays.equals( z, other.z);
        }
    }

    private static boolean equal(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }
}
