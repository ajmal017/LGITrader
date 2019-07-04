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

package com.ibtrader.data.service.base;

import com.ibtrader.data.model.Share;
import com.ibtrader.data.service.ShareService;
import com.ibtrader.data.service.persistence.AuditIndicatorsStrategyPersistence;
import com.ibtrader.data.service.persistence.BackTestingPersistence;
import com.ibtrader.data.service.persistence.ConfigPersistence;
import com.ibtrader.data.service.persistence.HistoricalRealtimeFinder;
import com.ibtrader.data.service.persistence.HistoricalRealtimePersistence;
import com.ibtrader.data.service.persistence.IBOrderPersistence;
import com.ibtrader.data.service.persistence.MarketPersistence;
import com.ibtrader.data.service.persistence.PositionFinder;
import com.ibtrader.data.service.persistence.PositionPersistence;
import com.ibtrader.data.service.persistence.RealtimeFinder;
import com.ibtrader.data.service.persistence.RealtimePersistence;
import com.ibtrader.data.service.persistence.SharePersistence;
import com.ibtrader.data.service.persistence.StrategyPersistence;
import com.ibtrader.data.service.persistence.StrategySharePersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the share remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.ibtrader.data.service.impl.ShareServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ibtrader.data.service.impl.ShareServiceImpl
 * @see com.ibtrader.data.service.ShareServiceUtil
 * @generated
 */
public abstract class ShareServiceBaseImpl extends BaseServiceImpl
	implements ShareService, IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.ibtrader.data.service.ShareServiceUtil} to access the share remote service.
	 */

	/**
	 * Returns the audit indicators strategy local service.
	 *
	 * @return the audit indicators strategy local service
	 */
	public com.ibtrader.data.service.AuditIndicatorsStrategyLocalService getAuditIndicatorsStrategyLocalService() {
		return auditIndicatorsStrategyLocalService;
	}

	/**
	 * Sets the audit indicators strategy local service.
	 *
	 * @param auditIndicatorsStrategyLocalService the audit indicators strategy local service
	 */
	public void setAuditIndicatorsStrategyLocalService(
		com.ibtrader.data.service.AuditIndicatorsStrategyLocalService auditIndicatorsStrategyLocalService) {
		this.auditIndicatorsStrategyLocalService = auditIndicatorsStrategyLocalService;
	}

	/**
	 * Returns the audit indicators strategy remote service.
	 *
	 * @return the audit indicators strategy remote service
	 */
	public com.ibtrader.data.service.AuditIndicatorsStrategyService getAuditIndicatorsStrategyService() {
		return auditIndicatorsStrategyService;
	}

	/**
	 * Sets the audit indicators strategy remote service.
	 *
	 * @param auditIndicatorsStrategyService the audit indicators strategy remote service
	 */
	public void setAuditIndicatorsStrategyService(
		com.ibtrader.data.service.AuditIndicatorsStrategyService auditIndicatorsStrategyService) {
		this.auditIndicatorsStrategyService = auditIndicatorsStrategyService;
	}

	/**
	 * Returns the audit indicators strategy persistence.
	 *
	 * @return the audit indicators strategy persistence
	 */
	public AuditIndicatorsStrategyPersistence getAuditIndicatorsStrategyPersistence() {
		return auditIndicatorsStrategyPersistence;
	}

	/**
	 * Sets the audit indicators strategy persistence.
	 *
	 * @param auditIndicatorsStrategyPersistence the audit indicators strategy persistence
	 */
	public void setAuditIndicatorsStrategyPersistence(
		AuditIndicatorsStrategyPersistence auditIndicatorsStrategyPersistence) {
		this.auditIndicatorsStrategyPersistence = auditIndicatorsStrategyPersistence;
	}

	/**
	 * Returns the back testing local service.
	 *
	 * @return the back testing local service
	 */
	public com.ibtrader.data.service.BackTestingLocalService getBackTestingLocalService() {
		return backTestingLocalService;
	}

	/**
	 * Sets the back testing local service.
	 *
	 * @param backTestingLocalService the back testing local service
	 */
	public void setBackTestingLocalService(
		com.ibtrader.data.service.BackTestingLocalService backTestingLocalService) {
		this.backTestingLocalService = backTestingLocalService;
	}

	/**
	 * Returns the back testing remote service.
	 *
	 * @return the back testing remote service
	 */
	public com.ibtrader.data.service.BackTestingService getBackTestingService() {
		return backTestingService;
	}

	/**
	 * Sets the back testing remote service.
	 *
	 * @param backTestingService the back testing remote service
	 */
	public void setBackTestingService(
		com.ibtrader.data.service.BackTestingService backTestingService) {
		this.backTestingService = backTestingService;
	}

	/**
	 * Returns the back testing persistence.
	 *
	 * @return the back testing persistence
	 */
	public BackTestingPersistence getBackTestingPersistence() {
		return backTestingPersistence;
	}

	/**
	 * Sets the back testing persistence.
	 *
	 * @param backTestingPersistence the back testing persistence
	 */
	public void setBackTestingPersistence(
		BackTestingPersistence backTestingPersistence) {
		this.backTestingPersistence = backTestingPersistence;
	}

	/**
	 * Returns the config local service.
	 *
	 * @return the config local service
	 */
	public com.ibtrader.data.service.ConfigLocalService getConfigLocalService() {
		return configLocalService;
	}

	/**
	 * Sets the config local service.
	 *
	 * @param configLocalService the config local service
	 */
	public void setConfigLocalService(
		com.ibtrader.data.service.ConfigLocalService configLocalService) {
		this.configLocalService = configLocalService;
	}

	/**
	 * Returns the config remote service.
	 *
	 * @return the config remote service
	 */
	public com.ibtrader.data.service.ConfigService getConfigService() {
		return configService;
	}

	/**
	 * Sets the config remote service.
	 *
	 * @param configService the config remote service
	 */
	public void setConfigService(
		com.ibtrader.data.service.ConfigService configService) {
		this.configService = configService;
	}

	/**
	 * Returns the config persistence.
	 *
	 * @return the config persistence
	 */
	public ConfigPersistence getConfigPersistence() {
		return configPersistence;
	}

	/**
	 * Sets the config persistence.
	 *
	 * @param configPersistence the config persistence
	 */
	public void setConfigPersistence(ConfigPersistence configPersistence) {
		this.configPersistence = configPersistence;
	}

	/**
	 * Returns the historical realtime local service.
	 *
	 * @return the historical realtime local service
	 */
	public com.ibtrader.data.service.HistoricalRealtimeLocalService getHistoricalRealtimeLocalService() {
		return historicalRealtimeLocalService;
	}

	/**
	 * Sets the historical realtime local service.
	 *
	 * @param historicalRealtimeLocalService the historical realtime local service
	 */
	public void setHistoricalRealtimeLocalService(
		com.ibtrader.data.service.HistoricalRealtimeLocalService historicalRealtimeLocalService) {
		this.historicalRealtimeLocalService = historicalRealtimeLocalService;
	}

	/**
	 * Returns the historical realtime remote service.
	 *
	 * @return the historical realtime remote service
	 */
	public com.ibtrader.data.service.HistoricalRealtimeService getHistoricalRealtimeService() {
		return historicalRealtimeService;
	}

	/**
	 * Sets the historical realtime remote service.
	 *
	 * @param historicalRealtimeService the historical realtime remote service
	 */
	public void setHistoricalRealtimeService(
		com.ibtrader.data.service.HistoricalRealtimeService historicalRealtimeService) {
		this.historicalRealtimeService = historicalRealtimeService;
	}

	/**
	 * Returns the historical realtime persistence.
	 *
	 * @return the historical realtime persistence
	 */
	public HistoricalRealtimePersistence getHistoricalRealtimePersistence() {
		return historicalRealtimePersistence;
	}

	/**
	 * Sets the historical realtime persistence.
	 *
	 * @param historicalRealtimePersistence the historical realtime persistence
	 */
	public void setHistoricalRealtimePersistence(
		HistoricalRealtimePersistence historicalRealtimePersistence) {
		this.historicalRealtimePersistence = historicalRealtimePersistence;
	}

	/**
	 * Returns the historical realtime finder.
	 *
	 * @return the historical realtime finder
	 */
	public HistoricalRealtimeFinder getHistoricalRealtimeFinder() {
		return historicalRealtimeFinder;
	}

	/**
	 * Sets the historical realtime finder.
	 *
	 * @param historicalRealtimeFinder the historical realtime finder
	 */
	public void setHistoricalRealtimeFinder(
		HistoricalRealtimeFinder historicalRealtimeFinder) {
		this.historicalRealtimeFinder = historicalRealtimeFinder;
	}

	/**
	 * Returns the ib order local service.
	 *
	 * @return the ib order local service
	 */
	public com.ibtrader.data.service.IBOrderLocalService getIBOrderLocalService() {
		return ibOrderLocalService;
	}

	/**
	 * Sets the ib order local service.
	 *
	 * @param ibOrderLocalService the ib order local service
	 */
	public void setIBOrderLocalService(
		com.ibtrader.data.service.IBOrderLocalService ibOrderLocalService) {
		this.ibOrderLocalService = ibOrderLocalService;
	}

	/**
	 * Returns the ib order remote service.
	 *
	 * @return the ib order remote service
	 */
	public com.ibtrader.data.service.IBOrderService getIBOrderService() {
		return ibOrderService;
	}

	/**
	 * Sets the ib order remote service.
	 *
	 * @param ibOrderService the ib order remote service
	 */
	public void setIBOrderService(
		com.ibtrader.data.service.IBOrderService ibOrderService) {
		this.ibOrderService = ibOrderService;
	}

	/**
	 * Returns the ib order persistence.
	 *
	 * @return the ib order persistence
	 */
	public IBOrderPersistence getIBOrderPersistence() {
		return ibOrderPersistence;
	}

	/**
	 * Sets the ib order persistence.
	 *
	 * @param ibOrderPersistence the ib order persistence
	 */
	public void setIBOrderPersistence(IBOrderPersistence ibOrderPersistence) {
		this.ibOrderPersistence = ibOrderPersistence;
	}

	/**
	 * Returns the market local service.
	 *
	 * @return the market local service
	 */
	public com.ibtrader.data.service.MarketLocalService getMarketLocalService() {
		return marketLocalService;
	}

	/**
	 * Sets the market local service.
	 *
	 * @param marketLocalService the market local service
	 */
	public void setMarketLocalService(
		com.ibtrader.data.service.MarketLocalService marketLocalService) {
		this.marketLocalService = marketLocalService;
	}

	/**
	 * Returns the market remote service.
	 *
	 * @return the market remote service
	 */
	public com.ibtrader.data.service.MarketService getMarketService() {
		return marketService;
	}

	/**
	 * Sets the market remote service.
	 *
	 * @param marketService the market remote service
	 */
	public void setMarketService(
		com.ibtrader.data.service.MarketService marketService) {
		this.marketService = marketService;
	}

	/**
	 * Returns the market persistence.
	 *
	 * @return the market persistence
	 */
	public MarketPersistence getMarketPersistence() {
		return marketPersistence;
	}

	/**
	 * Sets the market persistence.
	 *
	 * @param marketPersistence the market persistence
	 */
	public void setMarketPersistence(MarketPersistence marketPersistence) {
		this.marketPersistence = marketPersistence;
	}

	/**
	 * Returns the position local service.
	 *
	 * @return the position local service
	 */
	public com.ibtrader.data.service.PositionLocalService getPositionLocalService() {
		return positionLocalService;
	}

	/**
	 * Sets the position local service.
	 *
	 * @param positionLocalService the position local service
	 */
	public void setPositionLocalService(
		com.ibtrader.data.service.PositionLocalService positionLocalService) {
		this.positionLocalService = positionLocalService;
	}

	/**
	 * Returns the position remote service.
	 *
	 * @return the position remote service
	 */
	public com.ibtrader.data.service.PositionService getPositionService() {
		return positionService;
	}

	/**
	 * Sets the position remote service.
	 *
	 * @param positionService the position remote service
	 */
	public void setPositionService(
		com.ibtrader.data.service.PositionService positionService) {
		this.positionService = positionService;
	}

	/**
	 * Returns the position persistence.
	 *
	 * @return the position persistence
	 */
	public PositionPersistence getPositionPersistence() {
		return positionPersistence;
	}

	/**
	 * Sets the position persistence.
	 *
	 * @param positionPersistence the position persistence
	 */
	public void setPositionPersistence(PositionPersistence positionPersistence) {
		this.positionPersistence = positionPersistence;
	}

	/**
	 * Returns the position finder.
	 *
	 * @return the position finder
	 */
	public PositionFinder getPositionFinder() {
		return positionFinder;
	}

	/**
	 * Sets the position finder.
	 *
	 * @param positionFinder the position finder
	 */
	public void setPositionFinder(PositionFinder positionFinder) {
		this.positionFinder = positionFinder;
	}

	/**
	 * Returns the realtime local service.
	 *
	 * @return the realtime local service
	 */
	public com.ibtrader.data.service.RealtimeLocalService getRealtimeLocalService() {
		return realtimeLocalService;
	}

	/**
	 * Sets the realtime local service.
	 *
	 * @param realtimeLocalService the realtime local service
	 */
	public void setRealtimeLocalService(
		com.ibtrader.data.service.RealtimeLocalService realtimeLocalService) {
		this.realtimeLocalService = realtimeLocalService;
	}

	/**
	 * Returns the realtime remote service.
	 *
	 * @return the realtime remote service
	 */
	public com.ibtrader.data.service.RealtimeService getRealtimeService() {
		return realtimeService;
	}

	/**
	 * Sets the realtime remote service.
	 *
	 * @param realtimeService the realtime remote service
	 */
	public void setRealtimeService(
		com.ibtrader.data.service.RealtimeService realtimeService) {
		this.realtimeService = realtimeService;
	}

	/**
	 * Returns the realtime persistence.
	 *
	 * @return the realtime persistence
	 */
	public RealtimePersistence getRealtimePersistence() {
		return realtimePersistence;
	}

	/**
	 * Sets the realtime persistence.
	 *
	 * @param realtimePersistence the realtime persistence
	 */
	public void setRealtimePersistence(RealtimePersistence realtimePersistence) {
		this.realtimePersistence = realtimePersistence;
	}

	/**
	 * Returns the realtime finder.
	 *
	 * @return the realtime finder
	 */
	public RealtimeFinder getRealtimeFinder() {
		return realtimeFinder;
	}

	/**
	 * Sets the realtime finder.
	 *
	 * @param realtimeFinder the realtime finder
	 */
	public void setRealtimeFinder(RealtimeFinder realtimeFinder) {
		this.realtimeFinder = realtimeFinder;
	}

	/**
	 * Returns the share local service.
	 *
	 * @return the share local service
	 */
	public com.ibtrader.data.service.ShareLocalService getShareLocalService() {
		return shareLocalService;
	}

	/**
	 * Sets the share local service.
	 *
	 * @param shareLocalService the share local service
	 */
	public void setShareLocalService(
		com.ibtrader.data.service.ShareLocalService shareLocalService) {
		this.shareLocalService = shareLocalService;
	}

	/**
	 * Returns the share remote service.
	 *
	 * @return the share remote service
	 */
	public ShareService getShareService() {
		return shareService;
	}

	/**
	 * Sets the share remote service.
	 *
	 * @param shareService the share remote service
	 */
	public void setShareService(ShareService shareService) {
		this.shareService = shareService;
	}

	/**
	 * Returns the share persistence.
	 *
	 * @return the share persistence
	 */
	public SharePersistence getSharePersistence() {
		return sharePersistence;
	}

	/**
	 * Sets the share persistence.
	 *
	 * @param sharePersistence the share persistence
	 */
	public void setSharePersistence(SharePersistence sharePersistence) {
		this.sharePersistence = sharePersistence;
	}

	/**
	 * Returns the strategy local service.
	 *
	 * @return the strategy local service
	 */
	public com.ibtrader.data.service.StrategyLocalService getStrategyLocalService() {
		return strategyLocalService;
	}

	/**
	 * Sets the strategy local service.
	 *
	 * @param strategyLocalService the strategy local service
	 */
	public void setStrategyLocalService(
		com.ibtrader.data.service.StrategyLocalService strategyLocalService) {
		this.strategyLocalService = strategyLocalService;
	}

	/**
	 * Returns the strategy remote service.
	 *
	 * @return the strategy remote service
	 */
	public com.ibtrader.data.service.StrategyService getStrategyService() {
		return strategyService;
	}

	/**
	 * Sets the strategy remote service.
	 *
	 * @param strategyService the strategy remote service
	 */
	public void setStrategyService(
		com.ibtrader.data.service.StrategyService strategyService) {
		this.strategyService = strategyService;
	}

	/**
	 * Returns the strategy persistence.
	 *
	 * @return the strategy persistence
	 */
	public StrategyPersistence getStrategyPersistence() {
		return strategyPersistence;
	}

	/**
	 * Sets the strategy persistence.
	 *
	 * @param strategyPersistence the strategy persistence
	 */
	public void setStrategyPersistence(StrategyPersistence strategyPersistence) {
		this.strategyPersistence = strategyPersistence;
	}

	/**
	 * Returns the strategy share local service.
	 *
	 * @return the strategy share local service
	 */
	public com.ibtrader.data.service.StrategyShareLocalService getStrategyShareLocalService() {
		return strategyShareLocalService;
	}

	/**
	 * Sets the strategy share local service.
	 *
	 * @param strategyShareLocalService the strategy share local service
	 */
	public void setStrategyShareLocalService(
		com.ibtrader.data.service.StrategyShareLocalService strategyShareLocalService) {
		this.strategyShareLocalService = strategyShareLocalService;
	}

	/**
	 * Returns the strategy share remote service.
	 *
	 * @return the strategy share remote service
	 */
	public com.ibtrader.data.service.StrategyShareService getStrategyShareService() {
		return strategyShareService;
	}

	/**
	 * Sets the strategy share remote service.
	 *
	 * @param strategyShareService the strategy share remote service
	 */
	public void setStrategyShareService(
		com.ibtrader.data.service.StrategyShareService strategyShareService) {
		this.strategyShareService = strategyShareService;
	}

	/**
	 * Returns the strategy share persistence.
	 *
	 * @return the strategy share persistence
	 */
	public StrategySharePersistence getStrategySharePersistence() {
		return strategySharePersistence;
	}

	/**
	 * Sets the strategy share persistence.
	 *
	 * @param strategySharePersistence the strategy share persistence
	 */
	public void setStrategySharePersistence(
		StrategySharePersistence strategySharePersistence) {
		this.strategySharePersistence = strategySharePersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name remote service.
	 *
	 * @return the class name remote service
	 */
	public com.liferay.portal.kernel.service.ClassNameService getClassNameService() {
		return classNameService;
	}

	/**
	 * Sets the class name remote service.
	 *
	 * @param classNameService the class name remote service
	 */
	public void setClassNameService(
		com.liferay.portal.kernel.service.ClassNameService classNameService) {
		this.classNameService = classNameService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.kernel.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.kernel.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
	}

	public void destroy() {
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return ShareService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Share.class;
	}

	protected String getModelClassName() {
		return Share.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = sharePersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.ibtrader.data.service.AuditIndicatorsStrategyLocalService.class)
	protected com.ibtrader.data.service.AuditIndicatorsStrategyLocalService auditIndicatorsStrategyLocalService;
	@BeanReference(type = com.ibtrader.data.service.AuditIndicatorsStrategyService.class)
	protected com.ibtrader.data.service.AuditIndicatorsStrategyService auditIndicatorsStrategyService;
	@BeanReference(type = AuditIndicatorsStrategyPersistence.class)
	protected AuditIndicatorsStrategyPersistence auditIndicatorsStrategyPersistence;
	@BeanReference(type = com.ibtrader.data.service.BackTestingLocalService.class)
	protected com.ibtrader.data.service.BackTestingLocalService backTestingLocalService;
	@BeanReference(type = com.ibtrader.data.service.BackTestingService.class)
	protected com.ibtrader.data.service.BackTestingService backTestingService;
	@BeanReference(type = BackTestingPersistence.class)
	protected BackTestingPersistence backTestingPersistence;
	@BeanReference(type = com.ibtrader.data.service.ConfigLocalService.class)
	protected com.ibtrader.data.service.ConfigLocalService configLocalService;
	@BeanReference(type = com.ibtrader.data.service.ConfigService.class)
	protected com.ibtrader.data.service.ConfigService configService;
	@BeanReference(type = ConfigPersistence.class)
	protected ConfigPersistence configPersistence;
	@BeanReference(type = com.ibtrader.data.service.HistoricalRealtimeLocalService.class)
	protected com.ibtrader.data.service.HistoricalRealtimeLocalService historicalRealtimeLocalService;
	@BeanReference(type = com.ibtrader.data.service.HistoricalRealtimeService.class)
	protected com.ibtrader.data.service.HistoricalRealtimeService historicalRealtimeService;
	@BeanReference(type = HistoricalRealtimePersistence.class)
	protected HistoricalRealtimePersistence historicalRealtimePersistence;
	@BeanReference(type = HistoricalRealtimeFinder.class)
	protected HistoricalRealtimeFinder historicalRealtimeFinder;
	@BeanReference(type = com.ibtrader.data.service.IBOrderLocalService.class)
	protected com.ibtrader.data.service.IBOrderLocalService ibOrderLocalService;
	@BeanReference(type = com.ibtrader.data.service.IBOrderService.class)
	protected com.ibtrader.data.service.IBOrderService ibOrderService;
	@BeanReference(type = IBOrderPersistence.class)
	protected IBOrderPersistence ibOrderPersistence;
	@BeanReference(type = com.ibtrader.data.service.MarketLocalService.class)
	protected com.ibtrader.data.service.MarketLocalService marketLocalService;
	@BeanReference(type = com.ibtrader.data.service.MarketService.class)
	protected com.ibtrader.data.service.MarketService marketService;
	@BeanReference(type = MarketPersistence.class)
	protected MarketPersistence marketPersistence;
	@BeanReference(type = com.ibtrader.data.service.PositionLocalService.class)
	protected com.ibtrader.data.service.PositionLocalService positionLocalService;
	@BeanReference(type = com.ibtrader.data.service.PositionService.class)
	protected com.ibtrader.data.service.PositionService positionService;
	@BeanReference(type = PositionPersistence.class)
	protected PositionPersistence positionPersistence;
	@BeanReference(type = PositionFinder.class)
	protected PositionFinder positionFinder;
	@BeanReference(type = com.ibtrader.data.service.RealtimeLocalService.class)
	protected com.ibtrader.data.service.RealtimeLocalService realtimeLocalService;
	@BeanReference(type = com.ibtrader.data.service.RealtimeService.class)
	protected com.ibtrader.data.service.RealtimeService realtimeService;
	@BeanReference(type = RealtimePersistence.class)
	protected RealtimePersistence realtimePersistence;
	@BeanReference(type = RealtimeFinder.class)
	protected RealtimeFinder realtimeFinder;
	@BeanReference(type = com.ibtrader.data.service.ShareLocalService.class)
	protected com.ibtrader.data.service.ShareLocalService shareLocalService;
	@BeanReference(type = ShareService.class)
	protected ShareService shareService;
	@BeanReference(type = SharePersistence.class)
	protected SharePersistence sharePersistence;
	@BeanReference(type = com.ibtrader.data.service.StrategyLocalService.class)
	protected com.ibtrader.data.service.StrategyLocalService strategyLocalService;
	@BeanReference(type = com.ibtrader.data.service.StrategyService.class)
	protected com.ibtrader.data.service.StrategyService strategyService;
	@BeanReference(type = StrategyPersistence.class)
	protected StrategyPersistence strategyPersistence;
	@BeanReference(type = com.ibtrader.data.service.StrategyShareLocalService.class)
	protected com.ibtrader.data.service.StrategyShareLocalService strategyShareLocalService;
	@BeanReference(type = com.ibtrader.data.service.StrategyShareService.class)
	protected com.ibtrader.data.service.StrategyShareService strategyShareService;
	@BeanReference(type = StrategySharePersistence.class)
	protected StrategySharePersistence strategySharePersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameService.class)
	protected com.liferay.portal.kernel.service.ClassNameService classNameService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserService.class)
	protected com.liferay.portal.kernel.service.UserService userService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
}