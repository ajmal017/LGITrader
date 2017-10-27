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

import com.ibtrader.data.model.StrategyShare;
import com.ibtrader.data.model.StrategyShareModel;
import com.ibtrader.data.model.StrategyShareSoap;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the StrategyShare service. Represents a row in the &quot;ibtrader_StrategyShare&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link StrategyShareModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link StrategyShareImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StrategyShareImpl
 * @see StrategyShare
 * @see StrategyShareModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class StrategyShareModelImpl extends BaseModelImpl<StrategyShare>
	implements StrategyShareModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a strategy share model instance should use the {@link StrategyShare} interface instead.
	 */
	public static final String TABLE_NAME = "ibtrader_StrategyShare";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "strategyshareId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "strategyId", Types.BIGINT },
			{ "shareId", Types.BIGINT }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("strategyshareId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("strategyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("shareId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE = "create table ibtrader_StrategyShare (uuid_ VARCHAR(75) null,strategyshareId LONG not null primary key,groupId LONG,companyId LONG,createDate DATE null,modifiedDate DATE null,strategyId LONG,shareId LONG)";
	public static final String TABLE_SQL_DROP = "drop table ibtrader_StrategyShare";
	public static final String ORDER_BY_JPQL = " ORDER BY strategyShare.strategyshareId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY ibtrader_StrategyShare.strategyshareId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.ibtrader.data.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.ibtrader.data.model.StrategyShare"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.ibtrader.data.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.ibtrader.data.model.StrategyShare"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.ibtrader.data.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.ibtrader.data.model.StrategyShare"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long GROUPID_COLUMN_BITMASK = 2L;
	public static final long UUID_COLUMN_BITMASK = 4L;
	public static final long STRATEGYSHAREID_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static StrategyShare toModel(StrategyShareSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		StrategyShare model = new StrategyShareImpl();

		model.setUuid(soapModel.getUuid());
		model.setStrategyshareId(soapModel.getStrategyshareId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setStrategyId(soapModel.getStrategyId());
		model.setShareId(soapModel.getShareId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<StrategyShare> toModels(StrategyShareSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<StrategyShare> models = new ArrayList<StrategyShare>(soapModels.length);

		for (StrategyShareSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.ibtrader.data.service.util.ServiceProps.get(
				"lock.expiration.time.com.ibtrader.data.model.StrategyShare"));

	public StrategyShareModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _strategyshareId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setStrategyshareId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _strategyshareId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return StrategyShare.class;
	}

	@Override
	public String getModelClassName() {
		return StrategyShare.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("strategyshareId", getStrategyshareId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("strategyId", getStrategyId());
		attributes.put("shareId", getShareId());

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

		Long strategyshareId = (Long)attributes.get("strategyshareId");

		if (strategyshareId != null) {
			setStrategyshareId(strategyshareId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long strategyId = (Long)attributes.get("strategyId");

		if (strategyId != null) {
			setStrategyId(strategyId);
		}

		Long shareId = (Long)attributes.get("shareId");

		if (shareId != null) {
			setShareId(shareId);
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
	public long getStrategyshareId() {
		return _strategyshareId;
	}

	@Override
	public void setStrategyshareId(long strategyshareId) {
		_strategyshareId = strategyshareId;
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
	public long getStrategyId() {
		return _strategyId;
	}

	@Override
	public void setStrategyId(long strategyId) {
		_strategyId = strategyId;
	}

	@JSON
	@Override
	public long getShareId() {
		return _shareId;
	}

	@Override
	public void setShareId(long shareId) {
		_shareId = shareId;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				StrategyShare.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			StrategyShare.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public StrategyShare toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (StrategyShare)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		StrategyShareImpl strategyShareImpl = new StrategyShareImpl();

		strategyShareImpl.setUuid(getUuid());
		strategyShareImpl.setStrategyshareId(getStrategyshareId());
		strategyShareImpl.setGroupId(getGroupId());
		strategyShareImpl.setCompanyId(getCompanyId());
		strategyShareImpl.setCreateDate(getCreateDate());
		strategyShareImpl.setModifiedDate(getModifiedDate());
		strategyShareImpl.setStrategyId(getStrategyId());
		strategyShareImpl.setShareId(getShareId());

		strategyShareImpl.resetOriginalValues();

		return strategyShareImpl;
	}

	@Override
	public int compareTo(StrategyShare strategyShare) {
		long primaryKey = strategyShare.getPrimaryKey();

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

		if (!(obj instanceof StrategyShare)) {
			return false;
		}

		StrategyShare strategyShare = (StrategyShare)obj;

		long primaryKey = strategyShare.getPrimaryKey();

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
		StrategyShareModelImpl strategyShareModelImpl = this;

		strategyShareModelImpl._originalUuid = strategyShareModelImpl._uuid;

		strategyShareModelImpl._originalGroupId = strategyShareModelImpl._groupId;

		strategyShareModelImpl._setOriginalGroupId = false;

		strategyShareModelImpl._originalCompanyId = strategyShareModelImpl._companyId;

		strategyShareModelImpl._setOriginalCompanyId = false;

		strategyShareModelImpl._setModifiedDate = false;

		strategyShareModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<StrategyShare> toCacheModel() {
		StrategyShareCacheModel strategyShareCacheModel = new StrategyShareCacheModel();

		strategyShareCacheModel.uuid = getUuid();

		String uuid = strategyShareCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			strategyShareCacheModel.uuid = null;
		}

		strategyShareCacheModel.strategyshareId = getStrategyshareId();

		strategyShareCacheModel.groupId = getGroupId();

		strategyShareCacheModel.companyId = getCompanyId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			strategyShareCacheModel.createDate = createDate.getTime();
		}
		else {
			strategyShareCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			strategyShareCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			strategyShareCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		strategyShareCacheModel.strategyId = getStrategyId();

		strategyShareCacheModel.shareId = getShareId();

		return strategyShareCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", strategyshareId=");
		sb.append(getStrategyshareId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", strategyId=");
		sb.append(getStrategyId());
		sb.append(", shareId=");
		sb.append(getShareId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(28);

		sb.append("<model><model-name>");
		sb.append("com.ibtrader.data.model.StrategyShare");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>strategyshareId</column-name><column-value><![CDATA[");
		sb.append(getStrategyshareId());
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
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>strategyId</column-name><column-value><![CDATA[");
		sb.append(getStrategyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>shareId</column-name><column-value><![CDATA[");
		sb.append(getShareId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = StrategyShare.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			StrategyShare.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _strategyshareId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _strategyId;
	private long _shareId;
	private long _columnBitmask;
	private StrategyShare _escapedModel;
}