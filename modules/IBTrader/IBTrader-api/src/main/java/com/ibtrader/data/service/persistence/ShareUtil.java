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

package com.ibtrader.data.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.ibtrader.data.model.Share;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the share service. This utility wraps {@link com.ibtrader.data.service.persistence.impl.SharePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SharePersistence
 * @see com.ibtrader.data.service.persistence.impl.SharePersistenceImpl
 * @generated
 */
@ProviderType
public class ShareUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Share share) {
		getPersistence().clearCache(share);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Share> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Share> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Share> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<Share> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Share update(Share share) {
		return getPersistence().update(share);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Share update(Share share, ServiceContext serviceContext) {
		return getPersistence().update(share, serviceContext);
	}

	/**
	* Returns all the shares where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching shares
	*/
	public static List<Share> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the shares where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @return the range of matching shares
	*/
	public static List<Share> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the shares where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching shares
	*/
	public static List<Share> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Share> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the shares where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching shares
	*/
	public static List<Share> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Share> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first share in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching share
	* @throws NoSuchShareException if a matching share could not be found
	*/
	public static Share findByUuid_First(java.lang.String uuid,
		OrderByComparator<Share> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchShareException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first share in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching share, or <code>null</code> if a matching share could not be found
	*/
	public static Share fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<Share> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last share in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching share
	* @throws NoSuchShareException if a matching share could not be found
	*/
	public static Share findByUuid_Last(java.lang.String uuid,
		OrderByComparator<Share> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchShareException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last share in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching share, or <code>null</code> if a matching share could not be found
	*/
	public static Share fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<Share> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the shares before and after the current share in the ordered set where uuid = &#63;.
	*
	* @param shareId the primary key of the current share
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next share
	* @throws NoSuchShareException if a share with the primary key could not be found
	*/
	public static Share[] findByUuid_PrevAndNext(long shareId,
		java.lang.String uuid, OrderByComparator<Share> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchShareException {
		return getPersistence()
				   .findByUuid_PrevAndNext(shareId, uuid, orderByComparator);
	}

	/**
	* Removes all the shares where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of shares where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching shares
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the share where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchShareException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching share
	* @throws NoSuchShareException if a matching share could not be found
	*/
	public static Share findByUUID_G(java.lang.String uuid, long groupId)
		throws com.ibtrader.data.exception.NoSuchShareException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the share where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching share, or <code>null</code> if a matching share could not be found
	*/
	public static Share fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the share where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching share, or <code>null</code> if a matching share could not be found
	*/
	public static Share fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the share where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the share that was removed
	*/
	public static Share removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.ibtrader.data.exception.NoSuchShareException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of shares where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching shares
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the shares where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching shares
	*/
	public static List<Share> findByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the shares where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @return the range of matching shares
	*/
	public static List<Share> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the shares where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching shares
	*/
	public static List<Share> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Share> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the shares where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching shares
	*/
	public static List<Share> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Share> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first share in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching share
	* @throws NoSuchShareException if a matching share could not be found
	*/
	public static Share findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Share> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchShareException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first share in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching share, or <code>null</code> if a matching share could not be found
	*/
	public static Share fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Share> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last share in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching share
	* @throws NoSuchShareException if a matching share could not be found
	*/
	public static Share findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Share> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchShareException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last share in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching share, or <code>null</code> if a matching share could not be found
	*/
	public static Share fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Share> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the shares before and after the current share in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param shareId the primary key of the current share
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next share
	* @throws NoSuchShareException if a share with the primary key could not be found
	*/
	public static Share[] findByUuid_C_PrevAndNext(long shareId,
		java.lang.String uuid, long companyId,
		OrderByComparator<Share> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchShareException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(shareId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the shares where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of shares where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching shares
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the shares where companyId = &#63; and groupId = &#63; and shareId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param shareId the share ID
	* @return the matching shares
	*/
	public static List<Share> findByCompanyGroupShare(long companyId,
		long groupId, long shareId) {
		return getPersistence()
				   .findByCompanyGroupShare(companyId, groupId, shareId);
	}

	/**
	* Returns a range of all the shares where companyId = &#63; and groupId = &#63; and shareId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param shareId the share ID
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @return the range of matching shares
	*/
	public static List<Share> findByCompanyGroupShare(long companyId,
		long groupId, long shareId, int start, int end) {
		return getPersistence()
				   .findByCompanyGroupShare(companyId, groupId, shareId, start,
			end);
	}

	/**
	* Returns an ordered range of all the shares where companyId = &#63; and groupId = &#63; and shareId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param shareId the share ID
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching shares
	*/
	public static List<Share> findByCompanyGroupShare(long companyId,
		long groupId, long shareId, int start, int end,
		OrderByComparator<Share> orderByComparator) {
		return getPersistence()
				   .findByCompanyGroupShare(companyId, groupId, shareId, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the shares where companyId = &#63; and groupId = &#63; and shareId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param shareId the share ID
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching shares
	*/
	public static List<Share> findByCompanyGroupShare(long companyId,
		long groupId, long shareId, int start, int end,
		OrderByComparator<Share> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyGroupShare(companyId, groupId, shareId, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first share in the ordered set where companyId = &#63; and groupId = &#63; and shareId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching share
	* @throws NoSuchShareException if a matching share could not be found
	*/
	public static Share findByCompanyGroupShare_First(long companyId,
		long groupId, long shareId, OrderByComparator<Share> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchShareException {
		return getPersistence()
				   .findByCompanyGroupShare_First(companyId, groupId, shareId,
			orderByComparator);
	}

	/**
	* Returns the first share in the ordered set where companyId = &#63; and groupId = &#63; and shareId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching share, or <code>null</code> if a matching share could not be found
	*/
	public static Share fetchByCompanyGroupShare_First(long companyId,
		long groupId, long shareId, OrderByComparator<Share> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyGroupShare_First(companyId, groupId, shareId,
			orderByComparator);
	}

	/**
	* Returns the last share in the ordered set where companyId = &#63; and groupId = &#63; and shareId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching share
	* @throws NoSuchShareException if a matching share could not be found
	*/
	public static Share findByCompanyGroupShare_Last(long companyId,
		long groupId, long shareId, OrderByComparator<Share> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchShareException {
		return getPersistence()
				   .findByCompanyGroupShare_Last(companyId, groupId, shareId,
			orderByComparator);
	}

	/**
	* Returns the last share in the ordered set where companyId = &#63; and groupId = &#63; and shareId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching share, or <code>null</code> if a matching share could not be found
	*/
	public static Share fetchByCompanyGroupShare_Last(long companyId,
		long groupId, long shareId, OrderByComparator<Share> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyGroupShare_Last(companyId, groupId, shareId,
			orderByComparator);
	}

	/**
	* Removes all the shares where companyId = &#63; and groupId = &#63; and shareId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param shareId the share ID
	*/
	public static void removeByCompanyGroupShare(long companyId, long groupId,
		long shareId) {
		getPersistence().removeByCompanyGroupShare(companyId, groupId, shareId);
	}

	/**
	* Returns the number of shares where companyId = &#63; and groupId = &#63; and shareId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param shareId the share ID
	* @return the number of matching shares
	*/
	public static int countByCompanyGroupShare(long companyId, long groupId,
		long shareId) {
		return getPersistence()
				   .countByCompanyGroupShare(companyId, groupId, shareId);
	}

	/**
	* Returns all the shares where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @return the matching shares
	*/
	public static List<Share> findByCompanyGroup(long companyId, long groupId) {
		return getPersistence().findByCompanyGroup(companyId, groupId);
	}

	/**
	* Returns a range of all the shares where companyId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @return the range of matching shares
	*/
	public static List<Share> findByCompanyGroup(long companyId, long groupId,
		int start, int end) {
		return getPersistence()
				   .findByCompanyGroup(companyId, groupId, start, end);
	}

	/**
	* Returns an ordered range of all the shares where companyId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching shares
	*/
	public static List<Share> findByCompanyGroup(long companyId, long groupId,
		int start, int end, OrderByComparator<Share> orderByComparator) {
		return getPersistence()
				   .findByCompanyGroup(companyId, groupId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the shares where companyId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching shares
	*/
	public static List<Share> findByCompanyGroup(long companyId, long groupId,
		int start, int end, OrderByComparator<Share> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyGroup(companyId, groupId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first share in the ordered set where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching share
	* @throws NoSuchShareException if a matching share could not be found
	*/
	public static Share findByCompanyGroup_First(long companyId, long groupId,
		OrderByComparator<Share> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchShareException {
		return getPersistence()
				   .findByCompanyGroup_First(companyId, groupId,
			orderByComparator);
	}

	/**
	* Returns the first share in the ordered set where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching share, or <code>null</code> if a matching share could not be found
	*/
	public static Share fetchByCompanyGroup_First(long companyId, long groupId,
		OrderByComparator<Share> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyGroup_First(companyId, groupId,
			orderByComparator);
	}

	/**
	* Returns the last share in the ordered set where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching share
	* @throws NoSuchShareException if a matching share could not be found
	*/
	public static Share findByCompanyGroup_Last(long companyId, long groupId,
		OrderByComparator<Share> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchShareException {
		return getPersistence()
				   .findByCompanyGroup_Last(companyId, groupId,
			orderByComparator);
	}

	/**
	* Returns the last share in the ordered set where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching share, or <code>null</code> if a matching share could not be found
	*/
	public static Share fetchByCompanyGroup_Last(long companyId, long groupId,
		OrderByComparator<Share> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyGroup_Last(companyId, groupId,
			orderByComparator);
	}

	/**
	* Returns the shares before and after the current share in the ordered set where companyId = &#63; and groupId = &#63;.
	*
	* @param shareId the primary key of the current share
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next share
	* @throws NoSuchShareException if a share with the primary key could not be found
	*/
	public static Share[] findByCompanyGroup_PrevAndNext(long shareId,
		long companyId, long groupId, OrderByComparator<Share> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchShareException {
		return getPersistence()
				   .findByCompanyGroup_PrevAndNext(shareId, companyId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the shares where companyId = &#63; and groupId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	*/
	public static void removeByCompanyGroup(long companyId, long groupId) {
		getPersistence().removeByCompanyGroup(companyId, groupId);
	}

	/**
	* Returns the number of shares where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @return the number of matching shares
	*/
	public static int countByCompanyGroup(long companyId, long groupId) {
		return getPersistence().countByCompanyGroup(companyId, groupId);
	}

	/**
	* Returns the share where companyId = &#63; and groupId = &#63; and name = &#63; and marketId = &#63; or throws a {@link NoSuchShareException} if it could not be found.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param name the name
	* @param marketId the market ID
	* @return the matching share
	* @throws NoSuchShareException if a matching share could not be found
	*/
	public static Share findByNameMarketCompanyGroup(long companyId,
		long groupId, java.lang.String name, long marketId)
		throws com.ibtrader.data.exception.NoSuchShareException {
		return getPersistence()
				   .findByNameMarketCompanyGroup(companyId, groupId, name,
			marketId);
	}

	/**
	* Returns the share where companyId = &#63; and groupId = &#63; and name = &#63; and marketId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param name the name
	* @param marketId the market ID
	* @return the matching share, or <code>null</code> if a matching share could not be found
	*/
	public static Share fetchByNameMarketCompanyGroup(long companyId,
		long groupId, java.lang.String name, long marketId) {
		return getPersistence()
				   .fetchByNameMarketCompanyGroup(companyId, groupId, name,
			marketId);
	}

	/**
	* Returns the share where companyId = &#63; and groupId = &#63; and name = &#63; and marketId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param name the name
	* @param marketId the market ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching share, or <code>null</code> if a matching share could not be found
	*/
	public static Share fetchByNameMarketCompanyGroup(long companyId,
		long groupId, java.lang.String name, long marketId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByNameMarketCompanyGroup(companyId, groupId, name,
			marketId, retrieveFromCache);
	}

	/**
	* Removes the share where companyId = &#63; and groupId = &#63; and name = &#63; and marketId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param name the name
	* @param marketId the market ID
	* @return the share that was removed
	*/
	public static Share removeByNameMarketCompanyGroup(long companyId,
		long groupId, java.lang.String name, long marketId)
		throws com.ibtrader.data.exception.NoSuchShareException {
		return getPersistence()
				   .removeByNameMarketCompanyGroup(companyId, groupId, name,
			marketId);
	}

	/**
	* Returns the number of shares where companyId = &#63; and groupId = &#63; and name = &#63; and marketId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param name the name
	* @param marketId the market ID
	* @return the number of matching shares
	*/
	public static int countByNameMarketCompanyGroup(long companyId,
		long groupId, java.lang.String name, long marketId) {
		return getPersistence()
				   .countByNameMarketCompanyGroup(companyId, groupId, name,
			marketId);
	}

	/**
	* Returns the share where companyId = &#63; and groupId = &#63; and symbol = &#63; or throws a {@link NoSuchShareException} if it could not be found.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param symbol the symbol
	* @return the matching share
	* @throws NoSuchShareException if a matching share could not be found
	*/
	public static Share findBySymbolCompanyGroup(long companyId, long groupId,
		java.lang.String symbol)
		throws com.ibtrader.data.exception.NoSuchShareException {
		return getPersistence()
				   .findBySymbolCompanyGroup(companyId, groupId, symbol);
	}

	/**
	* Returns the share where companyId = &#63; and groupId = &#63; and symbol = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param symbol the symbol
	* @return the matching share, or <code>null</code> if a matching share could not be found
	*/
	public static Share fetchBySymbolCompanyGroup(long companyId, long groupId,
		java.lang.String symbol) {
		return getPersistence()
				   .fetchBySymbolCompanyGroup(companyId, groupId, symbol);
	}

	/**
	* Returns the share where companyId = &#63; and groupId = &#63; and symbol = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param symbol the symbol
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching share, or <code>null</code> if a matching share could not be found
	*/
	public static Share fetchBySymbolCompanyGroup(long companyId, long groupId,
		java.lang.String symbol, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchBySymbolCompanyGroup(companyId, groupId, symbol,
			retrieveFromCache);
	}

	/**
	* Removes the share where companyId = &#63; and groupId = &#63; and symbol = &#63; from the database.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param symbol the symbol
	* @return the share that was removed
	*/
	public static Share removeBySymbolCompanyGroup(long companyId,
		long groupId, java.lang.String symbol)
		throws com.ibtrader.data.exception.NoSuchShareException {
		return getPersistence()
				   .removeBySymbolCompanyGroup(companyId, groupId, symbol);
	}

	/**
	* Returns the number of shares where companyId = &#63; and groupId = &#63; and symbol = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param symbol the symbol
	* @return the number of matching shares
	*/
	public static int countBySymbolCompanyGroup(long companyId, long groupId,
		java.lang.String symbol) {
		return getPersistence()
				   .countBySymbolCompanyGroup(companyId, groupId, symbol);
	}

	/**
	* Returns all the shares where groupId = &#63; and companyId = &#63; and active = &#63; and marketId = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param active the active
	* @param marketId the market ID
	* @return the matching shares
	*/
	public static List<Share> findByActiveMarketGroupCompany(long groupId,
		long companyId, boolean active, long marketId) {
		return getPersistence()
				   .findByActiveMarketGroupCompany(groupId, companyId, active,
			marketId);
	}

	/**
	* Returns a range of all the shares where groupId = &#63; and companyId = &#63; and active = &#63; and marketId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param active the active
	* @param marketId the market ID
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @return the range of matching shares
	*/
	public static List<Share> findByActiveMarketGroupCompany(long groupId,
		long companyId, boolean active, long marketId, int start, int end) {
		return getPersistence()
				   .findByActiveMarketGroupCompany(groupId, companyId, active,
			marketId, start, end);
	}

	/**
	* Returns an ordered range of all the shares where groupId = &#63; and companyId = &#63; and active = &#63; and marketId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param active the active
	* @param marketId the market ID
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching shares
	*/
	public static List<Share> findByActiveMarketGroupCompany(long groupId,
		long companyId, boolean active, long marketId, int start, int end,
		OrderByComparator<Share> orderByComparator) {
		return getPersistence()
				   .findByActiveMarketGroupCompany(groupId, companyId, active,
			marketId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the shares where groupId = &#63; and companyId = &#63; and active = &#63; and marketId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param active the active
	* @param marketId the market ID
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching shares
	*/
	public static List<Share> findByActiveMarketGroupCompany(long groupId,
		long companyId, boolean active, long marketId, int start, int end,
		OrderByComparator<Share> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByActiveMarketGroupCompany(groupId, companyId, active,
			marketId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first share in the ordered set where groupId = &#63; and companyId = &#63; and active = &#63; and marketId = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param active the active
	* @param marketId the market ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching share
	* @throws NoSuchShareException if a matching share could not be found
	*/
	public static Share findByActiveMarketGroupCompany_First(long groupId,
		long companyId, boolean active, long marketId,
		OrderByComparator<Share> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchShareException {
		return getPersistence()
				   .findByActiveMarketGroupCompany_First(groupId, companyId,
			active, marketId, orderByComparator);
	}

	/**
	* Returns the first share in the ordered set where groupId = &#63; and companyId = &#63; and active = &#63; and marketId = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param active the active
	* @param marketId the market ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching share, or <code>null</code> if a matching share could not be found
	*/
	public static Share fetchByActiveMarketGroupCompany_First(long groupId,
		long companyId, boolean active, long marketId,
		OrderByComparator<Share> orderByComparator) {
		return getPersistence()
				   .fetchByActiveMarketGroupCompany_First(groupId, companyId,
			active, marketId, orderByComparator);
	}

	/**
	* Returns the last share in the ordered set where groupId = &#63; and companyId = &#63; and active = &#63; and marketId = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param active the active
	* @param marketId the market ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching share
	* @throws NoSuchShareException if a matching share could not be found
	*/
	public static Share findByActiveMarketGroupCompany_Last(long groupId,
		long companyId, boolean active, long marketId,
		OrderByComparator<Share> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchShareException {
		return getPersistence()
				   .findByActiveMarketGroupCompany_Last(groupId, companyId,
			active, marketId, orderByComparator);
	}

	/**
	* Returns the last share in the ordered set where groupId = &#63; and companyId = &#63; and active = &#63; and marketId = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param active the active
	* @param marketId the market ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching share, or <code>null</code> if a matching share could not be found
	*/
	public static Share fetchByActiveMarketGroupCompany_Last(long groupId,
		long companyId, boolean active, long marketId,
		OrderByComparator<Share> orderByComparator) {
		return getPersistence()
				   .fetchByActiveMarketGroupCompany_Last(groupId, companyId,
			active, marketId, orderByComparator);
	}

	/**
	* Returns the shares before and after the current share in the ordered set where groupId = &#63; and companyId = &#63; and active = &#63; and marketId = &#63;.
	*
	* @param shareId the primary key of the current share
	* @param groupId the group ID
	* @param companyId the company ID
	* @param active the active
	* @param marketId the market ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next share
	* @throws NoSuchShareException if a share with the primary key could not be found
	*/
	public static Share[] findByActiveMarketGroupCompany_PrevAndNext(
		long shareId, long groupId, long companyId, boolean active,
		long marketId, OrderByComparator<Share> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchShareException {
		return getPersistence()
				   .findByActiveMarketGroupCompany_PrevAndNext(shareId,
			groupId, companyId, active, marketId, orderByComparator);
	}

	/**
	* Removes all the shares where groupId = &#63; and companyId = &#63; and active = &#63; and marketId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param active the active
	* @param marketId the market ID
	*/
	public static void removeByActiveMarketGroupCompany(long groupId,
		long companyId, boolean active, long marketId) {
		getPersistence()
			.removeByActiveMarketGroupCompany(groupId, companyId, active,
			marketId);
	}

	/**
	* Returns the number of shares where groupId = &#63; and companyId = &#63; and active = &#63; and marketId = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param active the active
	* @param marketId the market ID
	* @return the number of matching shares
	*/
	public static int countByActiveMarketGroupCompany(long groupId,
		long companyId, boolean active, long marketId) {
		return getPersistence()
				   .countByActiveMarketGroupCompany(groupId, companyId, active,
			marketId);
	}

	/**
	* Returns all the shares where groupId = &#63; and companyId = &#63; and marketId = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param marketId the market ID
	* @return the matching shares
	*/
	public static List<Share> findByMarketGroupCompany(long groupId,
		long companyId, long marketId) {
		return getPersistence()
				   .findByMarketGroupCompany(groupId, companyId, marketId);
	}

	/**
	* Returns a range of all the shares where groupId = &#63; and companyId = &#63; and marketId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param marketId the market ID
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @return the range of matching shares
	*/
	public static List<Share> findByMarketGroupCompany(long groupId,
		long companyId, long marketId, int start, int end) {
		return getPersistence()
				   .findByMarketGroupCompany(groupId, companyId, marketId,
			start, end);
	}

	/**
	* Returns an ordered range of all the shares where groupId = &#63; and companyId = &#63; and marketId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param marketId the market ID
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching shares
	*/
	public static List<Share> findByMarketGroupCompany(long groupId,
		long companyId, long marketId, int start, int end,
		OrderByComparator<Share> orderByComparator) {
		return getPersistence()
				   .findByMarketGroupCompany(groupId, companyId, marketId,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the shares where groupId = &#63; and companyId = &#63; and marketId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param marketId the market ID
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching shares
	*/
	public static List<Share> findByMarketGroupCompany(long groupId,
		long companyId, long marketId, int start, int end,
		OrderByComparator<Share> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByMarketGroupCompany(groupId, companyId, marketId,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first share in the ordered set where groupId = &#63; and companyId = &#63; and marketId = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param marketId the market ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching share
	* @throws NoSuchShareException if a matching share could not be found
	*/
	public static Share findByMarketGroupCompany_First(long groupId,
		long companyId, long marketId,
		OrderByComparator<Share> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchShareException {
		return getPersistence()
				   .findByMarketGroupCompany_First(groupId, companyId,
			marketId, orderByComparator);
	}

	/**
	* Returns the first share in the ordered set where groupId = &#63; and companyId = &#63; and marketId = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param marketId the market ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching share, or <code>null</code> if a matching share could not be found
	*/
	public static Share fetchByMarketGroupCompany_First(long groupId,
		long companyId, long marketId,
		OrderByComparator<Share> orderByComparator) {
		return getPersistence()
				   .fetchByMarketGroupCompany_First(groupId, companyId,
			marketId, orderByComparator);
	}

	/**
	* Returns the last share in the ordered set where groupId = &#63; and companyId = &#63; and marketId = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param marketId the market ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching share
	* @throws NoSuchShareException if a matching share could not be found
	*/
	public static Share findByMarketGroupCompany_Last(long groupId,
		long companyId, long marketId,
		OrderByComparator<Share> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchShareException {
		return getPersistence()
				   .findByMarketGroupCompany_Last(groupId, companyId, marketId,
			orderByComparator);
	}

	/**
	* Returns the last share in the ordered set where groupId = &#63; and companyId = &#63; and marketId = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param marketId the market ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching share, or <code>null</code> if a matching share could not be found
	*/
	public static Share fetchByMarketGroupCompany_Last(long groupId,
		long companyId, long marketId,
		OrderByComparator<Share> orderByComparator) {
		return getPersistence()
				   .fetchByMarketGroupCompany_Last(groupId, companyId,
			marketId, orderByComparator);
	}

	/**
	* Returns the shares before and after the current share in the ordered set where groupId = &#63; and companyId = &#63; and marketId = &#63;.
	*
	* @param shareId the primary key of the current share
	* @param groupId the group ID
	* @param companyId the company ID
	* @param marketId the market ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next share
	* @throws NoSuchShareException if a share with the primary key could not be found
	*/
	public static Share[] findByMarketGroupCompany_PrevAndNext(long shareId,
		long groupId, long companyId, long marketId,
		OrderByComparator<Share> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchShareException {
		return getPersistence()
				   .findByMarketGroupCompany_PrevAndNext(shareId, groupId,
			companyId, marketId, orderByComparator);
	}

	/**
	* Removes all the shares where groupId = &#63; and companyId = &#63; and marketId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param marketId the market ID
	*/
	public static void removeByMarketGroupCompany(long groupId, long companyId,
		long marketId) {
		getPersistence().removeByMarketGroupCompany(groupId, companyId, marketId);
	}

	/**
	* Returns the number of shares where groupId = &#63; and companyId = &#63; and marketId = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param marketId the market ID
	* @return the number of matching shares
	*/
	public static int countByMarketGroupCompany(long groupId, long companyId,
		long marketId) {
		return getPersistence()
				   .countByMarketGroupCompany(groupId, companyId, marketId);
	}

	/**
	* Caches the share in the entity cache if it is enabled.
	*
	* @param share the share
	*/
	public static void cacheResult(Share share) {
		getPersistence().cacheResult(share);
	}

	/**
	* Caches the shares in the entity cache if it is enabled.
	*
	* @param shares the shares
	*/
	public static void cacheResult(List<Share> shares) {
		getPersistence().cacheResult(shares);
	}

	/**
	* Creates a new share with the primary key. Does not add the share to the database.
	*
	* @param shareId the primary key for the new share
	* @return the new share
	*/
	public static Share create(long shareId) {
		return getPersistence().create(shareId);
	}

	/**
	* Removes the share with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param shareId the primary key of the share
	* @return the share that was removed
	* @throws NoSuchShareException if a share with the primary key could not be found
	*/
	public static Share remove(long shareId)
		throws com.ibtrader.data.exception.NoSuchShareException {
		return getPersistence().remove(shareId);
	}

	public static Share updateImpl(Share share) {
		return getPersistence().updateImpl(share);
	}

	/**
	* Returns the share with the primary key or throws a {@link NoSuchShareException} if it could not be found.
	*
	* @param shareId the primary key of the share
	* @return the share
	* @throws NoSuchShareException if a share with the primary key could not be found
	*/
	public static Share findByPrimaryKey(long shareId)
		throws com.ibtrader.data.exception.NoSuchShareException {
		return getPersistence().findByPrimaryKey(shareId);
	}

	/**
	* Returns the share with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param shareId the primary key of the share
	* @return the share, or <code>null</code> if a share with the primary key could not be found
	*/
	public static Share fetchByPrimaryKey(long shareId) {
		return getPersistence().fetchByPrimaryKey(shareId);
	}

	public static java.util.Map<java.io.Serializable, Share> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the shares.
	*
	* @return the shares
	*/
	public static List<Share> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the shares.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @return the range of shares
	*/
	public static List<Share> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the shares.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of shares
	*/
	public static List<Share> findAll(int start, int end,
		OrderByComparator<Share> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the shares.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of shares
	*/
	public static List<Share> findAll(int start, int end,
		OrderByComparator<Share> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the shares from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of shares.
	*
	* @return the number of shares
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static SharePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SharePersistence, SharePersistence> _serviceTracker =
		ServiceTrackerFactory.open(SharePersistence.class);
}