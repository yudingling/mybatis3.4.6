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
package com.zeasn.common.ext1.datasync.mybatis;

public class DbSyncParam {
	private String groupName;
	private long deferMilliseconds = 0;

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

	public DbSyncParam(String groupName, long deferMilliseconds) {
		super();
		this.groupName = groupName;
		this.deferMilliseconds = deferMilliseconds;
	}
}
