EqHash
======

[![Build Status](https://travis-ci.org/poblish/EqHash.svg?branch=master)](https://travis-ci.org/poblish/EqHash)  [![codecov.io](https://codecov.io/github/poblish/EqHash/coverage.svg?branch=master)](https://codecov.io/github/poblish/EqHash?branch=master) [![Maven Central](https://img.shields.io/maven-central/v/org.hiatusuk/eqHash.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22org.hiatusuk%22%20AND%20a:%22eqHash%22)

The one Java class / method you need to test your objects' `equals()` and `hashCode()` methods.

* Save boilerplate code
* Get **code coverage** closer to 100%
* Best of all, get better mutation testing scores when using [PIT](http://pitest.org/) et al.

```java
public class EqHashTest {

  @Test
  public void testCustomObject() {
    final TestContainer orig = new TestContainer( 1, "a", new byte[]{46});
    // Assert that copy has same hashCode(), is equal etc.
    final TestContainer copy = new TestContainer( 1, "a", new byte[]{46});
    // Test all permutations for different hashCode(), not equal etc.
    final TestContainer diff1 = new TestContainer( 1, "a", new byte[]{46,47});
    final TestContainer diff2 = new TestContainer( 2, "a", new byte[]{46});
    final TestContainer diff3 = new TestContainer( 1, "b", new byte[]{46,47});

    EqHash.testEqualsHashcode( orig, copy, diff1, diff2, diff3);
  }

  [...]

  private static class TestContainer {
    private int x;
    private String y;
    private byte[] z;

    public TestContainer(int x, String y, byte[] z) {
      // Set fields etc.
    }

```
