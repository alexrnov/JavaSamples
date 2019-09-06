package rmi.download;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Удаленный интерфейс для простого товарного склада
 */
public interface Warehouse extends Remote {
  double getPrice(String description) throws RemoteException;
}
