package com.zeasn.common.ext1.datasync.mybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DbSyncWrapperProxy implements InvocationHandler {
	private Object mapperProxy;
	private String groupName;
	
	public DbSyncWrapperProxy(Object mapperProxy, String groupName){
		this.mapperProxy = mapperProxy;
		this.groupName = groupName;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if(!IDbSyncWrapper.class.equals(method.getDeclaringClass())){
			//set sysParam to the last index of args
			DbSyncParam param = new DbSyncParam(this.groupName);
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
