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

package com.ibtrader.data.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the BackTesting service. Represents a row in the &quot;ibtrader_BackTesting&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.ibtrader.data.model.impl.BackTestingModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.ibtrader.data.model.impl.BackTestingImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BackTesting
 * @see com.ibtrader.data.model.impl.BackTestingImpl
 * @see com.ibtrader.data.model.impl.BackTestingModelImpl
 * @generated
 */
@ProviderType
public interface BackTestingModel extends BaseModel<BackTesting>, ShardedModel,
	StagedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a back testing model instance should use the {@link BackTesting} interface instead.
	 */

	/**
	 * Returns the primary key of this back testing.
	 *
	 * @return the primary key of this back testing
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this back testing.
	 *
	 * @param primaryKey the primary key of this back testing
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this back testing.
	 *
	 * @return the uuid of this back testing
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this back testing.
	 *
	 * @param uuid the uuid of this back testing
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the back t ID of this back testing.
	 *
	 * @return the back t ID of this back testing
	 */
	public long getBackTId();

	/**
	 * Sets the back t ID of this back testing.
	 *
	 * @param backTId the back t ID of this back testing
	 */
	public void setBackTId(long backTId);

	/**
	 * Returns the group ID of this back testing.
	 *
	 * @return the group ID of this back testing
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this back testing.
	 *
	 * @param groupId the group ID of this back testing
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this back testing.
	 *
	 * @return the company ID of this back testing
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this back testing.
	 *
	 * @param companyId the company ID of this back testing
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the create date of this back testing.
	 *
	 * @return the create date of this back testing
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this back testing.
	 *
	 * @param createDate the create date of this back testing
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this back testing.
	 *
	 * @return the modified date of this back testing
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this back testing.
	 *
	 * @param modifiedDate the modified date of this back testing
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the from date of this back testing.
	 *
	 * @return the from date of this back testing
	 */
	public Date getFromDate();

	/**
	 * Sets the from date of this back testing.
	 *
	 * @param fromDate the from date of this back testing
	 */
	public void setFromDate(Date fromDate);

	/**
	 * Returns the to date of this back testing.
	 *
	 * @return the to date of this back testing
	 */
	public Date getToDate();

	/**
	 * Sets the to date of this back testing.
	 *
	 * @param toDate the to date of this back testing
	 */
	public void setToDate(Date toDate);

	/**
	 * Returns the last run date of this back testing.
	 *
	 * @return the last run date of this back testing
	 */
	public Date getLastRunDate();

	/**
	 * Sets the last run date of this back testing.
	 *
	 * @param lastRunDate the last run date of this back testing
	 */
	public void setLastRunDate(Date lastRunDate);

	/**
	 * Returns the share ID of this back testing.
	 *
	 * @return the share ID of this back testing
	 */
	public long getShareId();

	/**
	 * Sets the share ID of this back testing.
	 *
	 * @param shareId the share ID of this back testing
	 */
	public void setShareId(long shareId);

	/**
	 * Returns the countorders buy of this back testing.
	 *
	 * @return the countorders buy of this back testing
	 */
	public long getCountordersBUY();

	/**
	 * Sets the countorders buy of this back testing.
	 *
	 * @param countordersBUY the countorders buy of this back testing
	 */
	public void setCountordersBUY(long countordersBUY);

	/**
	 * Returns the countorders sell of this back testing.
	 *
	 * @return the countorders sell of this back testing
	 */
	public long getCountordersSELL();

	/**
	 * Sets the countorders sell of this back testing.
	 *
	 * @param countordersSELL the countorders sell of this back testing
	 */
	public void setCountordersSELL(long countordersSELL);

	/**
	 * Returns the profitorders buy of this back testing.
	 *
	 * @return the profitorders buy of this back testing
	 */
	public double getProfitordersBUY();

	/**
	 * Sets the profitorders buy of this back testing.
	 *
	 * @param profitordersBUY the profitorders buy of this back testing
	 */
	public void setProfitordersBUY(double profitordersBUY);

	/**
	 * Returns the profitorders sell of this back testing.
	 *
	 * @return the profitorders sell of this back testing
	 */
	public double getProfitordersSELL();

	/**
	 * Sets the profitorders sell of this back testing.
	 *
	 * @param profitordersSELL the profitorders sell of this back testing
	 */
	public void setProfitordersSELL(double profitordersSELL);

	/**
	 * Returns the status of this back testing.
	 *
	 * @return the status of this back testing
	 */
	@AutoEscape
	public String getStatus();

	/**
	 * Sets the status of this back testing.
	 *
	 * @param status the status of this back testing
	 */
	public void setStatus(String status);

	/**
	 * Returns the description of this back testing.
	 *
	 * @return the description of this back testing
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this back testing.
	 *
	 * @param description the description of this back testing
	 */
	public void setDescription(String description);

	/**
	 * Returns the start date of this back testing.
	 *
	 * @return the start date of this back testing
	 */
	public Date getStartDate();

	/**
	 * Sets the start date of this back testing.
	 *
	 * @param startDate the start date of this back testing
	 */
	public void setStartDate(Date startDate);

	/**
	 * Returns the end date of this back testing.
	 *
	 * @return the end date of this back testing
	 */
	public Date getEndDate();

	/**
	 * Sets the end date of this back testing.
	 *
	 * @param endDate the end date of this back testing
	 */
	public void setEndDate(Date endDate);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(BackTesting backTesting);

	@Override
	public int hashCode();

	@Override
	public CacheModel<BackTesting> toCacheModel();

	@Override
	public BackTesting toEscapedModel();

	@Override
	public BackTesting toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}