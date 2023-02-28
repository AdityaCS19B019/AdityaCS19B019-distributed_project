// A Java program for a Server
package admin;
import java.net.*;
import java.io.*;


public class Server extends Thread
{
	//initialize socket and input stream
	private Socket		 socket = null;
	private ServerSocket server = null;
	private DataInputStream in	 = null;
	private Boolean stop_server = false;

	public void pause_server()
	{
		stop_server = true;
	}

	public void resume_server()
	{
		stop_server = false;
	}

	// constructor with port
	void start_Server(int port)
	{
		// starts server and waits for a connection
		try
		{
			server = new ServerSocket(port);
			while(true)
			{
				System.out.println("Server started");

				System.out.println("Waiting for a new client ...");
				// if(stop_server)
				// {
				// 	break;
				// }
				socket = server.accept();
				while(stop_server)
				{
					try{
						Thread.sleep(1000);
					}catch(Exception e)
					{
						System.out.println(e);
					}
					// return;
				}
				// if(stop_server)
				// 	return;
				System.out.println("New cilent connected");

				// takes input from the client socket
				in = new DataInputStream(
					new BufferedInputStream(socket.getInputStream()));

				String line = "";

				// reads message from client until "Over" is sent
				try
				{
					line = in.readUTF();
					System.out.println("File requested by client : " + line);

				}
				catch(IOException i)
				{
					System.out.println(i);
					break;
				}
				String file_path = "/mnt/c/Users/imman/Desktop/My prep2.0/distributed/Node1/" + line;
				File file = new File(file_path);         
				FileInputStream fis = new FileInputStream(file);           
				BufferedInputStream bis = new BufferedInputStream(fis);                     
				
				//Get socket's output stream           
				OutputStream os = socket.getOutputStream();                  
				
				//Read File Contents into contents array            
				byte[] contents;            
				long fileLength = file.length();                        
				long current = 0;                       
				long start = System.nanoTime();                       
				while(current!=fileLength){              
					int size = 10;             
					if(fileLength - current >= size)                 
						current += size;                 
					else{                  
						size = (int)(fileLength - current);                  
						current = fileLength;             
					}              
					contents = new byte[size];              
					bis.read(contents, 0, size);               
					os.write(contents);             
					System.out.print("Sending file ... "+(current*100)/fileLength+"% complete!\n");           
				}                    
				os.flush();               
				socket.close();               
				System.out.println("File sent succesfully!");     
			}
			System.out.println("Closing connection");

			// close connection
			server.close();
			socket.close();
			in.close();
		}
		catch(IOException i)
		{
			
			System.out.println(i);
		}
	}

	public void run()
	{
		start_Server(3001);
	}
}

