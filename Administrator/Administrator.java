package admin;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Administrator implements Stub
{
    public Administrator(){};
    public String sayhello()
    {
        return "Hello bro";
    }

    public List<Integer> getPorts(String filename)
    {
        List<Integer> l1 = new ArrayList<Integer>();
        if(filename.equals("n1.txt"))
        {
            l1.add(3000);
            l1.add(3001);
            // l1.add(3002);
            // l1.add(3003);
            l1.add(5000);
        }
        else if(filename.equals("n2.txt"))
        {
            l1.add(3000);
            // l1.add(3001);
            l1.add(3002);
            // l1.add(3003);
            l1.add(5000);
        }
        else if(filename.equals("n3.txt"))
        {
            l1.add(3000);
            // l1.add(3001);
            // l1.add(3002);
            l1.add(3003);
            l1.add(5000);
        }
        return l1;
    }

    public static void main(String[] args) {
        try {
            Administrator obj = new Administrator();
            Stub stub = (Stub) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Stub", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}