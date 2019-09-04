package com.zeasn.common.ext1.datasync.data;

public class RedisSyncData extends SyncData {
	private static final long serialVersionUID = 5231204501480420843L;
	
	private ActionType action;
	private String key;
	private String field;
	private String serializedValue;
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

	public String getSerializedValue() {
		return serializedValue;
	}

	public void setSerializedValue(String serializedValue) {
		this.serializedValue = serializedValue;
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
	
	public RedisSyncData(String appName, String groupName, long deferMilliseconds, ActionType action) {
		super(appName, groupName, deferMilliseconds);
		
		this.action = action;
	}

	public static RedisSyncData fromKeyValue(String appName, String groupName, long deferMilliseconds, ActionType action, String key, String serializedValue){
		RedisSyncData obj = new RedisSyncData(appName, groupName, deferMilliseconds, action);
		
		obj.key = key;
		obj.serializedValue = serializedValue;
		
		return obj;
	}
	
	public static RedisSyncData fromKeyValueExpr(String appName, String groupName, long deferMilliseconds, ActionType action, String key, String serializedValue, long expireSeconds) {
		RedisSyncData obj = new RedisSyncData(appName, groupName, deferMilliseconds, action);
		
		obj.key = key;
		obj.serializedValue = serializedValue;
		obj.expireSeconds = expireSeconds;
		
		return obj;
	}
	
	public static RedisSyncData fromKeyFieldValue(String appName, String groupName, long deferMilliseconds, ActionType action, String key, String field, String serializedValue) {
		RedisSyncData obj = new RedisSyncData(appName, groupName, deferMilliseconds, action);
		
		obj.key = key;
		obj.field = field;
		obj.serializedValue = serializedValue;
		
		return obj;
	}
	
	public static RedisSyncData fromKey(String appName, String groupName, long deferMilliseconds, ActionType action, String key) {
		RedisSyncData obj = new RedisSyncData(appName, groupName, deferMilliseconds, action);
		
		obj.key = key;
		
		return obj;
	}
	
	public static RedisSyncData fromKeyField(String appName, String groupName, long deferMilliseconds, ActionType action, String key, String field) {
		RedisSyncData obj = new RedisSyncData(appName, groupName, deferMilliseconds, action);
		
		obj.key = key;
		obj.field = field;
		
		return obj;
	}
	
	public static RedisSyncData fromKeyDelta(String appName, String groupName, long deferMilliseconds, ActionType action, String key, long delta) {
		RedisSyncData obj = new RedisSyncData(appName, groupName, deferMilliseconds, action);
		
		obj.key = key;
		obj.delta = delta;
		
		return obj;
	}
	
	public static RedisSyncData fromKeyFieldDelta(String appName, String groupName, long deferMilliseconds, ActionType action, String key, String field, long delta) {
		RedisSyncData obj = new RedisSyncData(appName, groupName, deferMilliseconds, action);
		
		obj.key = key;
		obj.field = field;
		obj.delta = delta;
		
		return obj;
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
