package admin;
import java.io.*;
import java.net.*;

public class Node
{
    public static void main(String args[]) {
        // Client c = new Client();
        Server s = new Server();
        Gui g  = new Gui();
        g.start();
        s.start();
    }
}