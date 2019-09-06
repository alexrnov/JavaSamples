package web.thread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Этот класс обрабатывает данные, получаемые сервером от
 * клиента через одно сокетное соединение
 */
public class ThreadedEchoHandler implements Runnable {
  private Socket incoming;
  private int numberOfClient;
  ThreadedEchoHandler(Socket socket, int i) {
    incoming = socket;
    numberOfClient = i;
  }

  public void run() {
    try {
      try {
        InputStream inStream = incoming.getInputStream();
        OutputStream outStream = incoming.getOutputStream();
        Scanner in = new Scanner(inStream);
        PrintWriter out = new PrintWriter(outStream, true);//автоматисческая отчистка
        out.println("Hello! Enter BYE to exit.");
        //передать обратно данные, полученные от клиента
        boolean done = false;
        while (!done && in.hasNextLine()) {
          String line = in.nextLine();
          System.out.println("сообщение от клиента " + numberOfClient
                  + ": " + line);
          out.println("Echo: " + line);
          if (line.trim().equals("BYE")) {
            done = true;
          }
        }
      } finally {
        incoming.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
