package sorting;

public class Class2 extends Class1 {
  private Integer param;

  public Class2(String name, Integer age, Integer param) {
    super(name, age);
    this.param = param;
  }

  public Integer getParam() {
    return param;
  }

  @Override
  public void print() {
    System.out.println(getName() + " " + getAge() + " " + param);
  }
}
