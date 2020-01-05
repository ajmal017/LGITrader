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
 * The base model interface for the Share service. Represents a row in the &quot;ibtrader_Share&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.ibtrader.data.model.impl.ShareModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.ibtrader.data.model.impl.ShareImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Share
 * @see com.ibtrader.data.model.impl.ShareImpl
 * @see com.ibtrader.data.model.impl.ShareModelImpl
 * @generated
 */
@ProviderType
public interface ShareModel extends BaseModel<Share>, ShardedModel, StagedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a share model instance should use the {@link Share} interface instead.
	 */

	/**
	 * Returns the primary key of this share.
	 *
	 * @return the primary key of this share
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this share.
	 *
	 * @param primaryKey the primary key of this share
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this share.
	 *
	 * @return the uuid of this share
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this share.
	 *
	 * @param uuid the uuid of this share
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the share ID of this share.
	 *
	 * @return the share ID of this share
	 */
	public long getShareId();

	/**
	 * Sets the share ID of this share.
	 *
	 * @param shareId the share ID of this share
	 */
	public void setShareId(long shareId);

	/**
	 * Returns the name of this share.
	 *
	 * @return the name of this share
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this share.
	 *
	 * @param name the name of this share
	 */
	public void setName(String name);

	/**
	 * Returns the symbol of this share.
	 *
	 * @return the symbol of this share
	 */
	@AutoEscape
	public String getSymbol();

	/**
	 * Sets the symbol of this share.
	 *
	 * @param symbol the symbol of this share
	 */
	public void setSymbol(String symbol);

	/**
	 * Returns the group ID of this share.
	 *
	 * @return the group ID of this share
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this share.
	 *
	 * @param groupId the group ID of this share
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this share.
	 *
	 * @return the company ID of this share
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this share.
	 *
	 * @param companyId the company ID of this share
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the create date of this share.
	 *
	 * @return the create date of this share
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this share.
	 *
	 * @param createDate the create date of this share
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this share.
	 *
	 * @return the modified date of this share
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this share.
	 *
	 * @param modifiedDate the modified date of this share
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the active of this share.
	 *
	 * @return the active of this share
	 */
	public boolean getActive();

	/**
	 * Returns <code>true</code> if this share is active.
	 *
	 * @return <code>true</code> if this share is active; <code>false</code> otherwise
	 */
	public boolean isActive();

	/**
	 * Sets whether this share is active.
	 *
	 * @param active the active of this share
	 */
	public void setActive(boolean active);

	/**
	 * Returns the numbertopurchase of this share.
	 *
	 * @return the numbertopurchase of this share
	 */
	public long getNumbertopurchase();

	/**
	 * Sets the numbertopurchase of this share.
	 *
	 * @param numbertopurchase the numbertopurchase of this share
	 */
	public void setNumbertopurchase(long numbertopurchase);

	/**
	 * Returns the percentual_limit_buy of this share.
	 *
	 * @return the percentual_limit_buy of this share
	 */
	public double getPercentual_limit_buy();

	/**
	 * Sets the percentual_limit_buy of this share.
	 *
	 * @param percentual_limit_buy the percentual_limit_buy of this share
	 */
	public void setPercentual_limit_buy(double percentual_limit_buy);

	/**
	 * Returns the percentual_stop_lost of this share.
	 *
	 * @return the percentual_stop_lost of this share
	 */
	public double getPercentual_stop_lost();

	/**
	 * Sets the percentual_stop_lost of this share.
	 *
	 * @param percentual_stop_lost the percentual_stop_lost of this share
	 */
	public void setPercentual_stop_lost(double percentual_stop_lost);

	/**
	 * Returns the percentual_stop_profit of this share.
	 *
	 * @return the percentual_stop_profit of this share
	 */
	public double getPercentual_stop_profit();

	/**
	 * Sets the percentual_stop_profit of this share.
	 *
	 * @param percentual_stop_profit the percentual_stop_profit of this share
	 */
	public void setPercentual_stop_profit(double percentual_stop_profit);

	/**
	 * Returns the percentual_stop_profit_position of this share.
	 *
	 * @return the percentual_stop_profit_position of this share
	 */
	public double getPercentual_stop_profit_position();

	/**
	 * Sets the percentual_stop_profit_position of this share.
	 *
	 * @param percentual_stop_profit_position the percentual_stop_profit_position of this share
	 */
	public void setPercentual_stop_profit_position(
		double percentual_stop_profit_position);

	/**
	 * Returns the percentual_trailling_stop_lost of this share.
	 *
	 * @return the percentual_trailling_stop_lost of this share
	 */
	public double getPercentual_trailling_stop_lost();

	/**
	 * Sets the percentual_trailling_stop_lost of this share.
	 *
	 * @param percentual_trailling_stop_lost the percentual_trailling_stop_lost of this share
	 */
	public void setPercentual_trailling_stop_lost(
		double percentual_trailling_stop_lost);

	/**
	 * Returns the expiry_date of this share.
	 *
	 * @return the expiry_date of this share
	 */
	public Date getExpiry_date();

	/**
	 * Sets the expiry_date of this share.
	 *
	 * @param expiry_date the expiry_date of this share
	 */
	public void setExpiry_date(Date expiry_date);

	/**
	 * Returns the expiry_expression of this share.
	 *
	 * @return the expiry_expression of this share
	 */
	@AutoEscape
	public String getExpiry_expression();

	/**
	 * Sets the expiry_expression of this share.
	 *
	 * @param expiry_expression the expiry_expression of this share
	 */
	public void setExpiry_expression(String expiry_expression);

	/**
	 * Returns the tick_futures of this share.
	 *
	 * @return the tick_futures of this share
	 */
	public double getTick_futures();

	/**
	 * Sets the tick_futures of this share.
	 *
	 * @param tick_futures the tick_futures of this share
	 */
	public void setTick_futures(double tick_futures);

	/**
	 * Returns the multiplier of this share.
	 *
	 * @return the multiplier of this share
	 */
	public long getMultiplier();

	/**
	 * Sets the multiplier of this share.
	 *
	 * @param multiplier the multiplier of this share
	 */
	public void setMultiplier(long multiplier);

	/**
	 * Returns the security_type of this share.
	 *
	 * @return the security_type of this share
	 */
	@AutoEscape
	public String getSecurity_type();

	/**
	 * Sets the security_type of this share.
	 *
	 * @param security_type the security_type of this share
	 */
	public void setSecurity_type(String security_type);

	/**
	 * Returns the exchange of this share.
	 *
	 * @return the exchange of this share
	 */
	@AutoEscape
	public String getExchange();

	/**
	 * Sets the exchange of this share.
	 *
	 * @param exchange the exchange of this share
	 */
	public void setExchange(String exchange);

	/**
	 * Returns the primary_exchange of this share.
	 *
	 * @return the primary_exchange of this share
	 */
	@AutoEscape
	public String getPrimary_exchange();

	/**
	 * Sets the primary_exchange of this share.
	 *
	 * @param primary_exchange the primary_exchange of this share
	 */
	public void setPrimary_exchange(String primary_exchange);

	/**
	 * Returns the user created ID of this share.
	 *
	 * @return the user created ID of this share
	 */
	public long getUserCreatedId();

	/**
	 * Sets the user created ID of this share.
	 *
	 * @param userCreatedId the user created ID of this share
	 */
	public void setUserCreatedId(long userCreatedId);

	/**
	 * Returns the market ID of this share.
	 *
	 * @return the market ID of this share
	 */
	public long getMarketId();

	/**
	 * Sets the market ID of this share.
	 *
	 * @param marketId the market ID of this share
	 */
	public void setMarketId(long marketId);

	/**
	 * Returns the validated_trader_provider of this share.
	 *
	 * @return the validated_trader_provider of this share
	 */
	public boolean getValidated_trader_provider();

	/**
	 * Returns <code>true</code> if this share is validated_trader_provider.
	 *
	 * @return <code>true</code> if this share is validated_trader_provider; <code>false</code> otherwise
	 */
	public boolean isValidated_trader_provider();

	/**
	 * Sets whether this share is validated_trader_provider.
	 *
	 * @param validated_trader_provider the validated_trader_provider of this share
	 */
	public void setValidated_trader_provider(boolean validated_trader_provider);

	/**
	 * Returns the date_validated_trader_provider of this share.
	 *
	 * @return the date_validated_trader_provider of this share
	 */
	public Date getDate_validated_trader_provider();

	/**
	 * Sets the date_validated_trader_provider of this share.
	 *
	 * @param date_validated_trader_provider the date_validated_trader_provider of this share
	 */
	public void setDate_validated_trader_provider(
		Date date_validated_trader_provider);

	/**
	 * Returns the last_error_trader_provider of this share.
	 *
	 * @return the last_error_trader_provider of this share
	 */
	@AutoEscape
	public String getLast_error_trader_provider();

	/**
	 * Sets the last_error_trader_provider of this share.
	 *
	 * @param last_error_trader_provider the last_error_trader_provider of this share
	 */
	public void setLast_error_trader_provider(String last_error_trader_provider);

	/**
	 * Returns the simulation_end_date of this share.
	 *
	 * @return the simulation_end_date of this share
	 */
	public Date getSimulation_end_date();

	/**
	 * Sets the simulation_end_date of this share.
	 *
	 * @param simulation_end_date the simulation_end_date of this share
	 */
	public void setSimulation_end_date(Date simulation_end_date);

	/**
	 * Returns the trading_hours of this share.
	 *
	 * @return the trading_hours of this share
	 */
	@AutoEscape
	public String getTrading_hours();

	/**
	 * Sets the trading_hours of this share.
	 *
	 * @param trading_hours the trading_hours of this share
	 */
	public void setTrading_hours(String trading_hours);

	/**
	 * Returns the date_filled_realtime_gaps of this share.
	 *
	 * @return the date_filled_realtime_gaps of this share
	 */
	public Date getDate_filled_realtime_gaps();

	/**
	 * Sets the date_filled_realtime_gaps of this share.
	 *
	 * @param date_filled_realtime_gaps the date_filled_realtime_gaps of this share
	 */
	public void setDate_filled_realtime_gaps(Date date_filled_realtime_gaps);

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
	public int compareTo(Share share);

	@Override
	public int hashCode();

	@Override
	public CacheModel<Share> toCacheModel();

	@Override
	public Share toEscapedModel();

	@Override
	public Share toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}