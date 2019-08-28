/**
 *    Copyright ${license.git.copyrightYears} the original author or authors.
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
package org.apache.ibatis.binding;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.Map;

import org.apache.ibatis.lang.UsesJava7;
import org.apache.ibatis.reflection.ExceptionUtil;
import org.apache.ibatis.session.SqlSession;

import com.zeasn.common.ext1.datasync.SyncTemplate;
import com.zeasn.common.ext1.datasync.mybatis.DbSyncParam;
import com.zeasn.common.ext1.datasync.mybatis.DbSyncWrapperProxy;
import com.zeasn.common.ext1.datasync.mybatis.IDbSyncWrapper;

/**
 * @author Clinton Begin
 * @author Eduardo Macarron
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

  private static final long serialVersionUID = -6424540398559729838L;
  private final SqlSession sqlSession;
  private final Class<T> mapperInterface;
  private final Map<Method, MapperMethod> methodCache;

  public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface, Map<Method, MapperMethod> methodCache) {
    this.sqlSession = sqlSession;
    this.mapperInterface = mapperInterface;
    this.methodCache = methodCache;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    try {
    	Class<?> cls = method.getDeclaringClass();
    	
    	
      if (Object.class.equals(cls)) {
        return method.invoke(this, args);
        
      }else if(IDbSyncWrapper.class.equals(cls)){
    	  return this.createSyncProxy(proxy, args);
    	  
      } else if (isDefaultMethod(method)) {
        return invokeDefaultMethod(proxy, method, args);
      }
    } catch (Throwable t) {
      throw ExceptionUtil.unwrapThrowable(t);
    }
    final MapperMethod mapperMethod = cachedMapperMethod(method);
    
    Object[] data = this.getArgsAndSyncParam(args);
    
    return mapperMethod.execute(sqlSession, (Object[]) data[0], (DbSyncParam) data[1]);
  }
  
  private Object[] getArgsAndSyncParam(Object[] args){
	  if(args != null && args.length > 0){
		  
		  Object last = args[args.length - 1];
		  
		  if(last instanceof DbSyncParam){
			  Object[] newArgs = new Object[args.length - 1];
			  System.arraycopy(args, 0, newArgs, 0, newArgs.length);
			  
			  return new Object[]{ newArgs, last};
		  }
	  }
	  
	  return new Object[]{args, null};
  }
  
  private Object createSyncProxy(Object proxy, Object[] args){
	  String groupName = args != null && args.length > 0 ? ((String) args[0]) : SyncTemplate.DEFAULT_GROUP;
	  
	  Class<?> sourceCls = proxy.getClass().getInterfaces()[0];
	  
	  return Proxy.newProxyInstance(sourceCls.getClassLoader(), new Class[]{ sourceCls }, new DbSyncWrapperProxy(proxy, groupName));
  }

  private MapperMethod cachedMapperMethod(Method method) {
    MapperMethod mapperMethod = methodCache.get(method);
    if (mapperMethod == null) {
      mapperMethod = new MapperMethod(mapperInterface, method, sqlSession.getConfiguration());
      methodCache.put(method, mapperMethod);
    }
    return mapperMethod;
  }

  @UsesJava7
  private Object invokeDefaultMethod(Object proxy, Method method, Object[] args)
      throws Throwable {
    final Constructor<MethodHandles.Lookup> constructor = MethodHandles.Lookup.class
        .getDeclaredConstructor(Class.class, int.class);
    if (!constructor.isAccessible()) {
      constructor.setAccessible(true);
    }
    final Class<?> declaringClass = method.getDeclaringClass();
    return constructor
        .newInstance(declaringClass,
            MethodHandles.Lookup.PRIVATE | MethodHandles.Lookup.PROTECTED
                | MethodHandles.Lookup.PACKAGE | MethodHandles.Lookup.PUBLIC)
        .unreflectSpecial(method, declaringClass).bindTo(proxy).invokeWithArguments(args);
  }

  /**
   * Backport of java.lang.reflect.Method#isDefault()
   */
  private boolean isDefaultMethod(Method method) {
    return (method.getModifiers()
        & (Modifier.ABSTRACT | Modifier.PUBLIC | Modifier.STATIC)) == Modifier.PUBLIC
        && method.getDeclaringClass().isInterface();
  }
}
