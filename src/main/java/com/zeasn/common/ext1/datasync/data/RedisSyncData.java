package com.zeasn.common.ext1.datasync.data;

import java.io.Serializable;

public class RedisSyncData extends SyncData {
	private static final long serialVersionUID = 5231204501480420843L;
	
	private ActionType action;
	private String key;
	private String field;
	private Serializable value;
	private Long expireSeconds;
	private Long delta;
	
	public ActionType getAction() {
		return action;
	}

	public void setAction(ActionType action) {
		this.action = action;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Serializable getValue() {
		return value;
	}

	public void setValue(Serializable value) {
		this.value = value;
	}

	public Long getExpireSeconds() {
		return expireSeconds;
	}

	public void setExpireSeconds(Long expireSeconds) {
		this.expireSeconds = expireSeconds;
	}

	public Long getDelta() {
		return delta;
	}

	public void setDelta(Long delta) {
		this.delta = delta;
	}

	public RedisSyncData(){
		super();
	}
	
	public RedisSyncData(String appName, String groupName, long deferMilliseconds, ActionType action, String key, Serializable value) {
		super(appName, groupName, deferMilliseconds);
		
		this.action = action;
		this.key = key;
		this.value = value;
	}
	
	public RedisSyncData(String appName, String groupName, long deferMilliseconds, ActionType action, String key, Serializable value, long expireSeconds) {
		super(appName, groupName, deferMilliseconds);
		
		this.action = action;
		this.key = key;
		this.value = value;
		this.expireSeconds = expireSeconds;
	}
	
	public RedisSyncData(String appName, String groupName, long deferMilliseconds, ActionType action, String key, String field, Serializable value) {
		super(appName, groupName, deferMilliseconds);
		
		this.action = action;
		this.key = key;
		this.field = field;
		this.value = value;
	}
	
	public RedisSyncData(String appName, String groupName, long deferMilliseconds, ActionType action, String key) {
		super(appName, groupName, deferMilliseconds);
		
		this.action = action;
		this.key = key;
	}
	
	public RedisSyncData(String appName, String groupName, long deferMilliseconds, ActionType action, String key, String field) {
		super(appName, groupName, deferMilliseconds);
		
		this.action = action;
		this.key = key;
		this.field = field;
	}
	
	public RedisSyncData(String appName, String groupName, long deferMilliseconds, ActionType action, String key, long delta) {
		super(appName, groupName, deferMilliseconds);
		
		this.action = action;
		this.key = key;
		this.delta = delta;
	}
	
	public RedisSyncData(String appName, String groupName, long deferMilliseconds, ActionType action, String key, String field, long delta) {
		super(appName, groupName, deferMilliseconds);
		
		this.action = action;
		this.key = key;
		this.field = field;
		this.delta = delta;
	}
	
	public enum ActionType{
		SET_KEY_VALUE,
		SETNX_KEY_VALUE,
		SETEX_KEY_VALUE,
		
		SET_KEY_VALUE_EXPR,
		SETNX_KEY_VALUE_EXPR,
		SETEX_KEY_VALUE_EXPR,
		
		SET_KEY_FIELD_VALUE,
		SETNX_KEY_FIELD_VALUE,
		
		DELETE_KEY,
		DELETE_KEY_FIELD,
		
		INCBY_KEY,
		INCBY_KEY_FIELD
	}
}
