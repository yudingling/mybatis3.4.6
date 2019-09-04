package com.zeasn.common.ext1.datasync.data;

public class EsSyncData extends SyncData {
	private static final long serialVersionUID = 1815560778676010106L;
	
	private ActionType action;
	private String serializedValue;
	private boolean upsert = false;
	
	public ActionType getAction() {
		return action;
	}

	public void setAction(ActionType action) {
		this.action = action;
	}

	public String getSerializedValue() {
		return serializedValue;
	}

	public void setSerializedValue(String serializedValue) {
		this.serializedValue = serializedValue;
	}

	public boolean isUpsert() {
		return upsert;
	}

	public void setUpsert(boolean upsert) {
		this.upsert = upsert;
	}

	public EsSyncData(){
		super();
	}
	
	public EsSyncData(String appName, String groupName, long deferMilliseconds, ActionType action, String serializedValue, boolean upsert){
		super(appName, groupName, deferMilliseconds);
		
		this.action = action;
		this.serializedValue = serializedValue;
		this.upsert = upsert;
	}

	public enum ActionType{
		/**
		 * support by sync-service
		 */
		SAVE,
		
		/**
		 * support by local-client
		 */
		DELETE_WITH_QUERY,
		/**
		 * support by local-client
		 */
		DELETE_WITH_FILTER,
		/**
		 * support by sync-service
		 */
		DELETE_WITH_MODEL,
		
		/**
		 * support by local-client
		 */
		UPDATE
	}
}
