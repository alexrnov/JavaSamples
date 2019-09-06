package annotation.demo2;

import java.lang.annotation.*;

/**
 * Аннотация для полей
 */
@Target(value= ElementType.FIELD)//этой аннотацией должно помечаться поле
@Retention(value= RetentionPolicy.RUNTIME)//жизненный цикл аннотации
@Documented//аннотация должна быть добавлена в javadoc
@Inherited//аннотация будет унаследована потомком класса, отмеченного такой аннотацией
public @interface FieldAn {
  String name();
  String type1() default "string";
  String type2();
}
