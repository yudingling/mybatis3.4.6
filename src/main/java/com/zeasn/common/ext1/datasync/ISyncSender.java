package com.zeasn.common.ext1.datasync;

public interface ISyncSender {
	/**
	 * sync redis
	 * @param appName
	 * @param groupName if groupName is null, it means synchronize to all groups
	 * @param data
	 */
	void sendRedis(String appName, String groupName, Object data);
	/**
	 * sync elasticSearch
	 * @param appName
	 * @param groupName if groupName is null, it means synchronize to all groups
	 * @param data
	 */
	void sendEs(String appName, String groupName, Object data);
	/**
	 * sync rabbitMq
	 * @param appName
	 * @param groupName if groupName is null, it means synchronize to all groups
	 * @param data
	 */
	void sendRabbitmq(String appName, String groupName, Object data);
	/**
	 * sync mysql
	 * @param appName
	 * @param groupName if groupName is null, it means synchronize to all groups
	 * @param data
	 */
	void sendMysql(String appName, String groupName, Object data);
}
