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

package com.ibtrader.data.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for StrategyShare. This utility wraps
 * {@link com.ibtrader.data.service.impl.StrategyShareLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see StrategyShareLocalService
 * @see com.ibtrader.data.service.base.StrategyShareLocalServiceBaseImpl
 * @see com.ibtrader.data.service.impl.StrategyShareLocalServiceImpl
 * @generated
 */
@ProviderType
public class StrategyShareLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.ibtrader.data.service.impl.StrategyShareLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the strategy share to the database. Also notifies the appropriate model listeners.
	*
	* @param strategyShare the strategy share
	* @return the strategy share that was added
	*/
	public static com.ibtrader.data.model.StrategyShare addStrategyShare(
		com.ibtrader.data.model.StrategyShare strategyShare) {
		return getService().addStrategyShare(strategyShare);
	}

	/**
	* Creates a new strategy share with the primary key. Does not add the strategy share to the database.
	*
	* @param strategyshareId the primary key for the new strategy share
	* @return the new strategy share
	*/
	public static com.ibtrader.data.model.StrategyShare createStrategyShare(
		long strategyshareId) {
		return getService().createStrategyShare(strategyshareId);
	}

	/**
	* Deletes the strategy share from the database. Also notifies the appropriate model listeners.
	*
	* @param strategyShare the strategy share
	* @return the strategy share that was removed
	*/
	public static com.ibtrader.data.model.StrategyShare deleteStrategyShare(
		com.ibtrader.data.model.StrategyShare strategyShare) {
		return getService().deleteStrategyShare(strategyShare);
	}

	/**
	* Deletes the strategy share with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param strategyshareId the primary key of the strategy share
	* @return the strategy share that was removed
	* @throws PortalException if a strategy share with the primary key could not be found
	*/
	public static com.ibtrader.data.model.StrategyShare deleteStrategyShare(
		long strategyshareId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteStrategyShare(strategyshareId);
	}

	public static com.ibtrader.data.model.StrategyShare fetchStrategyShare(
		long strategyshareId) {
		return getService().fetchStrategyShare(strategyshareId);
	}

	/**
	* Returns the strategy share matching the UUID and group.
	*
	* @param uuid the strategy share's UUID
	* @param groupId the primary key of the group
	* @return the matching strategy share, or <code>null</code> if a matching strategy share could not be found
	*/
	public static com.ibtrader.data.model.StrategyShare fetchStrategyShareByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchStrategyShareByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the strategy share with the primary key.
	*
	* @param strategyshareId the primary key of the strategy share
	* @return the strategy share
	* @throws PortalException if a strategy share with the primary key could not be found
	*/
	public static com.ibtrader.data.model.StrategyShare getStrategyShare(
		long strategyshareId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getStrategyShare(strategyshareId);
	}

	/**
	* Returns the strategy share matching the UUID and group.
	*
	* @param uuid the strategy share's UUID
	* @param groupId the primary key of the group
	* @return the matching strategy share
	* @throws PortalException if a matching strategy share could not be found
	*/
	public static com.ibtrader.data.model.StrategyShare getStrategyShareByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getStrategyShareByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the strategy share in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param strategyShare the strategy share
	* @return the strategy share that was updated
	*/
	public static com.ibtrader.data.model.StrategyShare updateStrategyShare(
		com.ibtrader.data.model.StrategyShare strategyShare) {
		return getService().updateStrategyShare(strategyShare);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of strategy shares.
	*
	* @return the number of strategy shares
	*/
	public static int getStrategySharesCount() {
		return getService().getStrategySharesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public static java.util.List<com.ibtrader.data.model.StrategyShare> getByGroupCompanyShareId(
		long groupid, long companyid, long shareId) {
		return getService().getByGroupCompanyShareId(groupid, companyid, shareId);
	}

	/**
	* Returns a range of all the strategy shares.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of strategy shares
	* @param end the upper bound of the range of strategy shares (not inclusive)
	* @return the range of strategy shares
	*/
	public static java.util.List<com.ibtrader.data.model.StrategyShare> getStrategyShares(
		int start, int end) {
		return getService().getStrategyShares(start, end);
	}

	/**
	* Returns all the strategy shares matching the UUID and company.
	*
	* @param uuid the UUID of the strategy shares
	* @param companyId the primary key of the company
	* @return the matching strategy shares, or an empty list if no matches were found
	*/
	public static java.util.List<com.ibtrader.data.model.StrategyShare> getStrategySharesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getStrategySharesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of strategy shares matching the UUID and company.
	*
	* @param uuid the UUID of the strategy shares
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of strategy shares
	* @param end the upper bound of the range of strategy shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching strategy shares, or an empty list if no matches were found
	*/
	public static java.util.List<com.ibtrader.data.model.StrategyShare> getStrategySharesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ibtrader.data.model.StrategyShare> orderByComparator) {
		return getService()
				   .getStrategySharesByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static StrategyShareLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StrategyShareLocalService, StrategyShareLocalService> _serviceTracker =
		ServiceTrackerFactory.open(StrategyShareLocalService.class);
}