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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DbSyncWrapperProxy implements InvocationHandler {
	private Object mapperProxy;
	private String groupName;
	private long deferMilliseconds = 0;
	
	public DbSyncWrapperProxy(Object mapperProxy, String groupName, long deferMilliseconds){
		this.mapperProxy = mapperProxy;
		this.groupName = groupName;
		this.deferMilliseconds = deferMilliseconds;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if(!IDbSyncWrapper.class.equals(method.getDeclaringClass())){
			//set sysParam to the last index of args
			DbSyncParam param = new DbSyncParam(this.groupName, this.deferMilliseconds);
			Object[] newArgs;
			
			if(args != null && args.length > 0){
				newArgs = new Object[args.length + 1];
				
				System.arraycopy(args, 0, newArgs, 0, args.length);
				newArgs[args.length] = param;
				
			}else{
				newArgs = new Object[]{param};
			}
			
			return method.invoke(this.mapperProxy, newArgs);
		}
		
		throw new Exception("method not supported");
	}
}
