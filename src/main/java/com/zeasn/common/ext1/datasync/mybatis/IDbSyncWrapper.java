package com.zeasn.common.ext1.datasync.mybatis;

public interface IDbSyncWrapper {
	Object createSync();
	Object createSync(String groupName);
}
