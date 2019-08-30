package com.zeasn.common.ext1.datasync.data;

public class MysqlSyncData extends SyncData {
	private static final long serialVersionUID = 9131113876483986336L;
	
	private String sql;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	
	public MysqlSyncData(){
		super();
	}

	public MysqlSyncData(String appName, String groupName, long deferMilliseconds, String sql) {
		super(appName, groupName, deferMilliseconds);
		
		this.sql = sql;
	}
}
