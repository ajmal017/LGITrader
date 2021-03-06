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

import com.ibtrader.data.model.AuditIndicatorsStrategy;
import com.ibtrader.data.model.AuditIndicatorsStrategyModel;
import com.ibtrader.data.model.AuditIndicatorsStrategySoap;
import com.ibtrader.data.service.persistence.AuditIndicatorsStrategyPK;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the AuditIndicatorsStrategy service. Represents a row in the &quot;ibtrader_AuditIndicatorsStrategy&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link AuditIndicatorsStrategyModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AuditIndicatorsStrategyImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditIndicatorsStrategyImpl
 * @see AuditIndicatorsStrategy
 * @see AuditIndicatorsStrategyModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class AuditIndicatorsStrategyModelImpl extends BaseModelImpl<AuditIndicatorsStrategy>
	implements AuditIndicatorsStrategyModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a audit indicators strategy model instance should use the {@link AuditIndicatorsStrategy} interface instead.
	 */
	public static final String TABLE_NAME = "ibtrader_AuditIndicatorsStrategy";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "auditDate", Types.VARCHAR },
			{ "auditstrategy", Types.VARCHAR },
			{ "shareId", Types.BIGINT },
			{ "auditData", Types.CLOB }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("auditDate", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("auditstrategy", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("shareId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("auditData", Types.CLOB);
	}

	public static final String TABLE_SQL_CREATE = "create table ibtrader_AuditIndicatorsStrategy (uuid_ VARCHAR(75) null,groupId LONG not null,companyId LONG not null,auditDate VARCHAR(75) not null,auditstrategy VARCHAR(75) not null,shareId LONG not null,auditData TEXT null,primary key (groupId, companyId, auditDate, auditstrategy, shareId))";
	public static final String TABLE_SQL_DROP = "drop table ibtrader_AuditIndicatorsStrategy";
	public static final String ORDER_BY_JPQL = " ORDER BY auditIndicatorsStrategy.id.groupId ASC, auditIndicatorsStrategy.id.companyId ASC, auditIndicatorsStrategy.id.auditDate ASC, auditIndicatorsStrategy.id.auditstrategy ASC, auditIndicatorsStrategy.id.shareId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY ibtrader_AuditIndicatorsStrategy.groupId ASC, ibtrader_AuditIndicatorsStrategy.companyId ASC, ibtrader_AuditIndicatorsStrategy.auditDate ASC, ibtrader_AuditIndicatorsStrategy.auditstrategy ASC, ibtrader_AuditIndicatorsStrategy.shareId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.service.foo.service.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.ibtrader.data.model.AuditIndicatorsStrategy"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.service.foo.service.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.ibtrader.data.model.AuditIndicatorsStrategy"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.service.foo.service.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.com.ibtrader.data.model.AuditIndicatorsStrategy"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long GROUPID_COLUMN_BITMASK = 2L;
	public static final long UUID_COLUMN_BITMASK = 4L;
	public static final long AUDITDATE_COLUMN_BITMASK = 8L;
	public static final long AUDITSTRATEGY_COLUMN_BITMASK = 16L;
	public static final long SHAREID_COLUMN_BITMASK = 32L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static AuditIndicatorsStrategy toModel(
		AuditIndicatorsStrategySoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		AuditIndicatorsStrategy model = new AuditIndicatorsStrategyImpl();

		model.setUuid(soapModel.getUuid());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setAuditDate(soapModel.getAuditDate());
		model.setAuditstrategy(soapModel.getAuditstrategy());
		model.setShareId(soapModel.getShareId());
		model.setAuditData(soapModel.getAuditData());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<AuditIndicatorsStrategy> toModels(
		AuditIndicatorsStrategySoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<AuditIndicatorsStrategy> models = new ArrayList<AuditIndicatorsStrategy>(soapModels.length);

		for (AuditIndicatorsStrategySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.service.foo.service.util.PropsUtil.get(
				"lock.expiration.time.com.ibtrader.data.model.AuditIndicatorsStrategy"));

	public AuditIndicatorsStrategyModelImpl() {
	}

	@Override
	public AuditIndicatorsStrategyPK getPrimaryKey() {
		return new AuditIndicatorsStrategyPK(_groupId, _companyId, _auditDate,
			_auditstrategy, _shareId);
	}

	@Override
	public void setPrimaryKey(AuditIndicatorsStrategyPK primaryKey) {
		setGroupId(primaryKey.groupId);
		setCompanyId(primaryKey.companyId);
		setAuditDate(primaryKey.auditDate);
		setAuditstrategy(primaryKey.auditstrategy);
		setShareId(primaryKey.shareId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new AuditIndicatorsStrategyPK(_groupId, _companyId, _auditDate,
			_auditstrategy, _shareId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((AuditIndicatorsStrategyPK)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return AuditIndicatorsStrategy.class;
	}

	@Override
	public String getModelClassName() {
		return AuditIndicatorsStrategy.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("auditDate", getAuditDate());
		attributes.put("auditstrategy", getAuditstrategy());
		attributes.put("shareId", getShareId());
		attributes.put("auditData", getAuditData());

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

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		String auditDate = (String)attributes.get("auditDate");

		if (auditDate != null) {
			setAuditDate(auditDate);
		}

		String auditstrategy = (String)attributes.get("auditstrategy");

		if (auditstrategy != null) {
			setAuditstrategy(auditstrategy);
		}

		Long shareId = (Long)attributes.get("shareId");

		if (shareId != null) {
			setShareId(shareId);
		}

		String auditData = (String)attributes.get("auditData");

		if (auditData != null) {
			setAuditData(auditData);
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
	public String getAuditDate() {
		if (_auditDate == null) {
			return StringPool.BLANK;
		}
		else {
			return _auditDate;
		}
	}

	@Override
	public void setAuditDate(String auditDate) {
		_auditDate = auditDate;
	}

	@JSON
	@Override
	public String getAuditstrategy() {
		if (_auditstrategy == null) {
			return StringPool.BLANK;
		}
		else {
			return _auditstrategy;
		}
	}

	@Override
	public void setAuditstrategy(String auditstrategy) {
		_auditstrategy = auditstrategy;
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

	@JSON
	@Override
	public String getAuditData() {
		if (_auditData == null) {
			return StringPool.BLANK;
		}
		else {
			return _auditData;
		}
	}

	@Override
	public void setAuditData(String auditData) {
		_auditData = auditData;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public AuditIndicatorsStrategy toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (AuditIndicatorsStrategy)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		AuditIndicatorsStrategyImpl auditIndicatorsStrategyImpl = new AuditIndicatorsStrategyImpl();

		auditIndicatorsStrategyImpl.setUuid(getUuid());
		auditIndicatorsStrategyImpl.setGroupId(getGroupId());
		auditIndicatorsStrategyImpl.setCompanyId(getCompanyId());
		auditIndicatorsStrategyImpl.setAuditDate(getAuditDate());
		auditIndicatorsStrategyImpl.setAuditstrategy(getAuditstrategy());
		auditIndicatorsStrategyImpl.setShareId(getShareId());
		auditIndicatorsStrategyImpl.setAuditData(getAuditData());

		auditIndicatorsStrategyImpl.resetOriginalValues();

		return auditIndicatorsStrategyImpl;
	}

	@Override
	public int compareTo(AuditIndicatorsStrategy auditIndicatorsStrategy) {
		AuditIndicatorsStrategyPK primaryKey = auditIndicatorsStrategy.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AuditIndicatorsStrategy)) {
			return false;
		}

		AuditIndicatorsStrategy auditIndicatorsStrategy = (AuditIndicatorsStrategy)obj;

		AuditIndicatorsStrategyPK primaryKey = auditIndicatorsStrategy.getPrimaryKey();

		if (getPrimaryKey().equals(primaryKey)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return getPrimaryKey().hashCode();
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
		AuditIndicatorsStrategyModelImpl auditIndicatorsStrategyModelImpl = this;

		auditIndicatorsStrategyModelImpl._originalUuid = auditIndicatorsStrategyModelImpl._uuid;

		auditIndicatorsStrategyModelImpl._originalGroupId = auditIndicatorsStrategyModelImpl._groupId;

		auditIndicatorsStrategyModelImpl._setOriginalGroupId = false;

		auditIndicatorsStrategyModelImpl._originalCompanyId = auditIndicatorsStrategyModelImpl._companyId;

		auditIndicatorsStrategyModelImpl._setOriginalCompanyId = false;

		auditIndicatorsStrategyModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<AuditIndicatorsStrategy> toCacheModel() {
		AuditIndicatorsStrategyCacheModel auditIndicatorsStrategyCacheModel = new AuditIndicatorsStrategyCacheModel();

		auditIndicatorsStrategyCacheModel.auditIndicatorsStrategyPK = getPrimaryKey();

		auditIndicatorsStrategyCacheModel.uuid = getUuid();

		String uuid = auditIndicatorsStrategyCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			auditIndicatorsStrategyCacheModel.uuid = null;
		}

		auditIndicatorsStrategyCacheModel.groupId = getGroupId();

		auditIndicatorsStrategyCacheModel.companyId = getCompanyId();

		auditIndicatorsStrategyCacheModel.auditDate = getAuditDate();

		String auditDate = auditIndicatorsStrategyCacheModel.auditDate;

		if ((auditDate != null) && (auditDate.length() == 0)) {
			auditIndicatorsStrategyCacheModel.auditDate = null;
		}

		auditIndicatorsStrategyCacheModel.auditstrategy = getAuditstrategy();

		String auditstrategy = auditIndicatorsStrategyCacheModel.auditstrategy;

		if ((auditstrategy != null) && (auditstrategy.length() == 0)) {
			auditIndicatorsStrategyCacheModel.auditstrategy = null;
		}

		auditIndicatorsStrategyCacheModel.shareId = getShareId();

		auditIndicatorsStrategyCacheModel.auditData = getAuditData();

		String auditData = auditIndicatorsStrategyCacheModel.auditData;

		if ((auditData != null) && (auditData.length() == 0)) {
			auditIndicatorsStrategyCacheModel.auditData = null;
		}

		return auditIndicatorsStrategyCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", auditDate=");
		sb.append(getAuditDate());
		sb.append(", auditstrategy=");
		sb.append(getAuditstrategy());
		sb.append(", shareId=");
		sb.append(getShareId());
		sb.append(", auditData=");
		sb.append(getAuditData());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("com.ibtrader.data.model.AuditIndicatorsStrategy");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
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
			"<column><column-name>auditDate</column-name><column-value><![CDATA[");
		sb.append(getAuditDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>auditstrategy</column-name><column-value><![CDATA[");
		sb.append(getAuditstrategy());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>shareId</column-name><column-value><![CDATA[");
		sb.append(getShareId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>auditData</column-name><column-value><![CDATA[");
		sb.append(getAuditData());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = AuditIndicatorsStrategy.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			AuditIndicatorsStrategy.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private String _auditDate;
	private String _auditstrategy;
	private long _shareId;
	private String _auditData;
	private long _columnBitmask;
	private AuditIndicatorsStrategy _escapedModel;
}