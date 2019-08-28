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
package org.apache.ibatis.mapping;

import javax.sql.DataSource;

import org.apache.ibatis.transaction.TransactionFactory;

import com.zeasn.common.ext1.datasync.ISyncSender;
import com.zeasn.common.ext1.datasync.SyncTemplate;

/**
 * @author Clinton Begin
 */
public final class Environment {
  private final String id;
  private final TransactionFactory transactionFactory;
  private final DataSource dataSource;
  
  private final SyncTemplate template;
  private final ISyncSender sender;
  
  public Environment(String id, TransactionFactory transactionFactory, DataSource dataSource) {
	  this(id, transactionFactory, dataSource, null, null);
  }

  public Environment(String id, TransactionFactory transactionFactory, DataSource dataSource, SyncTemplate template, ISyncSender sender) {
    if (id == null) {
      throw new IllegalArgumentException("Parameter 'id' must not be null");
    }
    if (transactionFactory == null) {
        throw new IllegalArgumentException("Parameter 'transactionFactory' must not be null");
    }
    this.id = id;
    if (dataSource == null) {
      throw new IllegalArgumentException("Parameter 'dataSource' must not be null");
    }
    this.transactionFactory = transactionFactory;
    this.dataSource = dataSource;
    
    this.template = template;
    this.sender = sender;
  }

  public static class Builder {
      private String id;
      private TransactionFactory transactionFactory;
      private DataSource dataSource;
      
      private SyncTemplate template;
      private ISyncSender sender;

    public Builder(String id) {
      this.id = id;
    }

    public Builder transactionFactory(TransactionFactory transactionFactory) {
      this.transactionFactory = transactionFactory;
      return this;
    }

    public Builder dataSource(DataSource dataSource) {
      this.dataSource = dataSource;
      return this;
    }

    public String id() {
      return this.id;
    }
    
    public Builder syncTemplate(SyncTemplate template){
    	this.template = template;
    	return this;
    }
    
    public Builder syncSender(ISyncSender sender){
    	this.sender = sender;
    	return this;
    }
    

    public Environment build() {
      return new Environment(this.id, this.transactionFactory, this.dataSource, this.template, this.sender);
    }

  }

  public String getId() {
    return this.id;
  }

  public TransactionFactory getTransactionFactory() {
    return this.transactionFactory;
  }

  public DataSource getDataSource() {
    return this.dataSource;
  }

	public SyncTemplate getTemplate() {
		return template;
	}
	
	public ISyncSender getSender() {
		return sender;
	}
}
