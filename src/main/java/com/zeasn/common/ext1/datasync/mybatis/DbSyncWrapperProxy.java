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

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.lang.UsesJava7;
import org.apache.ibatis.reflection.ExceptionUtil;
import org.apache.ibatis.session.SqlSession;

public class DbSyncWrapperProxy implements InvocationHandler {
	private String groupName;
	private long deferMilliseconds = 0;

	private final SqlSession sqlSession;
	private final Class<?> mapperInterface;
	private final Map<Method, MapperMethod> methodCache;

	public DbSyncWrapperProxy(SqlSession sqlSession, Class<?> mapperInterface, Map<Method, MapperMethod> methodCache, 
			String groupName, long deferMilliseconds) {
		this.sqlSession = sqlSession;
		this.mapperInterface = mapperInterface;
		this.methodCache = methodCache;

		this.groupName = groupName;
		this.deferMilliseconds = deferMilliseconds;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (!IDbSyncWrapper.class.equals(method.getDeclaringClass())) {
			// set sysParam to the last index of args
			DbSyncParam param = new DbSyncParam(this.groupName, this.deferMilliseconds);
			
			return this.invokeInner(proxy, method, args, param);
		}
		
		throw new Exception("method not supported");
	}

	private Object invokeInner(Object proxy, Method method, Object[] args, DbSyncParam param)
			throws Throwable {
		try {
			Class<?> cls = method.getDeclaringClass();

			if (Object.class.equals(cls)) {
				return method.invoke(this, args);

			} else if (isDefaultMethod(method)) {
				return invokeDefaultMethod(proxy, method, args);
			}
			
		} catch (Throwable t) {
			throw ExceptionUtil.unwrapThrowable(t);
		}
		
		final MapperMethod mapperMethod = cachedMapperMethod(method);
		
		return mapperMethod.execute(sqlSession, args, param);
	}

	private MapperMethod cachedMapperMethod(Method method) {
		MapperMethod mapperMethod = methodCache.get(method);
		if (mapperMethod == null) {
			mapperMethod = new MapperMethod(mapperInterface, method,
					sqlSession.getConfiguration());
			methodCache.put(method, mapperMethod);
		}
		return mapperMethod;
	}

	@UsesJava7
	private Object invokeDefaultMethod(Object proxy, Method method,
			Object[] args) throws Throwable {
		final Constructor<MethodHandles.Lookup> constructor = MethodHandles.Lookup.class
				.getDeclaredConstructor(Class.class, int.class);
		if (!constructor.isAccessible()) {
			constructor.setAccessible(true);
		}
		final Class<?> declaringClass = method.getDeclaringClass();
		return constructor
				.newInstance(
						declaringClass,
						MethodHandles.Lookup.PRIVATE
								| MethodHandles.Lookup.PROTECTED
								| MethodHandles.Lookup.PACKAGE
								| MethodHandles.Lookup.PUBLIC)
				.unreflectSpecial(method, declaringClass).bindTo(proxy)
				.invokeWithArguments(args);
	}

	/**
	 * Backport of java.lang.reflect.Method#isDefault()
	 */
	private boolean isDefaultMethod(Method method) {
		return (method.getModifiers() & (Modifier.ABSTRACT | Modifier.PUBLIC | Modifier.STATIC)) == Modifier.PUBLIC
				&& method.getDeclaringClass().isInterface();
	}
}
