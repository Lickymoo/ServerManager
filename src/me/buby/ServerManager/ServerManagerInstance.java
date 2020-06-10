package me.buby.ServerManager;

import java.net.InetAddress;

import me.buby.ServerManager.socket.ServerRequest;
import me.buby.ServerManager.socket.SocketMessage;

public class ServerManagerInstance {
	public String ip;
	public int port;
	
	public InstanceType type;
	
	public void init() {
		try {
		if(ip.equals(InetAddress.getLocalHost().getHostAddress().toString()))
			ip = "0.0.0.0";
		}catch(Exception e) {}
	}
	
	public ServerManagerInstance(String ip, int port, InstanceType type) {
		this.ip = ip;
		this.port = port;
		this.type = type;
		init();
	}
	
	public ServerManagerInstance(String ip, String port, String type) {
		this.ip = ip;
		this.port = Integer.parseInt(port);
		this.type = InstanceType.getType(type);
		init();
	}
	
	public int getAmountHosted() {
		return Integer.parseInt(new ServerRequest(this, SocketMessage.GET_AMOUNT_SRV).response);
	}
	
	public int getRAMSize() {
		return Integer.parseInt(new ServerRequest(this, SocketMessage.GET_AMOUNT_RAM).response);
	}
	
	public int getWeight() {
		return Integer.parseInt(new ServerRequest(this, SocketMessage.GET_WEIGHT).response);
	}
	
	public String[] getServerInstances() {
		return new ServerRequest(this, SocketMessage.GET_SERVERS).response.split(":");
	}
	
	public void stopServerInstance(String ID) {
		
	}
	
	@Override
	public String toString() {
		return "ip="+ip+"port="+port+"type="+type;
	}
}
