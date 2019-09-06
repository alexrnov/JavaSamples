package collection;

import java.util.*;

public class Collections {

  public static void main(String[] args) {
    //интерфейс List - описывает упорядоченные коллекции, в которых имеет
    //значение расположение элементов.
    System.out.println("ArrayList: ");
    arrayList();
    System.out.println("----------------------");
    System.out.println("LinkedList: ");
    linkedList();
    System.out.println("----------------------");
    //интерфейс Set - описывает структуры данных типа "хеш-таблица"
    System.out.println("hashSet: ");
    hashSet();
    System.out.println("----------------------");
    System.out.println("tree set: ");
    treeSet();
    //очереди
    System.out.println("----------------------");
    System.out.println("priorityQueue()");
    priorityQueue();
  }

  /*
   * Коллекция "списочный динамичный массив" - аналог обычного
   * динамического массива. Применение коллекции оправдано, когда
   * необходим произвольный досту к коллекции (метод get(i)).
   * Вместе с тем, коллекция неэффективно удаляет элементы из
   * середины списка с точки хрения потребляемых вычислительных ресурсов.
   * Это обусловлено тем, что при удалении элемента, все последующие элементы
   * приходится перемещать на одну позицию вверх.
   */
  private static void arrayList() {
    long time = System.currentTimeMillis();
    List<String> col = new ArrayList<>();
    //метод add() добавляет элемент в конец списка
    col.add("af"); col.add("bre"); col.add("ffdw"); col.add("ec");
    col.add("2");col.add("dsf");col.add("vdfs");col.add("fdgd");
    for (String s: col) {
      System.out.print(s + ", ");
    }
    System.out.println();
    //интерфейс ListIterator служит как для прохода по элементам, так
    //и для изменения и вставки эелементов в определенном месте
    ListIterator<String> iterator = col.listIterator();
    if (iterator.hasNext()) {
      iterator.next(); //пройти первый эелемент списка
    }
    iterator.set("a1");//устанавливает в первом элементе новое значение
    //вставить элементы в определенное место(после первого элемента)
    //с помощью listIterator
    iterator.add("a2");
    iterator.add("a3");
    for (String s: col) {
      System.out.print(s + ", ");
    }
    System.out.println();
    time = System.currentTimeMillis() - time;
    System.out.println("spent time = " + time);
  }

  /*
   * Коллекция "связный список" - позволяет эффективно удалять элемент
   * из середины списка с точки зрения потребляемых вычислительных ресурсов.
   * Основная причина для использования данной коллекции - минимизация
   * издержек на ввод и удаление элементов в середине списка
   * Связные списки обычно не применяются, когда необходимо обращение к
   * элементу списка по целочисленному индексу (с помощью метода get()).
   * Если требуется произвольный доступ к коллекции(метод get(i)), лучше
   * воспользоваться обычным или списочным массивом типа ArrayList.
   */
  private static void linkedList() {
    long time = System.currentTimeMillis();
    List<String> col = new LinkedList<>();
    //метод add() добавляет элемент в конец списка
    col.add("af"); col.add("bre"); col.add("ffdw"); col.add("ec");
    col.add("2");col.add("dsf");col.add("vdfs");col.add("fdgd");
    for (String s: col) {
      System.out.print(s + ", ");
    }
    System.out.println();
    //интерфейс ListIterator служит как для прохода по элементам, так
    //и для изменения и вставки эелементов в определенном месте
    ListIterator<String> iterator = col.listIterator();
    if (iterator.hasNext()) {
      iterator.next(); //пройти первый эелемент списка
    }
    iterator.set("a1");//устанавливает в первом элементе новое значение
    //вставить элементы в определенное место(после первого элемента)
    //с помощью listIterator
    iterator.add("a2");
    iterator.add("a3");
    for (String s: col) {
      System.out.print(s + ", ");
    }
    System.out.println();
    time = System.currentTimeMillis() - time;
    System.out.println("spent time = " + time);
    //начать с третьего индекса - такой итератор неэффективен, поскольку
    //основан на методе произвольного доступа get(i)
    ListIterator<String> iterator2 = col.listIterator(2);
    if (iterator2.hasNext()) {
      iterator2.next();
    }
    iterator2.add("01");
    for (String s: col) {
      System.out.print(s + ", ");
    }
    System.out.println();
  }

  /*
   * Коллекция "хеш-множество" используется если
   * порядок расположения элементов не имеет особого значения и
   * необходимо быстро находить элементы в коллекции. Изменяя элементы
   * множества нужно быть внимательным и аккуратным, поскольку если
   * хеш-код элемента изменится, этот элемент уже не будет находиться
   * на правильной позиции в структуре данных
   */
  private static void hashSet() {
    //конструирует пустое хеш-множество заданной начальной емкости
    //(10 элементов) и коэффециентом загрузки 0.75(числовым значением
    //от 0.0 до 1.0, определяющим иредельный процент заполнения
    //хеш-таблицы, при достижении которого происходит повторное хеширование)
    long time = System.currentTimeMillis();
    Set<String> col = new HashSet<>(10, 0.75f);
    col.add("af"); col.add("bre"); col.add("ffdw"); col.add("ec");
    col.add("2");col.add("dsf");col.add("vdfs");col.add("fdgd");
    for (String s: col) {
      System.out.print(s + ", ");
    }
    System.out.println();
    //эффективный метод поиска элемента. Он проверяет элементы только
    //одной группы, а не всего множества
    if (col.contains("vdfs")) {
      System.out.println("Элемент \"vdfs\" найден в коллекции");
    }
    time = System.currentTimeMillis() - time;
    System.out.println("spent time = " + time);
    Iterator<String> iterator = col.iterator();
    if (iterator.hasNext()) {
      iterator.next();
      iterator.remove();//удалить первый пройденный элемент
    }
    for (String s: col) {
      System.out.print(s + ", ");
    }
    System.out.println();
  }

  /*
   * Коллекция "древовидное множество" подобно хеш-множеству, но с
   * одним допольнительным усовершенствованием: его элементы отсортированы.
   * Сортировка обеспечивается древовидной структурой данных. Ввод элемента
   * в древовидное множество происходит медленнее, чем в хеш-таблицу,
   * но все же намного быстрее, чем в требуемое место массива или связного
   * списка
   *
   */
  private static void treeSet() {
    long time = System.currentTimeMillis();
    //сортировать элементыв обратном лексикографическом порядке
    Set<String> col = new TreeSet<>(Comparator.reverseOrder());
    col.add("af"); col.add("bre"); col.add("ffdw"); col.add("ec");
    col.add("2"); col.add("dsf"); col.add("vdfs"); col.add("fdgd");
    for (String s: col) {
      System.out.print(s + ", ");
    }
    System.out.println();
    if (col.contains("vdfs")) {
      System.out.println("Элемент \"vdfs\" найден в коллекции");
    }
    time = System.currentTimeMillis() - time;
    System.out.println("spent time = " + time);
  }

  private static void priorityQueue() {
    PriorityQueue<String> col = new PriorityQueue<>((a, b) -> b.compareTo(a));
    col.add("af"); col.add("bre"); col.add("ffdw"); col.add("ec");
    col.add("2"); col.add("dsf"); col.add("vdfs"); col.add("fdgd");
    for (String s: col) {
      System.out.print(s + ", ");
    }
    System.out.println();
    col.remove();
    for (String s: col) {
      System.out.print(s + ", ");
    }
    System.out.println();
  }
}
