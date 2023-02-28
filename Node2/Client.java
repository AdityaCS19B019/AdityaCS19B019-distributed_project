// // A Java program for a Client
package admin;

import java.io.*;
import java.net.*;
import java.lang.Thread;
import java.io.BufferedOutputStream; 
import java.io.FileOutputStream; 
import java.io.InputStream; 
import java.net.InetAddress; 
import java.net.Socket; 

public class Client extends Thread{
	// initialize socket and input output streams
	private Socket socket = null;
	private DataInputStream input = null;
	private DataOutputStream out = null;
    private String ipaddress = "127.0.0.1";
    private int portno = 3000;
	private String filename = "";
	// constructor to put ip address and port
	void startclient (int port) throws Exception
	{
		// establish a connection
		// System.out.println("Hello");
		// catch (UnknownHostException u) {
		// 	u.printStackTrace();
		// 	System.out.println(u);
		// 	return;
		// }
		// catch (IOException i) {
		// 	System.out.println(i);
		// 	return;
		// }
		Boolean b = false;
		
		socket = new Socket(ipaddress, port);
		System.out.println("Connected");

		// takes input from terminal
		// input = new DataInputStream(System.in);

		// sends output to the socket
		out = new DataOutputStream(
		socket.getOutputStream());
		
		// System.out.println(b);
		// if(b) return;
		try 
		{
			// line = input.readLine();
			out.writeUTF(filename);
		}
		catch (IOException i) {
			System.out.println(i);
		}
		byte[] contents = new byte[10000];         
		
		//Initialize the FileOutputStream to the output file's full path.        
		FileOutputStream fos = new FileOutputStream("/mnt/c/Users/imman/Desktop/My prep2.0/distributed/Node2/" + filename);           
		BufferedOutputStream bos = new BufferedOutputStream(fos);          
		InputStream is = socket.getInputStream();          
		
		//No of bytes read in one read() call          
		int bytesRead = 0;                  
		while((bytesRead = is.read(contents)) != -1)               
		bos.write(contents, 0, bytesRead);                  
		bos.flush();         	         
		socket.close();                  
		System.out.println("File saved successfully!");     
	
			// string to read message from input
			// String line = "";
	
			// keep reading until "Over" is input
	
			// close the connection
		try {
			input.close();
			out.close();
			socket.close();
		}
		catch (IOException i) {
			System.out.println(i);
		}
		
		// return;
	}

	public void set_file_name(String name)
	{
		this.filename = name;
	}

	public void set_port_no(int portno)
	{
		this.portno = portno;
	}

	public void run()
	{
		try{
			startclient(this.portno);
		}catch(Exception e)
		{
			System.out.println(e);
		}
		return;
	}
}
