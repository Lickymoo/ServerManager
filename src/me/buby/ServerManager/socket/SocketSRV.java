package me.buby.ServerManager.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import me.buby.ServerManager.InstanceType;
import me.buby.ServerManager.ServerManager;
import me.buby.ServerManager.ServerManagerInstance;

public class SocketSRV {
	
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;	
	
	public void start(int port) {
	        try {
	 	       	serverSocket = new ServerSocket(port, 100);
				clientSocket = serverSocket.accept();
				out = new PrintWriter(clientSocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				String msg = in.readLine();
				String delim = null;
				
				//From client plugins
				{//Request new server instance
					//REQ_NEWSRV#TYPE
					//RETURNS: IP:PORT
					delim = "REQ_NEWSRV#";
	            	if(msg.contains(delim))
	            	{
	            		String output = msg.replace(delim, "");
	            		InstanceType type = InstanceType.getType(output);
	            		ServerManagerInstance inst = ServerManager.getManagerInstanceByWeight(type);
	            		out.println(new ServerRequest(inst.ip, Integer.parseInt(inst.ip), SocketMessage.NEW_MCSRV_DONE.append("")).response);
	            	}
				}
				
				//From Server Managers
				{//Register Server Manager Instance
					//REG_SRVMNGR_INST#IP:PORT:TYPE
					delim = "REG_SRVMNGR_INST#";
	            	if(msg.contains(delim))
	            	{
	            		String output = msg.replace(delim, "");
	            		ServerManager.addServerManagerInstance(output);
	            		System.out.println("Registered Server Instance " + output);
	          
	            	}
				}
				{//Remove Server Manager Instance
					//REM_SRVMNGR_INST#IP:PORT
					delim = "REM_SRVMNGR_INST#";
					if(msg.contains(delim))
					{
						String output = msg.replace(delim, "");
						String[] data = output.split(":");
						ServerManager.removeServerManagerInstance(data[0], Integer.parseInt(data[1]));
						System.out.println("Server instance " + output + " Stopping");
					}
				}
	                      
        		stop(port); //RECURSIVE;
	            
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void stop(int port) {
		try {
			in.close();
			out.close();
			clientSocket.close();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		start(port);
	}
	
}