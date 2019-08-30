package com.zeasn.common.ext1.datasync.data;

import java.io.Serializable;

public class SyncData implements Serializable {
	private static final long serialVersionUID = -4855657310156936766L;
	
	protected String appName;
	/**
	 * if groupName is null, it means synchronize to all groups
	 */
	protected String groupName;
	
	protected long deferMilliseconds = 0;
	
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public long getDeferMilliseconds() {
		return deferMilliseconds;
	}
	public void setDeferMilliseconds(long deferMilliseconds) {
		this.deferMilliseconds = deferMilliseconds;
	}
	
	public SyncData(){
		super();
	}
	
	public SyncData(String appName, String groupName, long deferMilliseconds) {
		super();
		this.appName = appName;
		this.groupName = groupName;
		this.deferMilliseconds = deferMilliseconds;
	}
}
