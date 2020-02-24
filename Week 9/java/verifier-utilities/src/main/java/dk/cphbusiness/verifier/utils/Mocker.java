package dk.cphbusiness.verifier.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import static dk.cphbusiness.verifier.utils.Defaults.*;

public class Mocker implements InvocationHandler {
  private Map<String, Object> expectations = new HashMap<>();
  private static Mocker mockerInvoked = null;
  private static Method methodInvoked = null;

  public static <T> T mock(Class<T> klass) {
    ClassLoader loader = ClassLoader.getSystemClassLoader();
    return (T)Proxy.newProxyInstance(loader, new Class[] { klass }, new Mocker());
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

  public static Invokation when(Object value) {
    return new Invokation(mockerInvoked, methodInvoked);
    }

  public static class Invokation {
    private final Mocker mocker;
    private final Method method;

    Invokation(Mocker mocker, Method method) {
      this.mocker = mocker;
      this.method = method;
      }
    void doReturn(Object result) {
      String key = keyOf(method);
      mocker.expectations.put(key, result);
      }
    void doThrow(Throwable throwable) {

      }
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
    return getDefaultValue(method.getReturnType());
    }

  public static void main(String[] args) {
    Bank bank = Mocker.mock(Bank.class);
    Mocker.when(         // called #3
        bank.getName(),  // called #1
        "KurtsBank"      // called #2
        );
    Mocker.when(bank.getManager()).doReturn("Sonja");
    Mocker.when(bank.getBalance()).doReturn(1000l);

    System.out.println(bank.getName()+" managed by "+bank.getManager());
    System.out.println(bank.getBalance());
    bank.setBalance(30000);
    }
  }
