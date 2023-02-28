package admin;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

public interface Stub extends Remote
{
   String sayhello() throws RemoteException;
   List<Integer> getPorts(String filename) throws RemoteException;
}