package com.zeasn.common.ext1.datasync.data;

import java.util.Set;

public class EsSyncData extends SyncData {
	private static final long serialVersionUID = 1815560778676010106L;
	
	private ActionType action;
	private String index;
	private String type;
	private String id;
	private String parentId;
	private Long version;
	private String mappingJson;
	private String serializedValue;
	
	private Set<String> indexes;
	private Integer pageSize;
	private Long scrollTimeInMillis;
	private String serializedQueryBuilder;
	private String serializedScript;
	
	private boolean upsert = false;

	public ActionType getAction() {
		return action;
	}

	public void setAction(ActionType action) {
		this.action = action;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getMappingJson() {
		return mappingJson;
	}

	public void setMappingJson(String mappingJson) {
		this.mappingJson = mappingJson;
	}

	public String getSerializedValue() {
		return serializedValue;
	}

	public void setSerializedValue(String serializedValue) {
		this.serializedValue = serializedValue;
	}

	public boolean isUpsert() {
		return upsert;
	}

	public void setUpsert(boolean upsert) {
		this.upsert = upsert;
	}

	public Set<String> getIndexes() {
		return indexes;
	}

	public void setIndexes(Set<String> indexes) {
		this.indexes = indexes;
	}

	public String getSerializedQueryBuilder() {
		return serializedQueryBuilder;
	}

	public void setSerializedQueryBuilder(String serializedQueryBuilder) {
		this.serializedQueryBuilder = serializedQueryBuilder;
	}

	public String getSerializedScript() {
		return serializedScript;
	}

	public void setSerializedScript(String serializedScript) {
		this.serializedScript = serializedScript;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getScrollTimeInMillis() {
		return scrollTimeInMillis;
	}

	public void setScrollTimeInMillis(Long scrollTimeInMillis) {
		this.scrollTimeInMillis = scrollTimeInMillis;
	}

	public EsSyncData(){
		super();
	}
	
	public EsSyncData(String appName, String groupName, long deferMilliseconds,
			ActionType action, String index, String type,
			String id, String parentId, Long version, String mappingJson,
			String serializedValue, boolean upsert) {
		super(appName, groupName, deferMilliseconds);
		
		this.action = action;
		this.index = index;
		this.type = type;
		this.id = id;
		this.parentId = parentId;
		this.version = version;
		this.mappingJson = mappingJson;
		this.serializedValue = serializedValue;
		this.upsert = upsert;
	}
	
	public EsSyncData(String appName, String groupName, long deferMilliseconds,
			ActionType action, String index, String type, Integer pageSize, Long scrollTimeInMillis, String serializedQueryBuilder) {
		super(appName, groupName, deferMilliseconds);
		
		this.action = action;
		this.index = index;
		this.type = type;
		this.pageSize = pageSize;
		this.scrollTimeInMillis = scrollTimeInMillis;
		this.serializedQueryBuilder = serializedQueryBuilder;
	}
	
	public EsSyncData(String appName, String groupName, long deferMilliseconds,
			ActionType action, Set<String> indexes, String serializedQueryBuilder) {
		super(appName, groupName, deferMilliseconds);
		
		this.action = action;
		this.indexes = indexes;
		this.serializedQueryBuilder = serializedQueryBuilder;
	}
	
	public EsSyncData(String appName, String groupName, long deferMilliseconds,
			ActionType action, Set<String> indexes, String serializedScript, String serializedQueryBuilder) {
		super(appName, groupName, deferMilliseconds);
		
		this.action = action;
		this.indexes = indexes;
		this.serializedScript = serializedScript;
		this.serializedQueryBuilder = serializedQueryBuilder;
	}

	public enum ActionType{
		SAVE,
		
		DELETE_WITH_QUERY,
		DELETE_WITH_FILTER,
		DELETE_WITH_MODEL,
		
		UPDATE
	}
}
