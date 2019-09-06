package groovy

/**
 * Код данного класса вызывается в java (в классе UseGroovyClassLoader.java)
 */
class Calculate {

  Integer getMax(List values) {
    values.max()
  }

  String getString(String s, String s2) {
    s + " & groovy " + s2
  }
}
