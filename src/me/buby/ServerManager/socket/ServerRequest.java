package me.buby.ServerManager.socket;

import me.buby.ServerManager.ServerManager;
import me.buby.ServerManager.ServerManagerInstance;
import me.buby.ServerManager.socket.SocketInterface;

public class ServerRequest {

	public String response;
	
	public ServerRequest(ServerManagerInstance inst, SocketMessage msg) {
		this(inst.ip, inst.port, msg.prefix);
	}
	
	public ServerRequest(String ip, int port, String msg) {
		try {
			SocketInterface client = new SocketInterface();
			client.startConnection(ip, port);
			String send = ""; 
			send = client.sendMessage(msg);
			client.stopConnection();
			this.response = send;
		}catch(Exception e) {
			ServerManager.removeServerManagerInstance(ip, port);
		}
	}
}
