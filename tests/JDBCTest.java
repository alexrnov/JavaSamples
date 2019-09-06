import jdbs.DummyQuery;
import jdbs.JDBSSimple;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JDBCTest {

  private JDBSSimple simple1;
  private DummyQuery dummyQuery;

  @Before
  public void up() {
    simple1 = new JDBSSimple();
    dummyQuery = new DummyQuery();
  }

  @After
  public void down() {
    simple1 = null;
    dummyQuery = null;
  }

  @Test
  public void test1() {
    simple1.method1();
  }

  @Test
  public void test2() {
    dummyQuery.method1();
  }
}
