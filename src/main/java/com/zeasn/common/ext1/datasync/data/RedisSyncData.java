package com.zeasn.common.ext1.datasync.data;

public class RedisSyncData extends SyncData {
	private static final long serialVersionUID = 5231204501480420843L;
	
	
	
	public RedisSyncData(){
		super();
	}
	
	public RedisSyncData(String appName, String groupName, long deferMilliseconds) {
		super(appName, groupName, deferMilliseconds);
	}
	
}
