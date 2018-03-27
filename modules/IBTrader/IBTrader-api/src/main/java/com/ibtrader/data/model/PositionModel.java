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
 * The base model interface for the Position service. Represents a row in the &quot;ibtrader_Position&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.ibtrader.data.model.impl.PositionModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.ibtrader.data.model.impl.PositionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Position
 * @see com.ibtrader.data.model.impl.PositionImpl
 * @see com.ibtrader.data.model.impl.PositionModelImpl
 * @generated
 */
@ProviderType
public interface PositionModel extends BaseModel<Position>, ShardedModel,
	StagedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a position model instance should use the {@link Position} interface instead.
	 */

	/**
	 * Returns the primary key of this position.
	 *
	 * @return the primary key of this position
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this position.
	 *
	 * @param primaryKey the primary key of this position
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this position.
	 *
	 * @return the uuid of this position
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this position.
	 *
	 * @param uuid the uuid of this position
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the position ID of this position.
	 *
	 * @return the position ID of this position
	 */
	public long getPositionId();

	/**
	 * Sets the position ID of this position.
	 *
	 * @param positionId the position ID of this position
	 */
	public void setPositionId(long positionId);

	/**
	 * Returns the group ID of this position.
	 *
	 * @return the group ID of this position
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this position.
	 *
	 * @param groupId the group ID of this position
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this position.
	 *
	 * @return the company ID of this position
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this position.
	 *
	 * @param companyId the company ID of this position
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the share ID of this position.
	 *
	 * @return the share ID of this position
	 */
	public long getShareId();

	/**
	 * Sets the share ID of this position.
	 *
	 * @param shareId the share ID of this position
	 */
	public void setShareId(long shareId);

	/**
	 * Returns the create date of this position.
	 *
	 * @return the create date of this position
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this position.
	 *
	 * @param createDate the create date of this position
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this position.
	 *
	 * @return the modified date of this position
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this position.
	 *
	 * @param modifiedDate the modified date of this position
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the state of this position.
	 *
	 * @return the state of this position
	 */
	@AutoEscape
	public String getState();

	/**
	 * Sets the state of this position.
	 *
	 * @param state the state of this position
	 */
	public void setState(String state);

	/**
	 * Returns the state_in of this position.
	 *
	 * @return the state_in of this position
	 */
	@AutoEscape
	public String getState_in();

	/**
	 * Sets the state_in of this position.
	 *
	 * @param state_in the state_in of this position
	 */
	public void setState_in(String state_in);

	/**
	 * Returns the state_out of this position.
	 *
	 * @return the state_out of this position
	 */
	@AutoEscape
	public String getState_out();

	/**
	 * Sets the state_out of this position.
	 *
	 * @param state_out the state_out of this position
	 */
	public void setState_out(String state_out);

	/**
	 * Returns the description of this position.
	 *
	 * @return the description of this position
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this position.
	 *
	 * @param description the description of this position
	 */
	public void setDescription(String description);

	/**
	 * Returns the price_in of this position.
	 *
	 * @return the price_in of this position
	 */
	public double getPrice_in();

	/**
	 * Sets the price_in of this position.
	 *
	 * @param price_in the price_in of this position
	 */
	public void setPrice_in(double price_in);

	/**
	 * Returns the price_real_in of this position.
	 *
	 * @return the price_real_in of this position
	 */
	public double getPrice_real_in();

	/**
	 * Sets the price_real_in of this position.
	 *
	 * @param price_real_in the price_real_in of this position
	 */
	public void setPrice_real_in(double price_real_in);

	/**
	 * Returns the limit_price_in of this position.
	 *
	 * @return the limit_price_in of this position
	 */
	public double getLimit_price_in();

	/**
	 * Sets the limit_price_in of this position.
	 *
	 * @param limit_price_in the limit_price_in of this position
	 */
	public void setLimit_price_in(double limit_price_in);

	/**
	 * Returns the date_in of this position.
	 *
	 * @return the date_in of this position
	 */
	public Date getDate_in();

	/**
	 * Sets the date_in of this position.
	 *
	 * @param date_in the date_in of this position
	 */
	public void setDate_in(Date date_in);

	/**
	 * Returns the date_real_in of this position.
	 *
	 * @return the date_real_in of this position
	 */
	public Date getDate_real_in();

	/**
	 * Sets the date_real_in of this position.
	 *
	 * @param date_real_in the date_real_in of this position
	 */
	public void setDate_real_in(Date date_real_in);

	/**
	 * Returns the position id_tws_in of this position.
	 *
	 * @return the position id_tws_in of this position
	 */
	public long getPositionId_tws_in();

	/**
	 * Sets the position id_tws_in of this position.
	 *
	 * @param positionId_tws_in the position id_tws_in of this position
	 */
	public void setPositionId_tws_in(long positionId_tws_in);

	/**
	 * Returns the position id_tws_out of this position.
	 *
	 * @return the position id_tws_out of this position
	 */
	public long getPositionId_tws_out();

	/**
	 * Sets the position id_tws_out of this position.
	 *
	 * @param positionId_tws_out the position id_tws_out of this position
	 */
	public void setPositionId_tws_out(long positionId_tws_out);

	/**
	 * Returns the type of this position.
	 *
	 * @return the type of this position
	 */
	@AutoEscape
	public String getType();

	/**
	 * Sets the type of this position.
	 *
	 * @param type the type of this position
	 */
	public void setType(String type);

	/**
	 * Returns the price_out of this position.
	 *
	 * @return the price_out of this position
	 */
	public double getPrice_out();

	/**
	 * Sets the price_out of this position.
	 *
	 * @param price_out the price_out of this position
	 */
	public void setPrice_out(double price_out);

	/**
	 * Returns the price_real_out of this position.
	 *
	 * @return the price_real_out of this position
	 */
	public double getPrice_real_out();

	/**
	 * Sets the price_real_out of this position.
	 *
	 * @param price_real_out the price_real_out of this position
	 */
	public void setPrice_real_out(double price_real_out);

	/**
	 * Returns the limit_price_out of this position.
	 *
	 * @return the limit_price_out of this position
	 */
	public double getLimit_price_out();

	/**
	 * Sets the limit_price_out of this position.
	 *
	 * @param limit_price_out the limit_price_out of this position
	 */
	public void setLimit_price_out(double limit_price_out);

	/**
	 * Returns the date_out of this position.
	 *
	 * @return the date_out of this position
	 */
	public Date getDate_out();

	/**
	 * Sets the date_out of this position.
	 *
	 * @param date_out the date_out of this position
	 */
	public void setDate_out(Date date_out);

	/**
	 * Returns the date_real_out of this position.
	 *
	 * @return the date_real_out of this position
	 */
	public Date getDate_real_out();

	/**
	 * Sets the date_real_out of this position.
	 *
	 * @param date_real_out the date_real_out of this position
	 */
	public void setDate_real_out(Date date_real_out);

	/**
	 * Returns the share_number of this position.
	 *
	 * @return the share_number of this position
	 */
	public long getShare_number();

	/**
	 * Sets the share_number of this position.
	 *
	 * @param share_number the share_number of this position
	 */
	public void setShare_number(long share_number);

	/**
	 * Returns the share_number_to_trade of this position.
	 *
	 * @return the share_number_to_trade of this position
	 */
	public long getShare_number_to_trade();

	/**
	 * Sets the share_number_to_trade of this position.
	 *
	 * @param share_number_to_trade the share_number_to_trade of this position
	 */
	public void setShare_number_to_trade(long share_number_to_trade);

	/**
	 * Returns the share_number_traded of this position.
	 *
	 * @return the share_number_traded of this position
	 */
	public long getShare_number_traded();

	/**
	 * Sets the share_number_traded of this position.
	 *
	 * @param share_number_traded the share_number_traded of this position
	 */
	public void setShare_number_traded(long share_number_traded);

	/**
	 * Returns the realtime id_in of this position.
	 *
	 * @return the realtime id_in of this position
	 */
	public long getRealtimeId_in();

	/**
	 * Sets the realtime id_in of this position.
	 *
	 * @param realtimeId_in the realtime id_in of this position
	 */
	public void setRealtimeId_in(long realtimeId_in);

	/**
	 * Returns the realtime id_out of this position.
	 *
	 * @return the realtime id_out of this position
	 */
	public long getRealtimeId_out();

	/**
	 * Sets the realtime id_out of this position.
	 *
	 * @param realtimeId_out the realtime id_out of this position
	 */
	public void setRealtimeId_out(long realtimeId_out);

	/**
	 * Returns the strategy_in of this position.
	 *
	 * @return the strategy_in of this position
	 */
	@AutoEscape
	public String getStrategy_in();

	/**
	 * Sets the strategy_in of this position.
	 *
	 * @param strategy_in the strategy_in of this position
	 */
	public void setStrategy_in(String strategy_in);

	/**
	 * Returns the strategy_out of this position.
	 *
	 * @return the strategy_out of this position
	 */
	@AutoEscape
	public String getStrategy_out();

	/**
	 * Sets the strategy_out of this position.
	 *
	 * @param strategy_out the strategy_out of this position
	 */
	public void setStrategy_out(String strategy_out);

	/**
	 * Returns the percentualstoplost_out of this position.
	 *
	 * @return the percentualstoplost_out of this position
	 */
	public double getPercentualstoplost_out();

	/**
	 * Sets the percentualstoplost_out of this position.
	 *
	 * @param percentualstoplost_out the percentualstoplost_out of this position
	 */
	public void setPercentualstoplost_out(double percentualstoplost_out);

	/**
	 * Returns the pricestoplost_out of this position.
	 *
	 * @return the pricestoplost_out of this position
	 */
	public double getPricestoplost_out();

	/**
	 * Sets the pricestoplost_out of this position.
	 *
	 * @param pricestoplost_out the pricestoplost_out of this position
	 */
	public void setPricestoplost_out(double pricestoplost_out);

	/**
	 * Returns the percentualstopprofit_out of this position.
	 *
	 * @return the percentualstopprofit_out of this position
	 */
	public double getPercentualstopprofit_out();

	/**
	 * Sets the percentualstopprofit_out of this position.
	 *
	 * @param percentualstopprofit_out the percentualstopprofit_out of this position
	 */
	public void setPercentualstopprofit_out(double percentualstopprofit_out);

	/**
	 * Returns the pricestopprofit_out of this position.
	 *
	 * @return the pricestopprofit_out of this position
	 */
	public double getPricestopprofit_out();

	/**
	 * Sets the pricestopprofit_out of this position.
	 *
	 * @param pricestopprofit_out the pricestopprofit_out of this position
	 */
	public void setPricestopprofit_out(double pricestopprofit_out);

	/**
	 * Returns the pendingcancelled of this position.
	 *
	 * @return the pendingcancelled of this position
	 */
	public long getPendingcancelled();

	/**
	 * Sets the pendingcancelled of this position.
	 *
	 * @param pendingcancelled the pendingcancelled of this position
	 */
	public void setPendingcancelled(long pendingcancelled);

	/**
	 * Returns the trading_data_operations of this position.
	 *
	 * @return the trading_data_operations of this position
	 */
	@AutoEscape
	public String getTrading_data_operations();

	/**
	 * Sets the trading_data_operations of this position.
	 *
	 * @param trading_data_operations the trading_data_operations of this position
	 */
	public void setTrading_data_operations(String trading_data_operations);

	/**
	 * Returns the simulation_mode of this position.
	 *
	 * @return the simulation_mode of this position
	 */
	public boolean getSimulation_mode();

	/**
	 * Returns <code>true</code> if this position is simulation_mode.
	 *
	 * @return <code>true</code> if this position is simulation_mode; <code>false</code> otherwise
	 */
	public boolean isSimulation_mode();

	/**
	 * Sets whether this position is simulation_mode.
	 *
	 * @param simulation_mode the simulation_mode of this position
	 */
	public void setSimulation_mode(boolean simulation_mode);

	/**
	 * Returns the totalcommision of this position.
	 *
	 * @return the totalcommision of this position
	 */
	public double getTotalcommision();

	/**
	 * Sets the totalcommision of this position.
	 *
	 * @param totalcommision the totalcommision of this position
	 */
	public void setTotalcommision(double totalcommision);

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
	public int compareTo(Position position);

	@Override
	public int hashCode();

	@Override
	public CacheModel<Position> toCacheModel();

	@Override
	public Position toEscapedModel();

	@Override
	public Position toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}