package admin;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

public class Getfiles extends Thread{

    public Getfiles() {}
    private String filename;
    public void set_file_name(String file_name)
    {
        this.filename = file_name;
    }

    int start_clients(int portno , Client c)
    {
        try
        {
            c.startclient(portno);
            return 1;
        }catch(Exception e){
            // e.printStackTrace();
            // System.out.println(e);
            return 0;
        }
    }

    public void get_file(String filename) {

        // String host = (args.length < 1) ? null : args[0];
        String host = null;
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            Stub stub = (Stub) registry.lookup("Stub");
            // String response = stub.sayhello();
            List<Integer> response = stub.getPorts(filename);
            // System.out.println("response: " + response);
            Client c = new Client();
            // String filename = "test.txt";
            c.set_file_name(filename);
            for(int i = 0 ; i < response.size() ; i++)
            {
                // if(start_clients(response.get(i) , c) == 1)
                // {
                //     System.out.print("Trying port " + response.get(i));
                //     break;
                // }
                // else
                // {
                //     System.out.println("Failed");
                //     System.out.println("Trying another port ");
                // }
                try{
                    System.out.println("Trying port " + response.get(i).toString());
                    c.startclient(response.get(i));
                    System.out.println("Success");
                }catch(Exception e){
                    System.out.println("Failed , Trying another port");
                }
                // System.out.println("dsfmds");
            }

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public void run()
    {
        get_file(this.filename);
    }
}