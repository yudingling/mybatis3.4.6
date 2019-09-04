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

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public class SyncTemplate implements Serializable {
	private static final long serialVersionUID = -1493959936112801291L;

	public static final String DEFAULT_GROUP = "default";
	
	/**
	 * app name defined in pom.xml (without app version)
	 */
	private String appName;
	private Setting redis;
	private EsSetting elasticsearch;
	private Setting rabbitmq;
	private Setting mysql;
	
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Setting getRedis() {
		return redis;
	}

	public void setRedis(Setting redis) {
		this.redis = redis;
	}

	public EsSetting getElasticsearch() {
		return elasticsearch;
	}

	public void setElasticsearch(EsSetting elasticsearch) {
		this.elasticsearch = elasticsearch;
	}

	public Setting getRabbitmq() {
		return rabbitmq;
	}

	public void setRabbitmq(Setting rabbitmq) {
		this.rabbitmq = rabbitmq;
	}

	public Setting getMysql() {
		return mysql;
	}

	public void setMysql(Setting mysql) {
		this.mysql = mysql;
	}
	
	public SyncTemplate(String appName){
		this.appName = appName;
		
		this.redis = new Setting();
		this.elasticsearch = new EsSetting();
		this.rabbitmq = new Setting();
		this.mysql = new Setting();
	}
	
	public void updateGroups(SyncTemplate newTemplate){
		if(newTemplate == null){
			return;
		}
		
		if(newTemplate.getElasticsearch() != null && newTemplate.getElasticsearch().getGroups() != null){
			this.elasticsearch.setGroups(newTemplate.getElasticsearch().getGroups());
			
		}else{
			this.elasticsearch.setGroups(new HashSet<>());
		}
		
		if(newTemplate.getMysql() != null && newTemplate.getMysql().getGroups() != null){
			this.mysql.setGroups(newTemplate.getMysql().getGroups());
			
		}else{
			this.mysql.setGroups(new HashSet<>());
		}
		
		if(newTemplate.getRabbitmq() != null && newTemplate.getRabbitmq().getGroups() != null){
			this.rabbitmq.setGroups(newTemplate.getRabbitmq().getGroups());
			
		}else{
			this.rabbitmq.setGroups(new HashSet<>());
		}
		
		if(newTemplate.getRedis() != null && newTemplate.getRedis().getGroups() != null){
			this.redis.setGroups(newTemplate.getRedis().getGroups());
			
		}else{
			this.redis.setGroups(new HashSet<>());
		}
	}

	public static class Setting implements Serializable{
		private static final long serialVersionUID = -4559631895431895276L;
		
		//these fields may be modified during synchronization of configuration. it must be a volatile field.
		private volatile boolean fullSync = false;
		private volatile Set<String> groups;
		
		public boolean isFullSync() {
			return fullSync;
		}
		public void setFullSync(boolean fullSync) {
			this.fullSync = fullSync;
		}
		public Set<String> getGroups() {
			return groups;
		}
		public void setGroups(Set<String> groups) {
			this.groups = groups;
		}
		
		/**
		 * determine if synchronization is needed. return false if the group is not defined in sync-service. 
		 */
		public boolean canSync(String groupName){
			return CollectionUtils.isNotEmpty(this.groups) && (fullSync || (StringUtils.isNotEmpty(groupName) && this.groups.contains(groupName)));
		}
	}
	
	/**
	 * we haven't figure out how to serialize some kinds of es parameters like 'QueryBuild', so some es methods are sync from the client itself.
	 *   this will improved in the future and move all to sync-service.
	 */
	public static class EsSetting extends Setting{
		private static final long serialVersionUID = 1277578152316933307L;
		
		private volatile Map<String, List<ElasticsearchSGProperties>> esCfgGroups;

		public Map<String, List<ElasticsearchSGProperties>> getEsCfgGroups() {
			return esCfgGroups;
		}

		public void setEsCfgGroups(
				Map<String, List<ElasticsearchSGProperties>> esCfgGroups) {
			this.esCfgGroups = esCfgGroups;
		}
	}
	
	public static class ElasticsearchSGProperties implements Serializable{
		private static final long serialVersionUID = 2741932273550699372L;
		
		private String clusterName = "elasticsearch";
		private String clusterNodes;
		private SearchGuardCfg searchguard;
		private Map<String, String> properties = new HashMap<>();
		
		public String getClusterName() {
			return clusterName;
		}
		public void setClusterName(String clusterName) {
			this.clusterName = clusterName;
		}
		public String getClusterNodes() {
			return clusterNodes;
		}
		public void setClusterNodes(String clusterNodes) {
			this.clusterNodes = clusterNodes;
		}
		public SearchGuardCfg getSearchguard() {
			return searchguard;
		}
		public void setSearchguard(SearchGuardCfg searchguard) {
			this.searchguard = searchguard;
		}
		public Map<String, String> getProperties() {
			return properties;
		}
		public void setProperties(Map<String, String> properties) {
			this.properties = properties;
		}
	}
	
	public static class SearchGuardCfg implements Serializable{
		private static final long serialVersionUID = 2127460466332926583L;
		
		private String pemkey;
		private String pemcert;
		private String cacert;
		
		public String getPemkey() {
			return pemkey;
		}
		public void setPemkey(String pemkey) {
			this.pemkey = pemkey;
		}
		public String getPemcert() {
			return pemcert;
		}
		public void setPemcert(String pemcert) {
			this.pemcert = pemcert;
		}
		public String getCacert() {
			return cacert;
		}
		public void setCacert(String cacert) {
			this.cacert = cacert;
		}
	}
}
