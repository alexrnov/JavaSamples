package fundamental;

/**
 * Демонстрация финализаторов
 */
public class Finalizer {
  public static void main(String[] args) throws Exception {
    Class1 class1 = new Class1();
    class1.setTrue();
    //class1.setFalse();
    class1 = new Class1();
    System.gc();//вызвать уборщик мусора
    /*
    вместо устаревшего finalize используется объект, реализующий
    интерфейс AutoCloseable. Этот объект обрабатывается в конструкторе
    try с ресурсами
    */
    try(Class2 class2 = new Class2()) {
      class2.setTrue();
    }

  }
}

class Class1 {
  private boolean b = false;

  public void setTrue() {
    b = true;
  }

  public void setFalse() {
    b = false;
  }

  /**
   * Устаревший метод финализации
   */
  @Override
  public void finalize() {
    if (b) {
      System.out.println("fundamental.Class1: b should be false");
    }
  }
}


/**
 * Объект данного класса вызывает метод close при окончании
 * использования объекта
 */
class Class2 implements AutoCloseable {

  private boolean b;

  public void setTrue() {
    b = true;
  }

  public void setFalse() {
    b = false;
  }

  @Override
  public void close() throws Exception {
    if (b) {
      throw new Exception("fundamental.Class2: b should be false");
    }
  }
}
