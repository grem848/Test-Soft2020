package test;

import static test.Actions.*;

public class FooTest {

  public void testFoo() {
    String foo = "Foo";
    String result = foo.toUpperCase();
    assertEquals("Result should be upper case", result, "FOO");
    assertEquals("Length should not differ", result.length(), foo.length());
    }

  public void testBar() {
    System.out.println("Bar");
    }
  }
