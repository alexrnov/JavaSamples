package web;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SocketDemo {
  public SocketDemo() {

  }

  //метод соединения с сервером без установленного времени ожидания
  public void methodSocket() throws IOException {
    try (Socket s = new Socket("time-A.timefreq.bldrdoc.gov", 13)) {
      if (s.isConnected()) {
        System.out.println("connect");
      } else {
        System.out.println("not connect");
      }
      InputStream inStream = s.getInputStream();
      Scanner in = new Scanner(inStream);
      while (in.hasNextLine()) {
        String line = in.nextLine();
        System.out.println(line);
      }
    }
  }

  //метод соединения с сервером с установленным временем ожидания
  public void methodSocketTimeout() throws IOException {
    try (Socket s = new Socket()) {
      s.connect(new InetSocketAddress("time-A.timefreq.bldrdoc.gov", 13), 10_000);
      System.out.println(s.toString());
      try {
        InputStream inStream = s.getInputStream();
        Scanner in = new Scanner(inStream);
        while (in.hasNextLine()) {
          String line = in.nextLine();
          System.out.println(line);
        }
      } catch (InterruptedIOException e) {
        //отреагировать на истечение времени ожидания
        System.out.println(e.getMessage());
      }
    }
  }

  public void methodInetAddress() {
    try {
      InetAddress address = InetAddress.getByName("time-A.timefreq.bldrdoc.gov");
      System.out.println("адрес хоста учета времени дня = " + address);
      System.out.println("только адрес = " + address.getHostAddress());
      System.out.println("только имя = " + address.getHostName());
      System.out.println("------------------");
      //byte[] addressBytes = address.getAddress();
      /*
      System.out.println(addressBytes[0] + "." + addressBytes[1] + "."
              + addressBytes[2] + "." + addressBytes[3]);
      */
      InetAddress address2 = InetAddress.getLocalHost();
      System.out.println("адрес своего локального хоста = " + address2);
      System.out.println("-------------------");
      //InetAddress[] address3 = InetAddress.getAllByName("google.com");
      InetAddress[] address3 = InetAddress.getAllByName("goodfon.ru");
      System.out.println("список сетевых адресов одного хоста с большим " +
              "объемом трафика: ");
      for (InetAddress a : address3) {
        System.out.println(a);
      }
      System.out.println("-------------------");
      InetAddress address4 = InetAddress.getByName("localhost");
      System.out.println("локальный петлевой адрес = " + address4);
    } catch (UnknownHostException e) {
      System.out.println("unknown host exception = " + e.getMessage());
    }
  }
}
