package web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Демонстрация простого web-сервера, прослушивающиего порт 8189.
 * После запуска программы необходимо в командной строке
 * набрать: telnet localhost 8189. Серверу можно послать
 * любое сообщение, но завершит работу он при отправке сообщения "BYE"
 */
public class EchoServer {
  public static void main(String... args) throws IOException {
    System.out.println("server");
    //установить сокет на стороне сервера
    //(создать сервер с контролируемым портом 8189)
    try (ServerSocket s = new ServerSocket(8189)) {
      //ожидать подключения клиента к заданному порту
      try (Socket incoming = s.accept()) {
        InputStream inStream = incoming.getInputStream();
        OutputStream outStream = incoming.getOutputStream();
        try (Scanner in = new Scanner(inStream)) {
          PrintWriter out = new PrintWriter(outStream, true); //автоматическая отчистка
          out.println("Hello! Enter BYE to exit");
          //передать обратно данные, полученные от клиента
          boolean done = false;
          while (!done && in.hasNextLine()) {
            String line = in.nextLine();
            System.out.println(line);
            out.println("Echo: " + line);
            if (line.trim().equals("BYE")) {
              done = true;
            }
          }
        }
        System.out.println("Клиент отлючился");
      }
    }
  }
}
