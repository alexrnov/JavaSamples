package fundamental;

/**
 * Демонстрация нисходящего преобразования типов
 */
public class BackingCasting {
  public static void main(String[] args) {

    ClassA[] arr = {
            new ClassA(),
            new ClassB(),
            new ClassA(),
            new ClassB()
    };
    for (ClassA a: arr) {
      if (a instanceof ClassB) {
        ((ClassB)a).info();
      } else {
        a.print();
      }
    }
  }

}

class ClassA {
  public void print() {
    System.out.println("ClassA");
  }
}

class ClassB extends ClassA {
  @Override
  public void print() {
    System.out.println("ClassB");
  }
  public void info() {
    System.out.println("realize only ClassB");
  }
}
