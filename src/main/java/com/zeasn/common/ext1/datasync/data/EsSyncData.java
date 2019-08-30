package com.zeasn.common.ext1.datasync.data;

public class EsSyncData extends SyncData {
	private static final long serialVersionUID = 1815560778676010106L;

	public EsSyncData(){
		super();
	}
	
	public EsSyncData(String appName, String groupName, long deferMilliseconds){
		super(appName, groupName, deferMilliseconds);
	}
}
