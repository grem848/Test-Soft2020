package dk.cphbusiness.verifier.utils;

public class AssertionException extends RuntimeException {
  private Object actual;
  private Object expected;

  public AssertionException(String message, Object actual, Object expected) {
    super(message);
    this.actual = actual;
    this.expected = expected;
    }

  public Object getActual() { return actual; }
  public Object getExpected() { return expected; }
  }
