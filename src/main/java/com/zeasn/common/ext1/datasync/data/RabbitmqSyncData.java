package com.zeasn.common.ext1.datasync.data;

public class RabbitmqSyncData extends SyncData {
	private static final long serialVersionUID = -7544255918801014699L;
	
	private String exchange;
	private String routingKey;
	private String serializedValue;
	private String serializedClassName;
	
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public String getRoutingKey() {
		return routingKey;
	}
	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}
	public String getSerializedValue() {
		return serializedValue;
	}
	public void setSerializedValue(String serializedValue) {
		this.serializedValue = serializedValue;
	}
	public String getSerializedClassName() {
		return serializedClassName;
	}
	public void setSerializedClassName(String serializedClassName) {
		this.serializedClassName = serializedClassName;
	}
	
	public RabbitmqSyncData(){
		super();
	}
	
	public RabbitmqSyncData(String appName, String groupName, long deferMilliseconds, String exchange, String routingKey, String serializedValue, String serializedClassName){
		super(appName, groupName, deferMilliseconds);
		
		this.exchange = exchange;
		this.routingKey = routingKey;
		this.serializedValue = serializedValue;
		this.serializedClassName = serializedClassName;
	}
}
