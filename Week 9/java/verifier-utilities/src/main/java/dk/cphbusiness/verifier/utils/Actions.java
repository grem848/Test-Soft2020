package dk.cphbusiness.verifier.utils;

public class Actions {
  public static void assertEquals(String message, Object actual, Object expected) {
    if (actual != null && actual.equals(expected) || actual == expected) return;
    throw new AssertionException(message, actual, expected);
    }
  }
