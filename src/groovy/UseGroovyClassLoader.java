package groovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Демонстрация вызова кода groovy из java
 */
public class UseGroovyClassLoader {
  public static void main(String[] args) {
    GroovyClassLoader loader = new GroovyClassLoader();
    try {
      //получение класса groovy
      Class<?> groovyClass = loader.parseClass(new File("./src/groovy/Calculate.groovy"));
      //получение экземпляра groovy
      GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
      ArrayList<Integer> list = new ArrayList<>();
      list.add(5);
      list.add(6);
      list.add(9);
      list.add(2);
      Object[] arguments = {list};
      //вызов метода groovy
      Object value = groovyObject.invokeMethod("getMax", arguments);
      Integer v = (Integer)value;
      System.out.println("maximum value = " + v);

      Object[] arguments2 = {"java", "(demo)"};
      Object value2 = groovyObject.invokeMethod("getString", arguments2);
      String s = (String) value2;
      System.out.println("new string = " + s);
    } catch(IOException | InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
    }
  }
}
