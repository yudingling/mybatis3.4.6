package com.zeasn.common.ext1.datasync.data;

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

	public enum ActionType{
		SAVE,
		
		DELETE_WITH_QUERY,
		DELETE_WITH_FILTER,
		DELETE_WITH_MODEL,
		
		UPDATE
	}
}
