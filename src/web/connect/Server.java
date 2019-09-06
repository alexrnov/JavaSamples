package web.connect;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Сервер. Запускается перед подключением клиента. Принимает от клиента
 * слово, вычисляет его размер и отправляет обратно
 * сообщение: "echo size: + длина слова"
 */
public class Server {
  public static void main(String[] args) throws IOException {
    try (ServerSocket s = new ServerSocket(8189)) {
      System.out.println("server start");
      //ожидать подключение клиента к заданному порту
      try (Socket incoming = s.accept()) {
        System.out.println("client connect");
        InputStream inStream = incoming.getInputStream();
        OutputStream outStream = incoming.getOutputStream();
        try (Scanner in = new Scanner(inStream)) {
          PrintWriter out = new PrintWriter(outStream, true);//автоматическая отчистка
          while (in.hasNextLine()) {
            String line = in.nextLine();
            System.out.println(line);
            int size = line.length();
            out.println("Echo size: " + size);
            out.flush();
          }
        }
        System.out.println("client disconnect");
      }
    }
  }
}
