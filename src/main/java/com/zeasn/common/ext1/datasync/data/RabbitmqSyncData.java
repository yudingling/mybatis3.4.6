package com.zeasn.common.ext1.datasync.data;

import java.io.Serializable;

public class RabbitmqSyncData extends SyncData {
	private static final long serialVersionUID = -7544255918801014699L;
	
	private String exchange;
	private String routingKey;
	private Serializable object;
	
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
	public Serializable getObject() {
		return object;
	}
	public void setObject(Serializable object) {
		this.object = object;
	}
	
	public RabbitmqSyncData(){
		super();
	}
	
	public RabbitmqSyncData(String appName, String groupName, long deferMilliseconds, String exchange, String routingKey, Serializable object){
		super(appName, groupName, deferMilliseconds);
		
		this.exchange = exchange;
		this.routingKey = routingKey;
		this.object = object;
	}
}
