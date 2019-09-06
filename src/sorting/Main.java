package sorting;

import java.util.*;

/**
 * Данный класс демонстрирует различные способы сортировки по одному,
 * двум, и трем параметрам. При этом могут ипользоваться как старые
 * стили с передачей анонимных классов, так и записи в стиле java8.
 */
public class Main {

  public static void main(String[] args) {
    List<Integer> list = Arrays.asList(8, 5, 3, 9, 4);
    System.out.print("Сортировка чисел: ");
    print(list);
    //сортировка в старом стиле
    list.sort(new Comparator<Integer>() {
      @Override
      public int compare(Integer a, Integer b) {
        return a.compareTo(b);
      }
    });
    System.out.print("Сортировка по возрастанию: ");
    print(list);

    //сортировка в функциональном стиле
    list.sort((a, b) -> b.compareTo(a));
    System.out.print("Сортировка по убыванию: ");
    print(list);

    list.sort(Comparator.naturalOrder());
    System.out.print("Сортировка по возрастанию: ");
    print(list);
    Collections.sort(list, (a, b) -> b.compareTo(a));
    System.out.print("Сортировка по убыванию: ");
    print(list);

    System.out.println("-----------------------------");
    List<Class1> list2 = new ArrayList<>();
    list2.add(new Class1("alex", 45));
    list2.add(new Class1("bob", 56));
    list2.add(new Class1("carl", 89));
    list2.add(new Class1("sam", 45));
    list2.add(new Class1("alex", 76));
    list2.add(new Class1("carl", 56));
    list2.add(new Class1("alex", 45));

    list2.sort(new Comparator<Class1>() {
      @Override
      public int compare(Class1 a, Class1 b) {
        String nameSize1 = a.getName();
        String nameSize2 = b.getName();
        return nameSize1.compareTo(nameSize2);
      }
    });
    System.out.println("Сортировка по имени: ");
    list2.forEach(Class1::print);
    System.out.println("----------------------------");

    System.out.println("Сортировка по возрасту: ");
    list2.sort((a, b) -> a.getAge().compareTo(b.getAge()));
    list2.forEach(e -> e.print());
    System.out.println("----------------------------");

    // java8 многофакторная сортировка, используется составное выражение
    System.out.println("Многофакторная сортировка, сначала по имени, потом по возрасту: ");
    list2.sort(Comparator.comparing(Class1 :: getName)
            .thenComparing(Class1 :: getAge));
    list2.forEach(e -> e.print());
    System.out.println("----------------------------");

    list2.sort(new Comparator<Class1>() { //старый стиль многофакторной сортировки
      @Override
      public int compare(Class1 a, Class1 b) {
        if (a.getName().equals(b.getName())) {
          return b.getAge() - a.getAge();
        } else {
          return b.getName().compareTo(a.getName());
        }
      }
    });

    System.out.println("Многофакторная сортировка по убыванию: ");
    list2.forEach(e -> e.print());
    System.out.println("----------------------------");


    Random r = new Random();
    List<Class2> list3 = new ArrayList<>();
    for (Class1 c : list2) {
      Class2 cl = new Class2(c.getName(), c.getAge(), r.nextInt(9));
      list3.add(cl);
    }

    list3.sort((a, b) -> {
      if (a.getAge().equals(b.getAge())) {
        //return a.getParam() - b.getParam();//аналогично нижнему
        return a.getParam().compareTo(b.getParam());
      } else {
        return a.getAge().compareTo(b.getAge());
      }
    });

    System.out.println("Многофакторная сортировка сначала по возрасту" +
            ", потом по третьему параметру: ");
    list3.forEach(e -> e.print());
    System.out.println("----------------------------");


    List<Class3> list5 = new ArrayList<>();
    for(int k = 0; k < 20; k++) {
      Class3 q = new Class3("a", r.nextInt(3), r.nextInt(3));
      Double v = r.nextDouble();
      q.setParam2(Math.round(v * 1.0) / 1.0);
      list5.add(q);
    }

    //сортировка по трем компонентам без использования составного выражения
    Comparator<Class3> f = (a, b) -> {
      if (a.getAge().equals(b.getAge())) {
        if (a.getParam().equals(b.getParam())) {
          return a.getParam2().compareTo(b.getParam2());
        } else {
          return a.getParam().compareTo(b.getParam());
        }
      } else {
        return a.getAge().compareTo(b.getAge());
      }
    };

    System.out.println("Неотсортированный массив: ");
    list5.forEach(e -> e.print());
    list5.sort(f);
    System.out.println("----------------------------");
    System.out.println("Многофакторная сортировка по трем параметрам (второму, третьему, четвертому): ");
    list5.forEach(e -> e.print());
  }

  private static void print(List<Integer> list) {
    list.forEach(e -> {
      System.out.print(e + " ");
    });
    System.out.println();
  }
}

