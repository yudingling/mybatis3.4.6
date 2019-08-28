package com.zeasn.common.ext1.datasync.mybatis;

public class DbSyncParam {
	private String groupName;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public DbSyncParam(String groupName) {
		super();
		this.groupName = groupName;
	}
}
