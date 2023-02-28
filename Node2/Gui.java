package admin;

import java.util.Scanner;

public class Gui extends Thread
{
    public void run() {
        Scanner sc = new Scanner(System.in);
        Boolean is_destroyed = false;
        Server s = new Server();
        s.start();
        while(true)
        {
            System.out.println("Press 1 for file Transfer");
            System.out.println("Press 2 for exit");
            if(is_destroyed)
                System.out.println("Press 3 for restart server");
            else
                System.out.println("Press 3 for blocking server");
            while(!sc.hasNextLine()){};
            String res = sc.nextLine();
            // System.out.println(res);
            // Client c = new Client();
            Getfiles g = new Getfiles();
            if(res.equals("1"))
            {
                System.out.println("Enter the filename you want to recieve");
                String name = sc.nextLine();
                // c.set_file_name(name);
                // c.start();
                g.set_file_name(name);
                g.start();
            }
            else if(res.equals("3") && is_destroyed)
            {
                s.resume_server();
                is_destroyed = false;
                System.out.println("Server resumed");
            }
            else if(res.equals("3") && !is_destroyed)
            {
                s.pause_server();
                is_destroyed = true;
                System.out.println("Server paused , No request will be processed");
            }
            // System.out.println("redfadsf");
        }
    }
}