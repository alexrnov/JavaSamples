import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import web.SocketDemo;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

public class WebTest {
  private SocketDemo socketDemo;

  @Before
  public void up() {
    socketDemo = new SocketDemo();
  }

  @After
  public void down() {
    socketDemo = null;
  }

  @Test
  public void SocketDemo1() {
    try {
      socketDemo.methodSocket();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void SocketDemo2() {
    try {
      socketDemo.methodSocketTimeout();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void inetAddressDemo() {
    socketDemo.methodInetAddress();
  }


}
