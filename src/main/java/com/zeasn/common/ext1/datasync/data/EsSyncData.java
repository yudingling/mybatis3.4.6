package com.zeasn.common.ext1.datasync.data;

import java.io.Serializable;

public class EsSyncData extends SyncData {
	private static final long serialVersionUID = 1815560778676010106L;
	
	private ActionType action;
	private Serializable model;
	private boolean upsert = false;
	
	public ActionType getAction() {
		return action;
	}

	public void setAction(ActionType action) {
		this.action = action;
	}
	public Serializable getModel() {
		return model;
	}
	public void setModel(Serializable model) {
		this.model = model;
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
	
	public EsSyncData(String appName, String groupName, long deferMilliseconds, ActionType action, Serializable model, boolean upsert){
		super(appName, groupName, deferMilliseconds);
		
		this.action = action;
		this.model = model;
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
		UPDATE,
		/**
		 * support by sync-service
		 */
		UPDATE_SELECTIVE
	}
}
