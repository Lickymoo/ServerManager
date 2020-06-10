package me.buby.ServerManager.socket;

public enum SocketMessage {

	NEW_MCSRV_DONE("NEW_MCSRV_DONE", PacketMode.OUTGOING, true),
	GET_AMOUNT_SRV("GET_AMOUNT_SRV", PacketMode.OUTGOING, true),
	GET_AMOUNT_RAM("GET_AMOUNT_RAM", PacketMode.OUTGOING, true),
	GET_WEIGHT("GET_WEIGHT", PacketMode.OUTGOING, true),
	GET_SERVERS("GET_SERVERS", PacketMode.OUTGOING, true);
	
	public String prefix;
	public PacketMode mode; 
	public boolean expectReturn;
	
	public String append(String str) {
		return prefix + str;
	}
	
	private SocketMessage(String prefix, PacketMode mode, boolean expectReturn) {
		this.prefix = prefix + "#";
		this.mode = mode;
		this.expectReturn = expectReturn;
	}
	
	private SocketMessage(String prefix, PacketMode mode) {
		this.prefix = prefix + "#";
		this.mode = mode;
	}
}
