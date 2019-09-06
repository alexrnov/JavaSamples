package web.thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * В этой программе реализуется многопоточный сервер, прослушивающий
 * порт 8189 и передающий обратно все данные, полученные от клиентов
 */
public class ThreadEchoServer {
  public static void main(String[] args) {
    try {
      int i = 1; //номер клиента
      ServerSocket s = new ServerSocket(8189);
      while (true) {
        Socket incoming = s.accept();
        System.out.println("spawning = " + i);
        Runnable r = new ThreadedEchoHandler(incoming, i);
        Thread t = new Thread(r);
        t.start();
        i++;
      }
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
