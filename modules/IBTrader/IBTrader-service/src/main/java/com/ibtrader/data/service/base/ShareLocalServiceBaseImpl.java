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

import aQute.bnd.annotation.ProviderType;

import com.ibtrader.data.model.Share;
import com.ibtrader.data.service.ShareLocalService;
import com.ibtrader.data.service.persistence.AuditIndicatorsStrategyPersistence;
import com.ibtrader.data.service.persistence.BackTestingPersistence;
import com.ibtrader.data.service.persistence.ConfigPersistence;
import com.ibtrader.data.service.persistence.HistoricalRealtimeFinder;
import com.ibtrader.data.service.persistence.HistoricalRealtimePersistence;
import com.ibtrader.data.service.persistence.IBOrderFinder;
import com.ibtrader.data.service.persistence.IBOrderPersistence;
import com.ibtrader.data.service.persistence.MarketPersistence;
import com.ibtrader.data.service.persistence.PositionFinder;
import com.ibtrader.data.service.persistence.PositionPersistence;
import com.ibtrader.data.service.persistence.RealtimeFinder;
import com.ibtrader.data.service.persistence.RealtimePersistence;
import com.ibtrader.data.service.persistence.SharePersistence;
import com.ibtrader.data.service.persistence.StrategyPersistence;
import com.ibtrader.data.service.persistence.StrategySharePersistence;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the share local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.ibtrader.data.service.impl.ShareLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ibtrader.data.service.impl.ShareLocalServiceImpl
 * @see com.ibtrader.data.service.ShareLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class ShareLocalServiceBaseImpl extends BaseLocalServiceImpl
	implements ShareLocalService, IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.ibtrader.data.service.ShareLocalServiceUtil} to access the share local service.
	 */

	/**
	 * Adds the share to the database. Also notifies the appropriate model listeners.
	 *
	 * @param share the share
	 * @return the share that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Share addShare(Share share) {
		share.setNew(true);

		return sharePersistence.update(share);
	}

	/**
	 * Creates a new share with the primary key. Does not add the share to the database.
	 *
	 * @param shareId the primary key for the new share
	 * @return the new share
	 */
	@Override
	public Share createShare(long shareId) {
		return sharePersistence.create(shareId);
	}

	/**
	 * Deletes the share with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param shareId the primary key of the share
	 * @return the share that was removed
	 * @throws PortalException if a share with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Share deleteShare(long shareId) throws PortalException {
		return sharePersistence.remove(shareId);
	}

	/**
	 * Deletes the share from the database. Also notifies the appropriate model listeners.
	 *
	 * @param share the share
	 * @return the share that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Share deleteShare(Share share) {
		return sharePersistence.remove(share);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(Share.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return sharePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return sharePersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return sharePersistence.findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return sharePersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return sharePersistence.countWithDynamicQuery(dynamicQuery, projection);
	}

	@Override
	public Share fetchShare(long shareId) {
		return sharePersistence.fetchByPrimaryKey(shareId);
	}

	/**
	 * Returns the share matching the UUID and group.
	 *
	 * @param uuid the share's UUID
	 * @param groupId the primary key of the group
	 * @return the matching share, or <code>null</code> if a matching share could not be found
	 */
	@Override
	public Share fetchShareByUuidAndGroupId(String uuid, long groupId) {
		return sharePersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the share with the primary key.
	 *
	 * @param shareId the primary key of the share
	 * @return the share
	 * @throws PortalException if a share with the primary key could not be found
	 */
	@Override
	public Share getShare(long shareId) throws PortalException {
		return sharePersistence.findByPrimaryKey(shareId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(shareLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Share.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("shareId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(shareLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(Share.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("shareId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(shareLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Share.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("shareId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {
		final ExportActionableDynamicQuery exportActionableDynamicQuery = new ExportActionableDynamicQuery() {
				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary = portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(stagedModelType,
						modelAdditionCount);

					long modelDeletionCount = ExportImportHelperUtil.getModelDeletionCount(portletDataContext,
							stagedModelType);

					manifestSummary.addModelDeletionCount(stagedModelType,
						modelDeletionCount);

					return modelAdditionCount;
				}
			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(new ActionableDynamicQuery.AddCriteriaMethod() {
				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(dynamicQuery,
						"modifiedDate");
				}
			});

		exportActionableDynamicQuery.setCompanyId(portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Share>() {
				@Override
				public void performAction(Share share)
					throws PortalException {
					StagedModelDataHandlerUtil.exportStagedModel(portletDataContext,
						share);
				}
			});
		exportActionableDynamicQuery.setStagedModelType(new StagedModelType(
				PortalUtil.getClassNameId(Share.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return shareLocalService.deleteShare((Share)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return sharePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the shares matching the UUID and company.
	 *
	 * @param uuid the UUID of the shares
	 * @param companyId the primary key of the company
	 * @return the matching shares, or an empty list if no matches were found
	 */
	@Override
	public List<Share> getSharesByUuidAndCompanyId(String uuid, long companyId) {
		return sharePersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of shares matching the UUID and company.
	 *
	 * @param uuid the UUID of the shares
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of shares
	 * @param end the upper bound of the range of shares (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching shares, or an empty list if no matches were found
	 */
	@Override
	public List<Share> getSharesByUuidAndCompanyId(String uuid, long companyId,
		int start, int end, OrderByComparator<Share> orderByComparator) {
		return sharePersistence.findByUuid_C(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	 * Returns the share matching the UUID and group.
	 *
	 * @param uuid the share's UUID
	 * @param groupId the primary key of the group
	 * @return the matching share
	 * @throws PortalException if a matching share could not be found
	 */
	@Override
	public Share getShareByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {
		return sharePersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the shares.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of shares
	 * @param end the upper bound of the range of shares (not inclusive)
	 * @return the range of shares
	 */
	@Override
	public List<Share> getShares(int start, int end) {
		return sharePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of shares.
	 *
	 * @return the number of shares
	 */
	@Override
	public int getSharesCount() {
		return sharePersistence.countAll();
	}

	/**
	 * Updates the share in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param share the share
	 * @return the share that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Share updateShare(Share share) {
		return sharePersistence.update(share);
	}

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
	 * Returns the ib order finder.
	 *
	 * @return the ib order finder
	 */
	public IBOrderFinder getIBOrderFinder() {
		return ibOrderFinder;
	}

	/**
	 * Sets the ib order finder.
	 *
	 * @param ibOrderFinder the ib order finder
	 */
	public void setIBOrderFinder(IBOrderFinder ibOrderFinder) {
		this.ibOrderFinder = ibOrderFinder;
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
	public ShareLocalService getShareLocalService() {
		return shareLocalService;
	}

	/**
	 * Sets the share local service.
	 *
	 * @param shareLocalService the share local service
	 */
	public void setShareLocalService(ShareLocalService shareLocalService) {
		this.shareLocalService = shareLocalService;
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
		persistedModelLocalServiceRegistry.register("com.ibtrader.data.model.Share",
			shareLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.ibtrader.data.model.Share");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return ShareLocalService.class.getName();
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
	@BeanReference(type = AuditIndicatorsStrategyPersistence.class)
	protected AuditIndicatorsStrategyPersistence auditIndicatorsStrategyPersistence;
	@BeanReference(type = com.ibtrader.data.service.BackTestingLocalService.class)
	protected com.ibtrader.data.service.BackTestingLocalService backTestingLocalService;
	@BeanReference(type = BackTestingPersistence.class)
	protected BackTestingPersistence backTestingPersistence;
	@BeanReference(type = com.ibtrader.data.service.ConfigLocalService.class)
	protected com.ibtrader.data.service.ConfigLocalService configLocalService;
	@BeanReference(type = ConfigPersistence.class)
	protected ConfigPersistence configPersistence;
	@BeanReference(type = com.ibtrader.data.service.HistoricalRealtimeLocalService.class)
	protected com.ibtrader.data.service.HistoricalRealtimeLocalService historicalRealtimeLocalService;
	@BeanReference(type = HistoricalRealtimePersistence.class)
	protected HistoricalRealtimePersistence historicalRealtimePersistence;
	@BeanReference(type = HistoricalRealtimeFinder.class)
	protected HistoricalRealtimeFinder historicalRealtimeFinder;
	@BeanReference(type = com.ibtrader.data.service.IBOrderLocalService.class)
	protected com.ibtrader.data.service.IBOrderLocalService ibOrderLocalService;
	@BeanReference(type = IBOrderPersistence.class)
	protected IBOrderPersistence ibOrderPersistence;
	@BeanReference(type = IBOrderFinder.class)
	protected IBOrderFinder ibOrderFinder;
	@BeanReference(type = com.ibtrader.data.service.MarketLocalService.class)
	protected com.ibtrader.data.service.MarketLocalService marketLocalService;
	@BeanReference(type = MarketPersistence.class)
	protected MarketPersistence marketPersistence;
	@BeanReference(type = com.ibtrader.data.service.PositionLocalService.class)
	protected com.ibtrader.data.service.PositionLocalService positionLocalService;
	@BeanReference(type = PositionPersistence.class)
	protected PositionPersistence positionPersistence;
	@BeanReference(type = PositionFinder.class)
	protected PositionFinder positionFinder;
	@BeanReference(type = com.ibtrader.data.service.RealtimeLocalService.class)
	protected com.ibtrader.data.service.RealtimeLocalService realtimeLocalService;
	@BeanReference(type = RealtimePersistence.class)
	protected RealtimePersistence realtimePersistence;
	@BeanReference(type = RealtimeFinder.class)
	protected RealtimeFinder realtimeFinder;
	@BeanReference(type = ShareLocalService.class)
	protected ShareLocalService shareLocalService;
	@BeanReference(type = SharePersistence.class)
	protected SharePersistence sharePersistence;
	@BeanReference(type = com.ibtrader.data.service.StrategyLocalService.class)
	protected com.ibtrader.data.service.StrategyLocalService strategyLocalService;
	@BeanReference(type = StrategyPersistence.class)
	protected StrategyPersistence strategyPersistence;
	@BeanReference(type = com.ibtrader.data.service.StrategyShareLocalService.class)
	protected com.ibtrader.data.service.StrategyShareLocalService strategyShareLocalService;
	@BeanReference(type = StrategySharePersistence.class)
	protected StrategySharePersistence strategySharePersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}