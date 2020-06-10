package me.buby.ServerManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import me.buby.ServerManager.socket.SocketSRV;

public class ServerManager {
	
	public static List<ServerManagerInstance> instanceList = new ArrayList<>();
	
	public static SocketSRV socketSRV = new SocketSRV();
	
	public static void main(String[] args) {

		System.out.println("Started Main");
		new Thread() {
			@Override
			public void run() {
				socketSRV.start(1333);
			}
		}.start();
	}	
	
	//PORT:IP:TYPE
	public static ServerManagerInstance addServerManagerInstance(String input) {
		String[] data = input.split(":");
		ServerManagerInstance inst = new ServerManagerInstance(data[0], data[1], data[2]);
		instanceList.add(inst);
		return inst;
	}
	
	public static void removeServerManagerInstance(String ip, int port) {
		instanceList = instanceList.stream().filter(e -> !e.ip.equals(ip) && !(e.port != port)).collect(Collectors.toList());
	}
	
	public static List<ServerManagerInstance> getListByType(InstanceType type){
		return instanceList.stream().filter(e -> e.type.equals(type)).collect(Collectors.toList());
	}

	public static ServerManagerInstance getManagerInstanceByWeight(InstanceType type) {
		return getManagerInstanceByWeight(getListByType(type));
	}
	
	public static ServerManagerInstance getManagerInstanceByWeight(List<ServerManagerInstance> instList) {
		return instList.stream().max(Comparator.comparing(ServerManagerInstance::getWeight)).orElseThrow(NoSuchElementException::new);
	}
}
