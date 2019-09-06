package groovy

/**
 * Простая программа на языке groovy
 */
class GroovyClass {
  static void main(String[] args) {
    println "Groovy"
    def x //определение переменной без указания конкретного типа
    x = 4
    x = "dsds"
    println x

    //позволяет сохранять в списке значения смешанных типов
    def arr = [1, "b", new Date(), "d", "e", "f"]
    println(arr[1])

    def glossary = [first:3, second:4]//словарь
    println(glossary["first"])


    List<Class1> list = [new Class1("a"), null, new Class1("b"),
                         null, new Class1("c")]
    for (Class1 c: list) {
      print(c?.getName() + " ")//оператор безопасного разыменования
    }
    println()
    println("-------------------")
    def func1 = { //функциональный литерал
      name -> if (name == "abc") {
        "first"
      } else {
        "second"
      }
    }
    method1(func1)
    println "-------------------"
    def func2 = { a, b -> a + b
    }
    println(func2(5,4))

    List<Class1>list2 = [new Class1("3"), new Class1("5"), new Class1("4")]
    list2.each({e -> print(e.getName() + " ")})
    println()
    list2.each({print(it.getName() + " ")}) //неявная переменная it
    println()
    [new Class1("a"), null, new Class1("d")].each({print(it?.getName() + " ")})

  }

  static void method1(def f) {
    String s = f("abc")
    println s
  }
}

class Class1 {
  String name;
  Class1(String name) {
    this.name = name;
  }
  String getName() {
    return name;
  }
}
