package annotation.demo2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для классов
 */
@Target(value= ElementType.TYPE)//этой аннотацией помечается класс или интерфейс
@Retention(value= RetentionPolicy.RUNTIME)
public @interface ClassAn {
  String name() default "class!!!";
  int type();
}
