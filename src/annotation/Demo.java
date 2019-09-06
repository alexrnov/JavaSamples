package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Демонстрация основных механизмов аннотирования
 */
public class Demo {

  public Demo() {
  }

  @ann(type = "4", val = 5, array = {43, 3, 43, 34})
  public void method1() {
    System.out.println("method1");
  }

  @ann //маркерная аннотация - т.е. без элементов
  public void method2() {

  }

  @ann2("v") //однозначная аннотация
  public void method3() {

  }

  @Deprecated
  public void method4() {

  }
}

//мета-аннотация(аннотация к аннотации). Определяет, что аннотация ann\
//должна применяться к методу
@Target(ElementType.METHOD)
//мета-аннотация определяет, что аннотация включается в файл класса
//и загружается виртуальной машиной. Они доступны через прикладной
//интерфейс API для рефлексии
@Retention(RetentionPolicy.RUNTIME)
@interface ann {
  String type() default "3";
  int val() default 1;
  int[] array() default {3,2,4};
}

@interface ann2 {
  String value();
}