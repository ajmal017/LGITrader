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

import com.ibtrader.data.model.BackTesting;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for BackTesting. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see BackTestingLocalServiceUtil
 * @see com.ibtrader.data.service.base.BackTestingLocalServiceBaseImpl
 * @see com.ibtrader.data.service.impl.BackTestingLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface BackTestingLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BackTestingLocalServiceUtil} to access the back testing local service. Add custom service methods to {@link com.ibtrader.data.service.impl.BackTestingLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public boolean removeBackTestingId(long companyId, long groupId,
		long backtestingId);

	/**
	* Adds the back testing to the database. Also notifies the appropriate model listeners.
	*
	* @param backTesting the back testing
	* @return the back testing that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public BackTesting addBackTesting(BackTesting backTesting);

	/**
	* Creates a new back testing with the primary key. Does not add the back testing to the database.
	*
	* @param backTId the primary key for the new back testing
	* @return the new back testing
	*/
	public BackTesting createBackTesting(long backTId);

	/**
	* Deletes the back testing from the database. Also notifies the appropriate model listeners.
	*
	* @param backTesting the back testing
	* @return the back testing that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public BackTesting deleteBackTesting(BackTesting backTesting);

	/**
	* Deletes the back testing with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param backTId the primary key of the back testing
	* @return the back testing that was removed
	* @throws PortalException if a back testing with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public BackTesting deleteBackTesting(long backTId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BackTesting fetchBackTesting(long backTId);

	/**
	* Returns the back testing matching the UUID and group.
	*
	* @param uuid the back testing's UUID
	* @param groupId the primary key of the group
	* @return the matching back testing, or <code>null</code> if a matching back testing could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BackTesting fetchBackTestingByUuidAndGroupId(java.lang.String uuid,
		long groupId);

	/**
	* Returns the back testing with the primary key.
	*
	* @param backTId the primary key of the back testing
	* @return the back testing
	* @throws PortalException if a back testing with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BackTesting getBackTesting(long backTId) throws PortalException;

	/**
	* Returns the back testing matching the UUID and group.
	*
	* @param uuid the back testing's UUID
	* @param groupId the primary key of the group
	* @return the matching back testing
	* @throws PortalException if a matching back testing could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BackTesting getBackTestingByUuidAndGroupId(java.lang.String uuid,
		long groupId) throws PortalException;

	/**
	* Updates the back testing in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param backTesting the back testing
	* @return the back testing that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public BackTesting updateBackTesting(BackTesting backTesting);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the number of back testings.
	*
	* @return the number of back testings
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getBackTestingsCount();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	public List<BackTesting> findByPendingCompanyGroup(long companyId,
		long groupId);

	public List<BackTesting> findByShareCompanyGroup(long shareId,
		long companyId, long groupId);

	/**
	* Returns a range of all the back testings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of back testings
	* @param end the upper bound of the range of back testings (not inclusive)
	* @return the range of back testings
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BackTesting> getBackTestings(int start, int end);

	/**
	* Returns all the back testings matching the UUID and company.
	*
	* @param uuid the UUID of the back testings
	* @param companyId the primary key of the company
	* @return the matching back testings, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BackTesting> getBackTestingsByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of back testings matching the UUID and company.
	*
	* @param uuid the UUID of the back testings
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of back testings
	* @param end the upper bound of the range of back testings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching back testings, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BackTesting> getBackTestingsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<BackTesting> orderByComparator);

	public long countBackTestingShareStatus(long shareId, long companyId,
		long groupId, java.lang.String status);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);
}