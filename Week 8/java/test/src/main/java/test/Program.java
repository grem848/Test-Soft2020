package test;

import java.lang.reflect.Method;

public class Program {

  public static void main(String[] args) throws Exception {
    Method[] methods = FooTest.class.getMethods();
    FooTest test = new FooTest();
    for (Method method : methods) {
      if (!method.getName().startsWith("test")) continue;
      System.out.println("Invoking test method: "+method.getName());
      method.invoke(test);
      }
    }

  }
