package test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Mocker implements InvocationHandler {
  private Map<String, Object> expectations = new HashMap<>();
  private static Mocker mockerInvoked = null;
  private static Method methodInvoked = null;

  public static Object mock(Class klass) {
    ClassLoader loader = ClassLoader.getSystemClassLoader();
    return Proxy.newProxyInstance(loader, new Class[] { klass }, new Mocker());
    }

  private static String keyOf(Method method) {
    return method.toString()+":"+
        Arrays.stream(method.getParameterTypes())
        .map( paramType -> paramType.toString())
        .collect(Collectors.joining("#"));
    }

  public static void when(Object value, Object result) {
    System.out.println("when called with "+result);
    if (mockerInvoked == null || methodInvoked == null) throw new RuntimeException("Hovsa");
    String key = keyOf(methodInvoked);
    mockerInvoked.expectations.put(key, result);
    methodInvoked = null;
    mockerInvoked = null;
    }

    @Override
  public Object invoke(
      Object proxy,
      Method method,
      Object[] args) throws Throwable {
    String key = keyOf(method);
    if (expectations.containsKey(key)) {
      return expectations.get(key);
      }
    mockerInvoked = this;
    methodInvoked = method;
    System.out.println("You called "+method.getName()+" without expectations");
    return null;
    }

  public static void main(String[] args) {
    Bank bank = (Bank)Mocker.mock(Bank.class);
    Mocker.when(         // called #3
        bank.getName(),  // called #1
        "KurtsBank"      // called #2
        );
    Mocker.when(bank.getManager(), "Sonja");

    System.out.println(bank.getName()+" managed by "+bank.getManager());
    }
  }
