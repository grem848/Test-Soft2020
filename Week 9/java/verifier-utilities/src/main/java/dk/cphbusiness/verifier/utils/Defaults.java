package dk.cphbusiness.verifier.utils;

public class Defaults {
  // These gets initialized to their default values
  private static boolean DEFAULT_BOOLEAN;
  private static byte DEFAULT_BYTE;
  private static short DEFAULT_SHORT;
  private static int DEFAULT_INT;
  private static long DEFAULT_LONG;
  private static float DEFAULT_FLOAT;
  private static double DEFAULT_DOUBLE;

  public static Object getDefaultValue(Class klass) {
    if (klass.equals(boolean.class)) return DEFAULT_BOOLEAN;
    if (klass.equals(byte.class)) return DEFAULT_BYTE;
    if (klass.equals(short.class)) return DEFAULT_SHORT;
    if (klass.equals(int.class)) return DEFAULT_INT;
    if (klass.equals(long.class)) return DEFAULT_LONG;
    if (klass.equals(float.class)) return DEFAULT_FLOAT;
    if (klass.equals(double.class)) return DEFAULT_DOUBLE;
    return null;
    }
  }

