package me.buby.ServerManager;

public enum InstanceType {
	PLAY("PLAY"),
	HUB("HUB"),
	MICRO("MICRO");
	
	public String name;
	
	private InstanceType(String name) {
		this.name = name;
	}
	
	public static InstanceType getType(String name) {
		for(InstanceType type : InstanceType.values()) {
			if(type.name.equalsIgnoreCase(name))
				return type;
		}
		return null;
	}
}
