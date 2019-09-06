package sorting;

public class Class3 extends Class2 {
  private Double param2 = 0.0;

  public Class3(String name, Integer age, Integer param) {
    super(name, age, param);
  }

  public void setParam2(Double param2) {
    this.param2 = param2;
  }

  public Double getParam2() {
    return param2;
  }

  @Override
  public void print() {
    System.out.println(getName() + " " + getAge() +
            " " + getParam() + " " + param2);
  }
}
