package rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Этот класс реализует удаленный интерфейс товарного склада Warehouse
 */
public class WarehouseImpl extends UnicastRemoteObject implements Warehouse {
  private Map<String, Double> prices;

  public WarehouseImpl() throws RemoteException {
    prices = new HashMap<>();
    prices.put("water", 5.4);
    prices.put("apple", 3.2);
  }

  public double getPrice(String description) throws RemoteException {
    Double price = prices.get(description);
    return price == null ? 0 : price;
  }
}
