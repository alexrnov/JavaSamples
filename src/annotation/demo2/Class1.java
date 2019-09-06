package annotation.demo2;

/**
 * Аннотируемый класс
 */
@ClassAn(type=7)
public class Class1 {
  @FieldAn(name = "field annotation", type2 = "d")
  private int i = 5;

  @MethodAn(name = "m1")
  public void method1() {
    System.out.println(i);
  }
}
