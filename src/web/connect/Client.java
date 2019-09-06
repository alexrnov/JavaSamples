package web.connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Клиент. Запускается после сервера. Отправляет серверу строку и
 * получает ответ в виде: "echo size: + длина строки".
 * Отключается от соединения если введено слово "exit"
 */
public class Client {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s = "";
    Socket socket = new Socket("localhost", 8189);
    Scanner in = new Scanner(socket.getInputStream());
    PrintWriter writer = new PrintWriter(socket.getOutputStream());
    while (!s.equals("exit")) { //организовать цикл ввода слов с консоли
      System.out.print("Enter string: ");
      s = br.readLine();
      writer.println(s);
      writer.flush();
      if (in.hasNextLine()) {
        String line = in.nextLine();
        System.out.println(line);
      }
    }
    socket.close();
  }
}
