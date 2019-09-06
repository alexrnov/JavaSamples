package rmi.client;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Enumeration;

/**
 * Клиент, вызывающий удаленный метод
 */
public class WarehouseClient {
  public static void main(String[] args) throws NamingException, RemoteException {
    Context namingContext = new InitialContext();
    System.out.println("RMI registry bindings: ");
    Enumeration<NameClassPair> e = namingContext.list("rmi://localhost/");
    while(e.hasMoreElements()) {
      System.out.println(e.nextElement().getName());
    }
    String url = "rmi://localhost/central_warehouse";
    Warehouse centralWarehouse = (Warehouse) namingContext.lookup(url);
    double price = centralWarehouse.getPrice("water");
    System.out.println(price);
  }
}
