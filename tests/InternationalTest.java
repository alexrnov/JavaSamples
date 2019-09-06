import international.Localise;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InternationalTest {
  Localise localise;

  @Before
  public void up() {
    localise = new Localise();
  }

  @After
  public void down() {
    localise = null;
  }

  @Test
  public void test1() {
    localise.method1();
  }

}
