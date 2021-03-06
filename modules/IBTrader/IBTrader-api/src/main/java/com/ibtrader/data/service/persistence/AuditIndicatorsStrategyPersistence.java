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

import com.ibtrader.data.exception.NoSuchAuditIndicatorsStrategyException;
import com.ibtrader.data.model.AuditIndicatorsStrategy;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the audit indicators strategy service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ibtrader.data.service.persistence.impl.AuditIndicatorsStrategyPersistenceImpl
 * @see AuditIndicatorsStrategyUtil
 * @generated
 */
@ProviderType
public interface AuditIndicatorsStrategyPersistence extends BasePersistence<AuditIndicatorsStrategy> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AuditIndicatorsStrategyUtil} to access the audit indicators strategy persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the audit indicators strategies where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching audit indicators strategies
	*/
	public java.util.List<AuditIndicatorsStrategy> findByUuid(
		java.lang.String uuid);

	/**
	* Returns a range of all the audit indicators strategies where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditIndicatorsStrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of audit indicators strategies
	* @param end the upper bound of the range of audit indicators strategies (not inclusive)
	* @return the range of matching audit indicators strategies
	*/
	public java.util.List<AuditIndicatorsStrategy> findByUuid(
		java.lang.String uuid, int start, int end);

	/**
	* Returns an ordered range of all the audit indicators strategies where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditIndicatorsStrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of audit indicators strategies
	* @param end the upper bound of the range of audit indicators strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching audit indicators strategies
	*/
	public java.util.List<AuditIndicatorsStrategy> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditIndicatorsStrategy> orderByComparator);

	/**
	* Returns an ordered range of all the audit indicators strategies where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditIndicatorsStrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of audit indicators strategies
	* @param end the upper bound of the range of audit indicators strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching audit indicators strategies
	*/
	public java.util.List<AuditIndicatorsStrategy> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditIndicatorsStrategy> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first audit indicators strategy in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit indicators strategy
	* @throws NoSuchAuditIndicatorsStrategyException if a matching audit indicators strategy could not be found
	*/
	public AuditIndicatorsStrategy findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AuditIndicatorsStrategy> orderByComparator)
		throws NoSuchAuditIndicatorsStrategyException;

	/**
	* Returns the first audit indicators strategy in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit indicators strategy, or <code>null</code> if a matching audit indicators strategy could not be found
	*/
	public AuditIndicatorsStrategy fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AuditIndicatorsStrategy> orderByComparator);

	/**
	* Returns the last audit indicators strategy in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit indicators strategy
	* @throws NoSuchAuditIndicatorsStrategyException if a matching audit indicators strategy could not be found
	*/
	public AuditIndicatorsStrategy findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AuditIndicatorsStrategy> orderByComparator)
		throws NoSuchAuditIndicatorsStrategyException;

	/**
	* Returns the last audit indicators strategy in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit indicators strategy, or <code>null</code> if a matching audit indicators strategy could not be found
	*/
	public AuditIndicatorsStrategy fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AuditIndicatorsStrategy> orderByComparator);

	/**
	* Returns the audit indicators strategies before and after the current audit indicators strategy in the ordered set where uuid = &#63;.
	*
	* @param auditIndicatorsStrategyPK the primary key of the current audit indicators strategy
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit indicators strategy
	* @throws NoSuchAuditIndicatorsStrategyException if a audit indicators strategy with the primary key could not be found
	*/
	public AuditIndicatorsStrategy[] findByUuid_PrevAndNext(
		AuditIndicatorsStrategyPK auditIndicatorsStrategyPK,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AuditIndicatorsStrategy> orderByComparator)
		throws NoSuchAuditIndicatorsStrategyException;

	/**
	* Removes all the audit indicators strategies where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of audit indicators strategies where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching audit indicators strategies
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the audit indicators strategy where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchAuditIndicatorsStrategyException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching audit indicators strategy
	* @throws NoSuchAuditIndicatorsStrategyException if a matching audit indicators strategy could not be found
	*/
	public AuditIndicatorsStrategy findByUUID_G(java.lang.String uuid,
		long groupId) throws NoSuchAuditIndicatorsStrategyException;

	/**
	* Returns the audit indicators strategy where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching audit indicators strategy, or <code>null</code> if a matching audit indicators strategy could not be found
	*/
	public AuditIndicatorsStrategy fetchByUUID_G(java.lang.String uuid,
		long groupId);

	/**
	* Returns the audit indicators strategy where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching audit indicators strategy, or <code>null</code> if a matching audit indicators strategy could not be found
	*/
	public AuditIndicatorsStrategy fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache);

	/**
	* Removes the audit indicators strategy where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the audit indicators strategy that was removed
	*/
	public AuditIndicatorsStrategy removeByUUID_G(java.lang.String uuid,
		long groupId) throws NoSuchAuditIndicatorsStrategyException;

	/**
	* Returns the number of audit indicators strategies where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching audit indicators strategies
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the audit indicators strategies where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching audit indicators strategies
	*/
	public java.util.List<AuditIndicatorsStrategy> findByUuid_C(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of all the audit indicators strategies where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditIndicatorsStrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of audit indicators strategies
	* @param end the upper bound of the range of audit indicators strategies (not inclusive)
	* @return the range of matching audit indicators strategies
	*/
	public java.util.List<AuditIndicatorsStrategy> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end);

	/**
	* Returns an ordered range of all the audit indicators strategies where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditIndicatorsStrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of audit indicators strategies
	* @param end the upper bound of the range of audit indicators strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching audit indicators strategies
	*/
	public java.util.List<AuditIndicatorsStrategy> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditIndicatorsStrategy> orderByComparator);

	/**
	* Returns an ordered range of all the audit indicators strategies where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditIndicatorsStrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of audit indicators strategies
	* @param end the upper bound of the range of audit indicators strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching audit indicators strategies
	*/
	public java.util.List<AuditIndicatorsStrategy> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditIndicatorsStrategy> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first audit indicators strategy in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit indicators strategy
	* @throws NoSuchAuditIndicatorsStrategyException if a matching audit indicators strategy could not be found
	*/
	public AuditIndicatorsStrategy findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditIndicatorsStrategy> orderByComparator)
		throws NoSuchAuditIndicatorsStrategyException;

	/**
	* Returns the first audit indicators strategy in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit indicators strategy, or <code>null</code> if a matching audit indicators strategy could not be found
	*/
	public AuditIndicatorsStrategy fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditIndicatorsStrategy> orderByComparator);

	/**
	* Returns the last audit indicators strategy in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit indicators strategy
	* @throws NoSuchAuditIndicatorsStrategyException if a matching audit indicators strategy could not be found
	*/
	public AuditIndicatorsStrategy findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditIndicatorsStrategy> orderByComparator)
		throws NoSuchAuditIndicatorsStrategyException;

	/**
	* Returns the last audit indicators strategy in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit indicators strategy, or <code>null</code> if a matching audit indicators strategy could not be found
	*/
	public AuditIndicatorsStrategy fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditIndicatorsStrategy> orderByComparator);

	/**
	* Returns the audit indicators strategies before and after the current audit indicators strategy in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param auditIndicatorsStrategyPK the primary key of the current audit indicators strategy
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit indicators strategy
	* @throws NoSuchAuditIndicatorsStrategyException if a audit indicators strategy with the primary key could not be found
	*/
	public AuditIndicatorsStrategy[] findByUuid_C_PrevAndNext(
		AuditIndicatorsStrategyPK auditIndicatorsStrategyPK,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditIndicatorsStrategy> orderByComparator)
		throws NoSuchAuditIndicatorsStrategyException;

	/**
	* Removes all the audit indicators strategies where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of audit indicators strategies where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching audit indicators strategies
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Caches the audit indicators strategy in the entity cache if it is enabled.
	*
	* @param auditIndicatorsStrategy the audit indicators strategy
	*/
	public void cacheResult(AuditIndicatorsStrategy auditIndicatorsStrategy);

	/**
	* Caches the audit indicators strategies in the entity cache if it is enabled.
	*
	* @param auditIndicatorsStrategies the audit indicators strategies
	*/
	public void cacheResult(
		java.util.List<AuditIndicatorsStrategy> auditIndicatorsStrategies);

	/**
	* Creates a new audit indicators strategy with the primary key. Does not add the audit indicators strategy to the database.
	*
	* @param auditIndicatorsStrategyPK the primary key for the new audit indicators strategy
	* @return the new audit indicators strategy
	*/
	public AuditIndicatorsStrategy create(
		AuditIndicatorsStrategyPK auditIndicatorsStrategyPK);

	/**
	* Removes the audit indicators strategy with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param auditIndicatorsStrategyPK the primary key of the audit indicators strategy
	* @return the audit indicators strategy that was removed
	* @throws NoSuchAuditIndicatorsStrategyException if a audit indicators strategy with the primary key could not be found
	*/
	public AuditIndicatorsStrategy remove(
		AuditIndicatorsStrategyPK auditIndicatorsStrategyPK)
		throws NoSuchAuditIndicatorsStrategyException;

	public AuditIndicatorsStrategy updateImpl(
		AuditIndicatorsStrategy auditIndicatorsStrategy);

	/**
	* Returns the audit indicators strategy with the primary key or throws a {@link NoSuchAuditIndicatorsStrategyException} if it could not be found.
	*
	* @param auditIndicatorsStrategyPK the primary key of the audit indicators strategy
	* @return the audit indicators strategy
	* @throws NoSuchAuditIndicatorsStrategyException if a audit indicators strategy with the primary key could not be found
	*/
	public AuditIndicatorsStrategy findByPrimaryKey(
		AuditIndicatorsStrategyPK auditIndicatorsStrategyPK)
		throws NoSuchAuditIndicatorsStrategyException;

	/**
	* Returns the audit indicators strategy with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param auditIndicatorsStrategyPK the primary key of the audit indicators strategy
	* @return the audit indicators strategy, or <code>null</code> if a audit indicators strategy with the primary key could not be found
	*/
	public AuditIndicatorsStrategy fetchByPrimaryKey(
		AuditIndicatorsStrategyPK auditIndicatorsStrategyPK);

	@Override
	public java.util.Map<java.io.Serializable, AuditIndicatorsStrategy> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the audit indicators strategies.
	*
	* @return the audit indicators strategies
	*/
	public java.util.List<AuditIndicatorsStrategy> findAll();

	/**
	* Returns a range of all the audit indicators strategies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditIndicatorsStrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of audit indicators strategies
	* @param end the upper bound of the range of audit indicators strategies (not inclusive)
	* @return the range of audit indicators strategies
	*/
	public java.util.List<AuditIndicatorsStrategy> findAll(int start, int end);

	/**
	* Returns an ordered range of all the audit indicators strategies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditIndicatorsStrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of audit indicators strategies
	* @param end the upper bound of the range of audit indicators strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of audit indicators strategies
	*/
	public java.util.List<AuditIndicatorsStrategy> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditIndicatorsStrategy> orderByComparator);

	/**
	* Returns an ordered range of all the audit indicators strategies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditIndicatorsStrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of audit indicators strategies
	* @param end the upper bound of the range of audit indicators strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of audit indicators strategies
	*/
	public java.util.List<AuditIndicatorsStrategy> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditIndicatorsStrategy> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the audit indicators strategies from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of audit indicators strategies.
	*
	* @return the number of audit indicators strategies
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}