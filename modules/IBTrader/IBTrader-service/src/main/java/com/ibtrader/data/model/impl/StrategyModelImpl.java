/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.ibtrader.data.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.ibtrader.data.model.Strategy;
import com.ibtrader.data.model.StrategyModel;
import com.ibtrader.data.model.StrategySoap;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the Strategy service. Represents a row in the &quot;ibtrader_Strategy&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link StrategyModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link StrategyImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StrategyImpl
 * @see Strategy
 * @see StrategyModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class StrategyModelImpl extends BaseModelImpl<Strategy>
	implements StrategyModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a strategy model instance should use the {@link Strategy} interface instead.
	 */
	public static final String TABLE_NAME = "ibtrader_Strategy";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "strategyID", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "name", Types.VARCHAR },
			{ "description", Types.CLOB },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "active_", Types.BOOLEAN },
			{ "status", Types.INTEGER },
			{ "statusByUserId", Types.BIGINT },
			{ "statusByUserName", Types.VARCHAR },
			{ "statusDate", Types.TIMESTAMP },
			{ "type_", Types.VARCHAR },
			{ "can_override_params", Types.BOOLEAN },
			{ "className", Types.VARCHAR },
			{ "userId", Types.BIGINT }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("strategyID", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.CLOB);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("active_", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("statusByUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("statusByUserName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("statusDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("can_override_params", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("className", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE = "create table ibtrader_Strategy (uuid_ VARCHAR(75) null,strategyID LONG not null primary key,groupId LONG,companyId LONG,name VARCHAR(75) null,description TEXT null,createDate DATE null,modifiedDate DATE null,active_ BOOLEAN,status INTEGER,statusByUserId LONG,statusByUserName VARCHAR(75) null,statusDate DATE null,type_ VARCHAR(75) null,can_override_params BOOLEAN,className VARCHAR(75) null,userId LONG)";
	public static final String TABLE_SQL_DROP = "drop table ibtrader_Strategy";
	public static final String ORDER_BY_JPQL = " ORDER BY strategy.strategyID ASC";
	public static final String ORDER_BY_SQL = " ORDER BY ibtrader_Strategy.strategyID ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.service.foo.service.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.ibtrader.data.model.Strategy"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.service.foo.service.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.ibtrader.data.model.Strategy"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.service.foo.service.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.com.ibtrader.data.model.Strategy"),
			true);
	public static final long ACTIVE_COLUMN_BITMASK = 1L;
	public static final long COMPANYID_COLUMN_BITMASK = 2L;
	public static final long GROUPID_COLUMN_BITMASK = 4L;
	public static final long STATUS_COLUMN_BITMASK = 8L;
	public static final long STRATEGYID_COLUMN_BITMASK = 16L;
	public static final long UUID_COLUMN_BITMASK = 32L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Strategy toModel(StrategySoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Strategy model = new StrategyImpl();

		model.setUuid(soapModel.getUuid());
		model.setStrategyID(soapModel.getStrategyID());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setActive(soapModel.getActive());
		model.setStatus(soapModel.getStatus());
		model.setStatusByUserId(soapModel.getStatusByUserId());
		model.setStatusByUserName(soapModel.getStatusByUserName());
		model.setStatusDate(soapModel.getStatusDate());
		model.setType(soapModel.getType());
		model.setCan_override_params(soapModel.getCan_override_params());
		model.setClassName(soapModel.getClassName());
		model.setUserId(soapModel.getUserId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Strategy> toModels(StrategySoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Strategy> models = new ArrayList<Strategy>(soapModels.length);

		for (StrategySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.service.foo.service.util.PropsUtil.get(
				"lock.expiration.time.com.ibtrader.data.model.Strategy"));

	public StrategyModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _strategyID;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setStrategyID(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _strategyID;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Strategy.class;
	}

	@Override
	public String getModelClassName() {
		return Strategy.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("strategyID", getStrategyID());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("active", getActive());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());
		attributes.put("type", getType());
		attributes.put("can_override_params", getCan_override_params());
		attributes.put("className", getClassName());
		attributes.put("userId", getUserId());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long strategyID = (Long)attributes.get("strategyID");

		if (strategyID != null) {
			setStrategyID(strategyID);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Boolean can_override_params = (Boolean)attributes.get(
				"can_override_params");

		if (can_override_params != null) {
			setCan_override_params(can_override_params);
		}

		String className = (String)attributes.get("className");

		if (className != null) {
			setClassName(className);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return StringPool.BLANK;
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getStrategyID() {
		return _strategyID;
	}

	@Override
	public void setStrategyID(long strategyID) {
		_columnBitmask |= STRATEGYID_COLUMN_BITMASK;

		if (!_setOriginalStrategyID) {
			_setOriginalStrategyID = true;

			_originalStrategyID = _strategyID;
		}

		_strategyID = strategyID;
	}

	public long getOriginalStrategyID() {
		return _originalStrategyID;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return StringPool.BLANK;
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@JSON
	@Override
	public String getDescription() {
		if (_description == null) {
			return StringPool.BLANK;
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public boolean getActive() {
		return _active;
	}

	@JSON
	@Override
	public boolean isActive() {
		return _active;
	}

	@Override
	public void setActive(boolean active) {
		_columnBitmask |= ACTIVE_COLUMN_BITMASK;

		if (!_setOriginalActive) {
			_setOriginalActive = true;

			_originalActive = _active;
		}

		_active = active;
	}

	public boolean getOriginalActive() {
		return _originalActive;
	}

	@JSON
	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_columnBitmask |= STATUS_COLUMN_BITMASK;

		if (!_setOriginalStatus) {
			_setOriginalStatus = true;

			_originalStatus = _status;
		}

		_status = status;
	}

	public int getOriginalStatus() {
		return _originalStatus;
	}

	@JSON
	@Override
	public long getStatusByUserId() {
		return _statusByUserId;
	}

	@Override
	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	@Override
	public String getStatusByUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getStatusByUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
	}

	@JSON
	@Override
	public String getStatusByUserName() {
		if (_statusByUserName == null) {
			return StringPool.BLANK;
		}
		else {
			return _statusByUserName;
		}
	}

	@Override
	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	@JSON
	@Override
	public Date getStatusDate() {
		return _statusDate;
	}

	@Override
	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	@JSON
	@Override
	public String getType() {
		if (_type == null) {
			return StringPool.BLANK;
		}
		else {
			return _type;
		}
	}

	@Override
	public void setType(String type) {
		_type = type;
	}

	@JSON
	@Override
	public boolean getCan_override_params() {
		return _can_override_params;
	}

	@JSON
	@Override
	public boolean isCan_override_params() {
		return _can_override_params;
	}

	@Override
	public void setCan_override_params(boolean can_override_params) {
		_can_override_params = can_override_params;
	}

	@JSON
	@Override
	public String getClassName() {
		if (_className == null) {
			return StringPool.BLANK;
		}
		else {
			return _className;
		}
	}

	@Override
	public void setClassName(String className) {
		_className = className;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				Strategy.class.getName()));
	}

	@Override
	public boolean isApproved() {
		if (getStatus() == WorkflowConstants.STATUS_APPROVED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDenied() {
		if (getStatus() == WorkflowConstants.STATUS_DENIED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDraft() {
		if (getStatus() == WorkflowConstants.STATUS_DRAFT) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isExpired() {
		if (getStatus() == WorkflowConstants.STATUS_EXPIRED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isInactive() {
		if (getStatus() == WorkflowConstants.STATUS_INACTIVE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isIncomplete() {
		if (getStatus() == WorkflowConstants.STATUS_INCOMPLETE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isPending() {
		if (getStatus() == WorkflowConstants.STATUS_PENDING) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isScheduled() {
		if (getStatus() == WorkflowConstants.STATUS_SCHEDULED) {
			return true;
		}
		else {
			return false;
		}
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			Strategy.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Strategy toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Strategy)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		StrategyImpl strategyImpl = new StrategyImpl();

		strategyImpl.setUuid(getUuid());
		strategyImpl.setStrategyID(getStrategyID());
		strategyImpl.setGroupId(getGroupId());
		strategyImpl.setCompanyId(getCompanyId());
		strategyImpl.setName(getName());
		strategyImpl.setDescription(getDescription());
		strategyImpl.setCreateDate(getCreateDate());
		strategyImpl.setModifiedDate(getModifiedDate());
		strategyImpl.setActive(getActive());
		strategyImpl.setStatus(getStatus());
		strategyImpl.setStatusByUserId(getStatusByUserId());
		strategyImpl.setStatusByUserName(getStatusByUserName());
		strategyImpl.setStatusDate(getStatusDate());
		strategyImpl.setType(getType());
		strategyImpl.setCan_override_params(getCan_override_params());
		strategyImpl.setClassName(getClassName());
		strategyImpl.setUserId(getUserId());

		strategyImpl.resetOriginalValues();

		return strategyImpl;
	}

	@Override
	public int compareTo(Strategy strategy) {
		long primaryKey = strategy.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Strategy)) {
			return false;
		}

		Strategy strategy = (Strategy)obj;

		long primaryKey = strategy.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		StrategyModelImpl strategyModelImpl = this;

		strategyModelImpl._originalUuid = strategyModelImpl._uuid;

		strategyModelImpl._originalStrategyID = strategyModelImpl._strategyID;

		strategyModelImpl._setOriginalStrategyID = false;

		strategyModelImpl._originalGroupId = strategyModelImpl._groupId;

		strategyModelImpl._setOriginalGroupId = false;

		strategyModelImpl._originalCompanyId = strategyModelImpl._companyId;

		strategyModelImpl._setOriginalCompanyId = false;

		strategyModelImpl._setModifiedDate = false;

		strategyModelImpl._originalActive = strategyModelImpl._active;

		strategyModelImpl._setOriginalActive = false;

		strategyModelImpl._originalStatus = strategyModelImpl._status;

		strategyModelImpl._setOriginalStatus = false;

		strategyModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Strategy> toCacheModel() {
		StrategyCacheModel strategyCacheModel = new StrategyCacheModel();

		strategyCacheModel.uuid = getUuid();

		String uuid = strategyCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			strategyCacheModel.uuid = null;
		}

		strategyCacheModel.strategyID = getStrategyID();

		strategyCacheModel.groupId = getGroupId();

		strategyCacheModel.companyId = getCompanyId();

		strategyCacheModel.name = getName();

		String name = strategyCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			strategyCacheModel.name = null;
		}

		strategyCacheModel.description = getDescription();

		String description = strategyCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			strategyCacheModel.description = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			strategyCacheModel.createDate = createDate.getTime();
		}
		else {
			strategyCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			strategyCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			strategyCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		strategyCacheModel.active = getActive();

		strategyCacheModel.status = getStatus();

		strategyCacheModel.statusByUserId = getStatusByUserId();

		strategyCacheModel.statusByUserName = getStatusByUserName();

		String statusByUserName = strategyCacheModel.statusByUserName;

		if ((statusByUserName != null) && (statusByUserName.length() == 0)) {
			strategyCacheModel.statusByUserName = null;
		}

		Date statusDate = getStatusDate();

		if (statusDate != null) {
			strategyCacheModel.statusDate = statusDate.getTime();
		}
		else {
			strategyCacheModel.statusDate = Long.MIN_VALUE;
		}

		strategyCacheModel.type = getType();

		String type = strategyCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			strategyCacheModel.type = null;
		}

		strategyCacheModel.can_override_params = getCan_override_params();

		strategyCacheModel.className = getClassName();

		String className = strategyCacheModel.className;

		if ((className != null) && (className.length() == 0)) {
			strategyCacheModel.className = null;
		}

		strategyCacheModel.userId = getUserId();

		return strategyCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", strategyID=");
		sb.append(getStrategyID());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", active=");
		sb.append(getActive());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", statusByUserId=");
		sb.append(getStatusByUserId());
		sb.append(", statusByUserName=");
		sb.append(getStatusByUserName());
		sb.append(", statusDate=");
		sb.append(getStatusDate());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", can_override_params=");
		sb.append(getCan_override_params());
		sb.append(", className=");
		sb.append(getClassName());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(55);

		sb.append("<model><model-name>");
		sb.append("com.ibtrader.data.model.Strategy");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>strategyID</column-name><column-value><![CDATA[");
		sb.append(getStrategyID());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>active</column-name><column-value><![CDATA[");
		sb.append(getActive());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserId</column-name><column-value><![CDATA[");
		sb.append(getStatusByUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserName</column-name><column-value><![CDATA[");
		sb.append(getStatusByUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusDate</column-name><column-value><![CDATA[");
		sb.append(getStatusDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>can_override_params</column-name><column-value><![CDATA[");
		sb.append(getCan_override_params());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>className</column-name><column-value><![CDATA[");
		sb.append(getClassName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = Strategy.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			Strategy.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _strategyID;
	private long _originalStrategyID;
	private boolean _setOriginalStrategyID;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private String _name;
	private String _description;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private boolean _active;
	private boolean _originalActive;
	private boolean _setOriginalActive;
	private int _status;
	private int _originalStatus;
	private boolean _setOriginalStatus;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private String _type;
	private boolean _can_override_params;
	private String _className;
	private long _userId;
	private long _columnBitmask;
	private Strategy _escapedModel;
}