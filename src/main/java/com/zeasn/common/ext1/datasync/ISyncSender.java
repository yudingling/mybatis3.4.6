/**
 *    Copyright 2009-2019 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.zeasn.common.ext1.datasync;

public interface ISyncSender {
	/**
	 * sync redis
	 * @param appName
	 * @param groupName if groupName is null, it means synchronize to all groups
	 * @param data
	 */
	void sendRedis(String appName, String groupName, Object data, long deferMilliseconds);
	/**
	 * sync elasticSearch
	 * @param appName
	 * @param groupName if groupName is null, it means synchronize to all groups
	 * @param data
	 */
	void sendEs(String appName, String groupName, Object data, long deferMilliseconds);
	/**
	 * sync rabbitMq
	 * @param appName
	 * @param groupName if groupName is null, it means synchronize to all groups
	 * @param data
	 */
	void sendRabbitmq(String appName, String groupName, Object data, long deferMilliseconds);
	/**
	 * sync mysql
	 * @param appName
	 * @param groupName if groupName is null, it means synchronize to all groups
	 * @param data
	 */
	void sendMysql(String appName, String groupName, Object data, long deferMilliseconds);
}
