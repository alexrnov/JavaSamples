import annotation.Demo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AnnotationTest {
  private Demo demo;

  @Before
  public void up() {
    demo = new Demo();
  }

  @After
  public void down() {
    demo = null;
  }

  @Test
  public void test() {
    demo.method1();
  }
}
