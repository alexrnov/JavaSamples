package rmi.server;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;

/**
 * Эта серверная программа служит для получения экземпляра удаленного
 * объекта товарного склада и его регистрации с помощью службы
 * наименования, ожидая от клиентов вызова удаленных методов
 *
 */
public class WarehouseServer {
  public static void main(String[] args) {
    try {
      System.out.println("Constructing server implementation...");
      WarehouseImpl centralWarehouse = new WarehouseImpl();
      System.out.println("Binding server implementation to registry");
      Context namingContext = new InitialContext();
      namingContext.bind("rmi:central_warehouse", centralWarehouse);
      System.out.println("Waiting for invocations from clients...");
    } catch(RemoteException | NamingException e) {
      e.printStackTrace();
    }
  }
}
