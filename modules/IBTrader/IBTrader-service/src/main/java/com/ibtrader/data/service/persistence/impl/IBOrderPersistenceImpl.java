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

package com.ibtrader.data.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.ibtrader.data.exception.NoSuchIBOrderException;
import com.ibtrader.data.model.IBOrder;
import com.ibtrader.data.model.impl.IBOrderImpl;
import com.ibtrader.data.model.impl.IBOrderModelImpl;
import com.ibtrader.data.service.persistence.IBOrderPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the ib order service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see IBOrderPersistence
 * @see com.ibtrader.data.service.persistence.IBOrderUtil
 * @generated
 */
@ProviderType
public class IBOrderPersistenceImpl extends BasePersistenceImpl<IBOrder>
	implements IBOrderPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IBOrderUtil} to access the ib order persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IBOrderImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderModelImpl.FINDER_CACHE_ENABLED, IBOrderImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderModelImpl.FINDER_CACHE_ENABLED, IBOrderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderModelImpl.FINDER_CACHE_ENABLED, IBOrderImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderModelImpl.FINDER_CACHE_ENABLED, IBOrderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			IBOrderModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the ib orders where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching ib orders
	 */
	@Override
	public List<IBOrder> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ib orders where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of ib orders
	 * @param end the upper bound of the range of ib orders (not inclusive)
	 * @return the range of matching ib orders
	 */
	@Override
	public List<IBOrder> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ib orders where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of ib orders
	 * @param end the upper bound of the range of ib orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ib orders
	 */
	@Override
	public List<IBOrder> findByUuid(String uuid, int start, int end,
		OrderByComparator<IBOrder> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ib orders where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of ib orders
	 * @param end the upper bound of the range of ib orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ib orders
	 */
	@Override
	public List<IBOrder> findByUuid(String uuid, int start, int end,
		OrderByComparator<IBOrder> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<IBOrder> list = null;

		if (retrieveFromCache) {
			list = (List<IBOrder>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (IBOrder ibOrder : list) {
					if (!Objects.equals(uuid, ibOrder.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_IBORDER_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(IBOrderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<IBOrder>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IBOrder>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first ib order in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ib order
	 * @throws NoSuchIBOrderException if a matching ib order could not be found
	 */
	@Override
	public IBOrder findByUuid_First(String uuid,
		OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException {
		IBOrder ibOrder = fetchByUuid_First(uuid, orderByComparator);

		if (ibOrder != null) {
			return ibOrder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIBOrderException(msg.toString());
	}

	/**
	 * Returns the first ib order in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ib order, or <code>null</code> if a matching ib order could not be found
	 */
	@Override
	public IBOrder fetchByUuid_First(String uuid,
		OrderByComparator<IBOrder> orderByComparator) {
		List<IBOrder> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ib order in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ib order
	 * @throws NoSuchIBOrderException if a matching ib order could not be found
	 */
	@Override
	public IBOrder findByUuid_Last(String uuid,
		OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException {
		IBOrder ibOrder = fetchByUuid_Last(uuid, orderByComparator);

		if (ibOrder != null) {
			return ibOrder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIBOrderException(msg.toString());
	}

	/**
	 * Returns the last ib order in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ib order, or <code>null</code> if a matching ib order could not be found
	 */
	@Override
	public IBOrder fetchByUuid_Last(String uuid,
		OrderByComparator<IBOrder> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<IBOrder> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ib orders before and after the current ib order in the ordered set where uuid = &#63;.
	 *
	 * @param orderIdPk the primary key of the current ib order
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ib order
	 * @throws NoSuchIBOrderException if a ib order with the primary key could not be found
	 */
	@Override
	public IBOrder[] findByUuid_PrevAndNext(long orderIdPk, String uuid,
		OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException {
		IBOrder ibOrder = findByPrimaryKey(orderIdPk);

		Session session = null;

		try {
			session = openSession();

			IBOrder[] array = new IBOrderImpl[3];

			array[0] = getByUuid_PrevAndNext(session, ibOrder, uuid,
					orderByComparator, true);

			array[1] = ibOrder;

			array[2] = getByUuid_PrevAndNext(session, ibOrder, uuid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected IBOrder getByUuid_PrevAndNext(Session session, IBOrder ibOrder,
		String uuid, OrderByComparator<IBOrder> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_IBORDER_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(IBOrderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ibOrder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<IBOrder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ib orders where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (IBOrder ibOrder : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(ibOrder);
		}
	}

	/**
	 * Returns the number of ib orders where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching ib orders
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_IBORDER_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "ibOrder.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "ibOrder.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(ibOrder.uuid IS NULL OR ibOrder.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderModelImpl.FINDER_CACHE_ENABLED, IBOrderImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			IBOrderModelImpl.UUID_COLUMN_BITMASK |
			IBOrderModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the ib order where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchIBOrderException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching ib order
	 * @throws NoSuchIBOrderException if a matching ib order could not be found
	 */
	@Override
	public IBOrder findByUUID_G(String uuid, long groupId)
		throws NoSuchIBOrderException {
		IBOrder ibOrder = fetchByUUID_G(uuid, groupId);

		if (ibOrder == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchIBOrderException(msg.toString());
		}

		return ibOrder;
	}

	/**
	 * Returns the ib order where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching ib order, or <code>null</code> if a matching ib order could not be found
	 */
	@Override
	public IBOrder fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the ib order where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching ib order, or <code>null</code> if a matching ib order could not be found
	 */
	@Override
	public IBOrder fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof IBOrder) {
			IBOrder ibOrder = (IBOrder)result;

			if (!Objects.equals(uuid, ibOrder.getUuid()) ||
					(groupId != ibOrder.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_IBORDER_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<IBOrder> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					IBOrder ibOrder = list.get(0);

					result = ibOrder;

					cacheResult(ibOrder);

					if ((ibOrder.getUuid() == null) ||
							!ibOrder.getUuid().equals(uuid) ||
							(ibOrder.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, ibOrder);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (IBOrder)result;
		}
	}

	/**
	 * Removes the ib order where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the ib order that was removed
	 */
	@Override
	public IBOrder removeByUUID_G(String uuid, long groupId)
		throws NoSuchIBOrderException {
		IBOrder ibOrder = findByUUID_G(uuid, groupId);

		return remove(ibOrder);
	}

	/**
	 * Returns the number of ib orders where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching ib orders
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_IBORDER_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "ibOrder.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "ibOrder.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(ibOrder.uuid IS NULL OR ibOrder.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "ibOrder.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderModelImpl.FINDER_CACHE_ENABLED, IBOrderImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderModelImpl.FINDER_CACHE_ENABLED, IBOrderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			IBOrderModelImpl.UUID_COLUMN_BITMASK |
			IBOrderModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the ib orders where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching ib orders
	 */
	@Override
	public List<IBOrder> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ib orders where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of ib orders
	 * @param end the upper bound of the range of ib orders (not inclusive)
	 * @return the range of matching ib orders
	 */
	@Override
	public List<IBOrder> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ib orders where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of ib orders
	 * @param end the upper bound of the range of ib orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ib orders
	 */
	@Override
	public List<IBOrder> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<IBOrder> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ib orders where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of ib orders
	 * @param end the upper bound of the range of ib orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ib orders
	 */
	@Override
	public List<IBOrder> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<IBOrder> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<IBOrder> list = null;

		if (retrieveFromCache) {
			list = (List<IBOrder>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (IBOrder ibOrder : list) {
					if (!Objects.equals(uuid, ibOrder.getUuid()) ||
							(companyId != ibOrder.getCompanyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_IBORDER_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(IBOrderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				if (!pagination) {
					list = (List<IBOrder>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IBOrder>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first ib order in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ib order
	 * @throws NoSuchIBOrderException if a matching ib order could not be found
	 */
	@Override
	public IBOrder findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException {
		IBOrder ibOrder = fetchByUuid_C_First(uuid, companyId, orderByComparator);

		if (ibOrder != null) {
			return ibOrder;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIBOrderException(msg.toString());
	}

	/**
	 * Returns the first ib order in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ib order, or <code>null</code> if a matching ib order could not be found
	 */
	@Override
	public IBOrder fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<IBOrder> orderByComparator) {
		List<IBOrder> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ib order in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ib order
	 * @throws NoSuchIBOrderException if a matching ib order could not be found
	 */
	@Override
	public IBOrder findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException {
		IBOrder ibOrder = fetchByUuid_C_Last(uuid, companyId, orderByComparator);

		if (ibOrder != null) {
			return ibOrder;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIBOrderException(msg.toString());
	}

	/**
	 * Returns the last ib order in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ib order, or <code>null</code> if a matching ib order could not be found
	 */
	@Override
	public IBOrder fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<IBOrder> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<IBOrder> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ib orders before and after the current ib order in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param orderIdPk the primary key of the current ib order
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ib order
	 * @throws NoSuchIBOrderException if a ib order with the primary key could not be found
	 */
	@Override
	public IBOrder[] findByUuid_C_PrevAndNext(long orderIdPk, String uuid,
		long companyId, OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException {
		IBOrder ibOrder = findByPrimaryKey(orderIdPk);

		Session session = null;

		try {
			session = openSession();

			IBOrder[] array = new IBOrderImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, ibOrder, uuid,
					companyId, orderByComparator, true);

			array[1] = ibOrder;

			array[2] = getByUuid_C_PrevAndNext(session, ibOrder, uuid,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected IBOrder getByUuid_C_PrevAndNext(Session session, IBOrder ibOrder,
		String uuid, long companyId,
		OrderByComparator<IBOrder> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_IBORDER_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(IBOrderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ibOrder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<IBOrder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ib orders where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (IBOrder ibOrder : findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(ibOrder);
		}
	}

	/**
	 * Returns the number of ib orders where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching ib orders
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_IBORDER_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "ibOrder.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "ibOrder.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(ibOrder.uuid IS NULL OR ibOrder.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "ibOrder.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SHAREIDCOMPANYGROUP =
		new FinderPath(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderModelImpl.FINDER_CACHE_ENABLED, IBOrderImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByShareIdCompanyGroup",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SHAREIDCOMPANYGROUP =
		new FinderPath(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderModelImpl.FINDER_CACHE_ENABLED, IBOrderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByShareIdCompanyGroup",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			IBOrderModelImpl.SHAREID_COLUMN_BITMASK |
			IBOrderModelImpl.COMPANYID_COLUMN_BITMASK |
			IBOrderModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SHAREIDCOMPANYGROUP = new FinderPath(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByShareIdCompanyGroup",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the ib orders where shareID = &#63; and companyId = &#63; and groupId = &#63;.
	 *
	 * @param shareID the share ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @return the matching ib orders
	 */
	@Override
	public List<IBOrder> findByShareIdCompanyGroup(long shareID,
		long companyId, long groupId) {
		return findByShareIdCompanyGroup(shareID, companyId, groupId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ib orders where shareID = &#63; and companyId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param shareID the share ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of ib orders
	 * @param end the upper bound of the range of ib orders (not inclusive)
	 * @return the range of matching ib orders
	 */
	@Override
	public List<IBOrder> findByShareIdCompanyGroup(long shareID,
		long companyId, long groupId, int start, int end) {
		return findByShareIdCompanyGroup(shareID, companyId, groupId, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the ib orders where shareID = &#63; and companyId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param shareID the share ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of ib orders
	 * @param end the upper bound of the range of ib orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ib orders
	 */
	@Override
	public List<IBOrder> findByShareIdCompanyGroup(long shareID,
		long companyId, long groupId, int start, int end,
		OrderByComparator<IBOrder> orderByComparator) {
		return findByShareIdCompanyGroup(shareID, companyId, groupId, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ib orders where shareID = &#63; and companyId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param shareID the share ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of ib orders
	 * @param end the upper bound of the range of ib orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ib orders
	 */
	@Override
	public List<IBOrder> findByShareIdCompanyGroup(long shareID,
		long companyId, long groupId, int start, int end,
		OrderByComparator<IBOrder> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SHAREIDCOMPANYGROUP;
			finderArgs = new Object[] { shareID, companyId, groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SHAREIDCOMPANYGROUP;
			finderArgs = new Object[] {
					shareID, companyId, groupId,
					
					start, end, orderByComparator
				};
		}

		List<IBOrder> list = null;

		if (retrieveFromCache) {
			list = (List<IBOrder>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (IBOrder ibOrder : list) {
					if ((shareID != ibOrder.getShareID()) ||
							(companyId != ibOrder.getCompanyId()) ||
							(groupId != ibOrder.getGroupId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_IBORDER_WHERE);

			query.append(_FINDER_COLUMN_SHAREIDCOMPANYGROUP_SHAREID_2);

			query.append(_FINDER_COLUMN_SHAREIDCOMPANYGROUP_COMPANYID_2);

			query.append(_FINDER_COLUMN_SHAREIDCOMPANYGROUP_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(IBOrderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(shareID);

				qPos.add(companyId);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<IBOrder>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IBOrder>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first ib order in the ordered set where shareID = &#63; and companyId = &#63; and groupId = &#63;.
	 *
	 * @param shareID the share ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ib order
	 * @throws NoSuchIBOrderException if a matching ib order could not be found
	 */
	@Override
	public IBOrder findByShareIdCompanyGroup_First(long shareID,
		long companyId, long groupId,
		OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException {
		IBOrder ibOrder = fetchByShareIdCompanyGroup_First(shareID, companyId,
				groupId, orderByComparator);

		if (ibOrder != null) {
			return ibOrder;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shareID=");
		msg.append(shareID);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIBOrderException(msg.toString());
	}

	/**
	 * Returns the first ib order in the ordered set where shareID = &#63; and companyId = &#63; and groupId = &#63;.
	 *
	 * @param shareID the share ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ib order, or <code>null</code> if a matching ib order could not be found
	 */
	@Override
	public IBOrder fetchByShareIdCompanyGroup_First(long shareID,
		long companyId, long groupId,
		OrderByComparator<IBOrder> orderByComparator) {
		List<IBOrder> list = findByShareIdCompanyGroup(shareID, companyId,
				groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ib order in the ordered set where shareID = &#63; and companyId = &#63; and groupId = &#63;.
	 *
	 * @param shareID the share ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ib order
	 * @throws NoSuchIBOrderException if a matching ib order could not be found
	 */
	@Override
	public IBOrder findByShareIdCompanyGroup_Last(long shareID, long companyId,
		long groupId, OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException {
		IBOrder ibOrder = fetchByShareIdCompanyGroup_Last(shareID, companyId,
				groupId, orderByComparator);

		if (ibOrder != null) {
			return ibOrder;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shareID=");
		msg.append(shareID);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIBOrderException(msg.toString());
	}

	/**
	 * Returns the last ib order in the ordered set where shareID = &#63; and companyId = &#63; and groupId = &#63;.
	 *
	 * @param shareID the share ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ib order, or <code>null</code> if a matching ib order could not be found
	 */
	@Override
	public IBOrder fetchByShareIdCompanyGroup_Last(long shareID,
		long companyId, long groupId,
		OrderByComparator<IBOrder> orderByComparator) {
		int count = countByShareIdCompanyGroup(shareID, companyId, groupId);

		if (count == 0) {
			return null;
		}

		List<IBOrder> list = findByShareIdCompanyGroup(shareID, companyId,
				groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ib orders before and after the current ib order in the ordered set where shareID = &#63; and companyId = &#63; and groupId = &#63;.
	 *
	 * @param orderIdPk the primary key of the current ib order
	 * @param shareID the share ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ib order
	 * @throws NoSuchIBOrderException if a ib order with the primary key could not be found
	 */
	@Override
	public IBOrder[] findByShareIdCompanyGroup_PrevAndNext(long orderIdPk,
		long shareID, long companyId, long groupId,
		OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException {
		IBOrder ibOrder = findByPrimaryKey(orderIdPk);

		Session session = null;

		try {
			session = openSession();

			IBOrder[] array = new IBOrderImpl[3];

			array[0] = getByShareIdCompanyGroup_PrevAndNext(session, ibOrder,
					shareID, companyId, groupId, orderByComparator, true);

			array[1] = ibOrder;

			array[2] = getByShareIdCompanyGroup_PrevAndNext(session, ibOrder,
					shareID, companyId, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected IBOrder getByShareIdCompanyGroup_PrevAndNext(Session session,
		IBOrder ibOrder, long shareID, long companyId, long groupId,
		OrderByComparator<IBOrder> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_IBORDER_WHERE);

		query.append(_FINDER_COLUMN_SHAREIDCOMPANYGROUP_SHAREID_2);

		query.append(_FINDER_COLUMN_SHAREIDCOMPANYGROUP_COMPANYID_2);

		query.append(_FINDER_COLUMN_SHAREIDCOMPANYGROUP_GROUPID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(IBOrderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(shareID);

		qPos.add(companyId);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ibOrder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<IBOrder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ib orders where shareID = &#63; and companyId = &#63; and groupId = &#63; from the database.
	 *
	 * @param shareID the share ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 */
	@Override
	public void removeByShareIdCompanyGroup(long shareID, long companyId,
		long groupId) {
		for (IBOrder ibOrder : findByShareIdCompanyGroup(shareID, companyId,
				groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ibOrder);
		}
	}

	/**
	 * Returns the number of ib orders where shareID = &#63; and companyId = &#63; and groupId = &#63;.
	 *
	 * @param shareID the share ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @return the number of matching ib orders
	 */
	@Override
	public int countByShareIdCompanyGroup(long shareID, long companyId,
		long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SHAREIDCOMPANYGROUP;

		Object[] finderArgs = new Object[] { shareID, companyId, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_IBORDER_WHERE);

			query.append(_FINDER_COLUMN_SHAREIDCOMPANYGROUP_SHAREID_2);

			query.append(_FINDER_COLUMN_SHAREIDCOMPANYGROUP_COMPANYID_2);

			query.append(_FINDER_COLUMN_SHAREIDCOMPANYGROUP_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(shareID);

				qPos.add(companyId);

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_SHAREIDCOMPANYGROUP_SHAREID_2 = "ibOrder.shareID = ? AND ";
	private static final String _FINDER_COLUMN_SHAREIDCOMPANYGROUP_COMPANYID_2 = "ibOrder.companyId = ? AND ";
	private static final String _FINDER_COLUMN_SHAREIDCOMPANYGROUP_GROUPID_2 = "ibOrder.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ORDERGROUPCOMPANY =
		new FinderPath(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderModelImpl.FINDER_CACHE_ENABLED, IBOrderImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOrderGroupCompany",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERGROUPCOMPANY =
		new FinderPath(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderModelImpl.FINDER_CACHE_ENABLED, IBOrderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByOrderGroupCompany",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			IBOrderModelImpl.SHAREID_COLUMN_BITMASK |
			IBOrderModelImpl.COMPANYID_COLUMN_BITMASK |
			IBOrderModelImpl.ORDERSID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ORDERGROUPCOMPANY = new FinderPath(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByOrderGroupCompany",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the ib orders where shareID = &#63; and companyId = &#63; and ordersId = &#63;.
	 *
	 * @param shareID the share ID
	 * @param companyId the company ID
	 * @param ordersId the orders ID
	 * @return the matching ib orders
	 */
	@Override
	public List<IBOrder> findByOrderGroupCompany(long shareID, long companyId,
		long ordersId) {
		return findByOrderGroupCompany(shareID, companyId, ordersId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ib orders where shareID = &#63; and companyId = &#63; and ordersId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param shareID the share ID
	 * @param companyId the company ID
	 * @param ordersId the orders ID
	 * @param start the lower bound of the range of ib orders
	 * @param end the upper bound of the range of ib orders (not inclusive)
	 * @return the range of matching ib orders
	 */
	@Override
	public List<IBOrder> findByOrderGroupCompany(long shareID, long companyId,
		long ordersId, int start, int end) {
		return findByOrderGroupCompany(shareID, companyId, ordersId, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the ib orders where shareID = &#63; and companyId = &#63; and ordersId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param shareID the share ID
	 * @param companyId the company ID
	 * @param ordersId the orders ID
	 * @param start the lower bound of the range of ib orders
	 * @param end the upper bound of the range of ib orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ib orders
	 */
	@Override
	public List<IBOrder> findByOrderGroupCompany(long shareID, long companyId,
		long ordersId, int start, int end,
		OrderByComparator<IBOrder> orderByComparator) {
		return findByOrderGroupCompany(shareID, companyId, ordersId, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ib orders where shareID = &#63; and companyId = &#63; and ordersId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param shareID the share ID
	 * @param companyId the company ID
	 * @param ordersId the orders ID
	 * @param start the lower bound of the range of ib orders
	 * @param end the upper bound of the range of ib orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ib orders
	 */
	@Override
	public List<IBOrder> findByOrderGroupCompany(long shareID, long companyId,
		long ordersId, int start, int end,
		OrderByComparator<IBOrder> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERGROUPCOMPANY;
			finderArgs = new Object[] { shareID, companyId, ordersId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ORDERGROUPCOMPANY;
			finderArgs = new Object[] {
					shareID, companyId, ordersId,
					
					start, end, orderByComparator
				};
		}

		List<IBOrder> list = null;

		if (retrieveFromCache) {
			list = (List<IBOrder>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (IBOrder ibOrder : list) {
					if ((shareID != ibOrder.getShareID()) ||
							(companyId != ibOrder.getCompanyId()) ||
							(ordersId != ibOrder.getOrdersId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_IBORDER_WHERE);

			query.append(_FINDER_COLUMN_ORDERGROUPCOMPANY_SHAREID_2);

			query.append(_FINDER_COLUMN_ORDERGROUPCOMPANY_COMPANYID_2);

			query.append(_FINDER_COLUMN_ORDERGROUPCOMPANY_ORDERSID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(IBOrderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(shareID);

				qPos.add(companyId);

				qPos.add(ordersId);

				if (!pagination) {
					list = (List<IBOrder>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IBOrder>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first ib order in the ordered set where shareID = &#63; and companyId = &#63; and ordersId = &#63;.
	 *
	 * @param shareID the share ID
	 * @param companyId the company ID
	 * @param ordersId the orders ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ib order
	 * @throws NoSuchIBOrderException if a matching ib order could not be found
	 */
	@Override
	public IBOrder findByOrderGroupCompany_First(long shareID, long companyId,
		long ordersId, OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException {
		IBOrder ibOrder = fetchByOrderGroupCompany_First(shareID, companyId,
				ordersId, orderByComparator);

		if (ibOrder != null) {
			return ibOrder;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shareID=");
		msg.append(shareID);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", ordersId=");
		msg.append(ordersId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIBOrderException(msg.toString());
	}

	/**
	 * Returns the first ib order in the ordered set where shareID = &#63; and companyId = &#63; and ordersId = &#63;.
	 *
	 * @param shareID the share ID
	 * @param companyId the company ID
	 * @param ordersId the orders ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ib order, or <code>null</code> if a matching ib order could not be found
	 */
	@Override
	public IBOrder fetchByOrderGroupCompany_First(long shareID, long companyId,
		long ordersId, OrderByComparator<IBOrder> orderByComparator) {
		List<IBOrder> list = findByOrderGroupCompany(shareID, companyId,
				ordersId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ib order in the ordered set where shareID = &#63; and companyId = &#63; and ordersId = &#63;.
	 *
	 * @param shareID the share ID
	 * @param companyId the company ID
	 * @param ordersId the orders ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ib order
	 * @throws NoSuchIBOrderException if a matching ib order could not be found
	 */
	@Override
	public IBOrder findByOrderGroupCompany_Last(long shareID, long companyId,
		long ordersId, OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException {
		IBOrder ibOrder = fetchByOrderGroupCompany_Last(shareID, companyId,
				ordersId, orderByComparator);

		if (ibOrder != null) {
			return ibOrder;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shareID=");
		msg.append(shareID);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", ordersId=");
		msg.append(ordersId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIBOrderException(msg.toString());
	}

	/**
	 * Returns the last ib order in the ordered set where shareID = &#63; and companyId = &#63; and ordersId = &#63;.
	 *
	 * @param shareID the share ID
	 * @param companyId the company ID
	 * @param ordersId the orders ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ib order, or <code>null</code> if a matching ib order could not be found
	 */
	@Override
	public IBOrder fetchByOrderGroupCompany_Last(long shareID, long companyId,
		long ordersId, OrderByComparator<IBOrder> orderByComparator) {
		int count = countByOrderGroupCompany(shareID, companyId, ordersId);

		if (count == 0) {
			return null;
		}

		List<IBOrder> list = findByOrderGroupCompany(shareID, companyId,
				ordersId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ib orders before and after the current ib order in the ordered set where shareID = &#63; and companyId = &#63; and ordersId = &#63;.
	 *
	 * @param orderIdPk the primary key of the current ib order
	 * @param shareID the share ID
	 * @param companyId the company ID
	 * @param ordersId the orders ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ib order
	 * @throws NoSuchIBOrderException if a ib order with the primary key could not be found
	 */
	@Override
	public IBOrder[] findByOrderGroupCompany_PrevAndNext(long orderIdPk,
		long shareID, long companyId, long ordersId,
		OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException {
		IBOrder ibOrder = findByPrimaryKey(orderIdPk);

		Session session = null;

		try {
			session = openSession();

			IBOrder[] array = new IBOrderImpl[3];

			array[0] = getByOrderGroupCompany_PrevAndNext(session, ibOrder,
					shareID, companyId, ordersId, orderByComparator, true);

			array[1] = ibOrder;

			array[2] = getByOrderGroupCompany_PrevAndNext(session, ibOrder,
					shareID, companyId, ordersId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected IBOrder getByOrderGroupCompany_PrevAndNext(Session session,
		IBOrder ibOrder, long shareID, long companyId, long ordersId,
		OrderByComparator<IBOrder> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_IBORDER_WHERE);

		query.append(_FINDER_COLUMN_ORDERGROUPCOMPANY_SHAREID_2);

		query.append(_FINDER_COLUMN_ORDERGROUPCOMPANY_COMPANYID_2);

		query.append(_FINDER_COLUMN_ORDERGROUPCOMPANY_ORDERSID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(IBOrderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(shareID);

		qPos.add(companyId);

		qPos.add(ordersId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ibOrder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<IBOrder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ib orders where shareID = &#63; and companyId = &#63; and ordersId = &#63; from the database.
	 *
	 * @param shareID the share ID
	 * @param companyId the company ID
	 * @param ordersId the orders ID
	 */
	@Override
	public void removeByOrderGroupCompany(long shareID, long companyId,
		long ordersId) {
		for (IBOrder ibOrder : findByOrderGroupCompany(shareID, companyId,
				ordersId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ibOrder);
		}
	}

	/**
	 * Returns the number of ib orders where shareID = &#63; and companyId = &#63; and ordersId = &#63;.
	 *
	 * @param shareID the share ID
	 * @param companyId the company ID
	 * @param ordersId the orders ID
	 * @return the number of matching ib orders
	 */
	@Override
	public int countByOrderGroupCompany(long shareID, long companyId,
		long ordersId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ORDERGROUPCOMPANY;

		Object[] finderArgs = new Object[] { shareID, companyId, ordersId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_IBORDER_WHERE);

			query.append(_FINDER_COLUMN_ORDERGROUPCOMPANY_SHAREID_2);

			query.append(_FINDER_COLUMN_ORDERGROUPCOMPANY_COMPANYID_2);

			query.append(_FINDER_COLUMN_ORDERGROUPCOMPANY_ORDERSID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(shareID);

				qPos.add(companyId);

				qPos.add(ordersId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_ORDERGROUPCOMPANY_SHAREID_2 = "ibOrder.shareID = ? AND ";
	private static final String _FINDER_COLUMN_ORDERGROUPCOMPANY_COMPANYID_2 = "ibOrder.companyId = ? AND ";
	private static final String _FINDER_COLUMN_ORDERGROUPCOMPANY_ORDERSID_2 = "ibOrder.ordersId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ORDERCLIENTGROUPCOMPANY =
		new FinderPath(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderModelImpl.FINDER_CACHE_ENABLED, IBOrderImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByOrderClientGroupCompany",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERCLIENTGROUPCOMPANY =
		new FinderPath(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderModelImpl.FINDER_CACHE_ENABLED, IBOrderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByOrderClientGroupCompany",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName()
			},
			IBOrderModelImpl.ORDERSID_COLUMN_BITMASK |
			IBOrderModelImpl.COMPANYID_COLUMN_BITMASK |
			IBOrderModelImpl.GROUPID_COLUMN_BITMASK |
			IBOrderModelImpl.IBCLIENTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ORDERCLIENTGROUPCOMPANY = new FinderPath(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByOrderClientGroupCompany",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName()
			});

	/**
	 * Returns all the ib orders where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63;.
	 *
	 * @param ordersId the orders ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param ibclientId the ibclient ID
	 * @return the matching ib orders
	 */
	@Override
	public List<IBOrder> findByOrderClientGroupCompany(long ordersId,
		long companyId, long groupId, long ibclientId) {
		return findByOrderClientGroupCompany(ordersId, companyId, groupId,
			ibclientId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ib orders where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ordersId the orders ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param ibclientId the ibclient ID
	 * @param start the lower bound of the range of ib orders
	 * @param end the upper bound of the range of ib orders (not inclusive)
	 * @return the range of matching ib orders
	 */
	@Override
	public List<IBOrder> findByOrderClientGroupCompany(long ordersId,
		long companyId, long groupId, long ibclientId, int start, int end) {
		return findByOrderClientGroupCompany(ordersId, companyId, groupId,
			ibclientId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ib orders where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ordersId the orders ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param ibclientId the ibclient ID
	 * @param start the lower bound of the range of ib orders
	 * @param end the upper bound of the range of ib orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ib orders
	 */
	@Override
	public List<IBOrder> findByOrderClientGroupCompany(long ordersId,
		long companyId, long groupId, long ibclientId, int start, int end,
		OrderByComparator<IBOrder> orderByComparator) {
		return findByOrderClientGroupCompany(ordersId, companyId, groupId,
			ibclientId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ib orders where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ordersId the orders ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param ibclientId the ibclient ID
	 * @param start the lower bound of the range of ib orders
	 * @param end the upper bound of the range of ib orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ib orders
	 */
	@Override
	public List<IBOrder> findByOrderClientGroupCompany(long ordersId,
		long companyId, long groupId, long ibclientId, int start, int end,
		OrderByComparator<IBOrder> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERCLIENTGROUPCOMPANY;
			finderArgs = new Object[] { ordersId, companyId, groupId, ibclientId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ORDERCLIENTGROUPCOMPANY;
			finderArgs = new Object[] {
					ordersId, companyId, groupId, ibclientId,
					
					start, end, orderByComparator
				};
		}

		List<IBOrder> list = null;

		if (retrieveFromCache) {
			list = (List<IBOrder>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (IBOrder ibOrder : list) {
					if ((ordersId != ibOrder.getOrdersId()) ||
							(companyId != ibOrder.getCompanyId()) ||
							(groupId != ibOrder.getGroupId()) ||
							(ibclientId != ibOrder.getIbclientId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_IBORDER_WHERE);

			query.append(_FINDER_COLUMN_ORDERCLIENTGROUPCOMPANY_ORDERSID_2);

			query.append(_FINDER_COLUMN_ORDERCLIENTGROUPCOMPANY_COMPANYID_2);

			query.append(_FINDER_COLUMN_ORDERCLIENTGROUPCOMPANY_GROUPID_2);

			query.append(_FINDER_COLUMN_ORDERCLIENTGROUPCOMPANY_IBCLIENTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(IBOrderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ordersId);

				qPos.add(companyId);

				qPos.add(groupId);

				qPos.add(ibclientId);

				if (!pagination) {
					list = (List<IBOrder>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IBOrder>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first ib order in the ordered set where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63;.
	 *
	 * @param ordersId the orders ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param ibclientId the ibclient ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ib order
	 * @throws NoSuchIBOrderException if a matching ib order could not be found
	 */
	@Override
	public IBOrder findByOrderClientGroupCompany_First(long ordersId,
		long companyId, long groupId, long ibclientId,
		OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException {
		IBOrder ibOrder = fetchByOrderClientGroupCompany_First(ordersId,
				companyId, groupId, ibclientId, orderByComparator);

		if (ibOrder != null) {
			return ibOrder;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ordersId=");
		msg.append(ordersId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", ibclientId=");
		msg.append(ibclientId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIBOrderException(msg.toString());
	}

	/**
	 * Returns the first ib order in the ordered set where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63;.
	 *
	 * @param ordersId the orders ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param ibclientId the ibclient ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ib order, or <code>null</code> if a matching ib order could not be found
	 */
	@Override
	public IBOrder fetchByOrderClientGroupCompany_First(long ordersId,
		long companyId, long groupId, long ibclientId,
		OrderByComparator<IBOrder> orderByComparator) {
		List<IBOrder> list = findByOrderClientGroupCompany(ordersId, companyId,
				groupId, ibclientId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ib order in the ordered set where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63;.
	 *
	 * @param ordersId the orders ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param ibclientId the ibclient ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ib order
	 * @throws NoSuchIBOrderException if a matching ib order could not be found
	 */
	@Override
	public IBOrder findByOrderClientGroupCompany_Last(long ordersId,
		long companyId, long groupId, long ibclientId,
		OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException {
		IBOrder ibOrder = fetchByOrderClientGroupCompany_Last(ordersId,
				companyId, groupId, ibclientId, orderByComparator);

		if (ibOrder != null) {
			return ibOrder;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ordersId=");
		msg.append(ordersId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", ibclientId=");
		msg.append(ibclientId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIBOrderException(msg.toString());
	}

	/**
	 * Returns the last ib order in the ordered set where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63;.
	 *
	 * @param ordersId the orders ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param ibclientId the ibclient ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ib order, or <code>null</code> if a matching ib order could not be found
	 */
	@Override
	public IBOrder fetchByOrderClientGroupCompany_Last(long ordersId,
		long companyId, long groupId, long ibclientId,
		OrderByComparator<IBOrder> orderByComparator) {
		int count = countByOrderClientGroupCompany(ordersId, companyId,
				groupId, ibclientId);

		if (count == 0) {
			return null;
		}

		List<IBOrder> list = findByOrderClientGroupCompany(ordersId, companyId,
				groupId, ibclientId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ib orders before and after the current ib order in the ordered set where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63;.
	 *
	 * @param orderIdPk the primary key of the current ib order
	 * @param ordersId the orders ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param ibclientId the ibclient ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ib order
	 * @throws NoSuchIBOrderException if a ib order with the primary key could not be found
	 */
	@Override
	public IBOrder[] findByOrderClientGroupCompany_PrevAndNext(long orderIdPk,
		long ordersId, long companyId, long groupId, long ibclientId,
		OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException {
		IBOrder ibOrder = findByPrimaryKey(orderIdPk);

		Session session = null;

		try {
			session = openSession();

			IBOrder[] array = new IBOrderImpl[3];

			array[0] = getByOrderClientGroupCompany_PrevAndNext(session,
					ibOrder, ordersId, companyId, groupId, ibclientId,
					orderByComparator, true);

			array[1] = ibOrder;

			array[2] = getByOrderClientGroupCompany_PrevAndNext(session,
					ibOrder, ordersId, companyId, groupId, ibclientId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected IBOrder getByOrderClientGroupCompany_PrevAndNext(
		Session session, IBOrder ibOrder, long ordersId, long companyId,
		long groupId, long ibclientId,
		OrderByComparator<IBOrder> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_IBORDER_WHERE);

		query.append(_FINDER_COLUMN_ORDERCLIENTGROUPCOMPANY_ORDERSID_2);

		query.append(_FINDER_COLUMN_ORDERCLIENTGROUPCOMPANY_COMPANYID_2);

		query.append(_FINDER_COLUMN_ORDERCLIENTGROUPCOMPANY_GROUPID_2);

		query.append(_FINDER_COLUMN_ORDERCLIENTGROUPCOMPANY_IBCLIENTID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(IBOrderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ordersId);

		qPos.add(companyId);

		qPos.add(groupId);

		qPos.add(ibclientId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ibOrder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<IBOrder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ib orders where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63; from the database.
	 *
	 * @param ordersId the orders ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param ibclientId the ibclient ID
	 */
	@Override
	public void removeByOrderClientGroupCompany(long ordersId, long companyId,
		long groupId, long ibclientId) {
		for (IBOrder ibOrder : findByOrderClientGroupCompany(ordersId,
				companyId, groupId, ibclientId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(ibOrder);
		}
	}

	/**
	 * Returns the number of ib orders where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63;.
	 *
	 * @param ordersId the orders ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param ibclientId the ibclient ID
	 * @return the number of matching ib orders
	 */
	@Override
	public int countByOrderClientGroupCompany(long ordersId, long companyId,
		long groupId, long ibclientId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ORDERCLIENTGROUPCOMPANY;

		Object[] finderArgs = new Object[] {
				ordersId, companyId, groupId, ibclientId
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_IBORDER_WHERE);

			query.append(_FINDER_COLUMN_ORDERCLIENTGROUPCOMPANY_ORDERSID_2);

			query.append(_FINDER_COLUMN_ORDERCLIENTGROUPCOMPANY_COMPANYID_2);

			query.append(_FINDER_COLUMN_ORDERCLIENTGROUPCOMPANY_GROUPID_2);

			query.append(_FINDER_COLUMN_ORDERCLIENTGROUPCOMPANY_IBCLIENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ordersId);

				qPos.add(companyId);

				qPos.add(groupId);

				qPos.add(ibclientId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_ORDERCLIENTGROUPCOMPANY_ORDERSID_2 =
		"ibOrder.ordersId = ? AND ";
	private static final String _FINDER_COLUMN_ORDERCLIENTGROUPCOMPANY_COMPANYID_2 =
		"ibOrder.companyId = ? AND ";
	private static final String _FINDER_COLUMN_ORDERCLIENTGROUPCOMPANY_GROUPID_2 =
		"ibOrder.groupId = ? AND ";
	private static final String _FINDER_COLUMN_ORDERCLIENTGROUPCOMPANY_IBCLIENTID_2 =
		"ibOrder.ibclientId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ORDERSHARECLIENTGROUPCOMPANY =
		new FinderPath(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderModelImpl.FINDER_CACHE_ENABLED, IBOrderImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByOrderShareClientGroupCompany",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERSHARECLIENTGROUPCOMPANY =
		new FinderPath(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderModelImpl.FINDER_CACHE_ENABLED, IBOrderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByOrderShareClientGroupCompany",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Long.class.getName()
			},
			IBOrderModelImpl.ORDERSID_COLUMN_BITMASK |
			IBOrderModelImpl.COMPANYID_COLUMN_BITMASK |
			IBOrderModelImpl.SHAREID_COLUMN_BITMASK |
			IBOrderModelImpl.GROUPID_COLUMN_BITMASK |
			IBOrderModelImpl.IBCLIENTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ORDERSHARECLIENTGROUPCOMPANY =
		new FinderPath(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByOrderShareClientGroupCompany",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the ib orders where ordersId = &#63; and companyId = &#63; and shareID = &#63; and groupId = &#63; and ibclientId = &#63;.
	 *
	 * @param ordersId the orders ID
	 * @param companyId the company ID
	 * @param shareID the share ID
	 * @param groupId the group ID
	 * @param ibclientId the ibclient ID
	 * @return the matching ib orders
	 */
	@Override
	public List<IBOrder> findByOrderShareClientGroupCompany(long ordersId,
		long companyId, long shareID, long groupId, long ibclientId) {
		return findByOrderShareClientGroupCompany(ordersId, companyId, shareID,
			groupId, ibclientId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ib orders where ordersId = &#63; and companyId = &#63; and shareID = &#63; and groupId = &#63; and ibclientId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ordersId the orders ID
	 * @param companyId the company ID
	 * @param shareID the share ID
	 * @param groupId the group ID
	 * @param ibclientId the ibclient ID
	 * @param start the lower bound of the range of ib orders
	 * @param end the upper bound of the range of ib orders (not inclusive)
	 * @return the range of matching ib orders
	 */
	@Override
	public List<IBOrder> findByOrderShareClientGroupCompany(long ordersId,
		long companyId, long shareID, long groupId, long ibclientId, int start,
		int end) {
		return findByOrderShareClientGroupCompany(ordersId, companyId, shareID,
			groupId, ibclientId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ib orders where ordersId = &#63; and companyId = &#63; and shareID = &#63; and groupId = &#63; and ibclientId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ordersId the orders ID
	 * @param companyId the company ID
	 * @param shareID the share ID
	 * @param groupId the group ID
	 * @param ibclientId the ibclient ID
	 * @param start the lower bound of the range of ib orders
	 * @param end the upper bound of the range of ib orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ib orders
	 */
	@Override
	public List<IBOrder> findByOrderShareClientGroupCompany(long ordersId,
		long companyId, long shareID, long groupId, long ibclientId, int start,
		int end, OrderByComparator<IBOrder> orderByComparator) {
		return findByOrderShareClientGroupCompany(ordersId, companyId, shareID,
			groupId, ibclientId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ib orders where ordersId = &#63; and companyId = &#63; and shareID = &#63; and groupId = &#63; and ibclientId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ordersId the orders ID
	 * @param companyId the company ID
	 * @param shareID the share ID
	 * @param groupId the group ID
	 * @param ibclientId the ibclient ID
	 * @param start the lower bound of the range of ib orders
	 * @param end the upper bound of the range of ib orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ib orders
	 */
	@Override
	public List<IBOrder> findByOrderShareClientGroupCompany(long ordersId,
		long companyId, long shareID, long groupId, long ibclientId, int start,
		int end, OrderByComparator<IBOrder> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERSHARECLIENTGROUPCOMPANY;
			finderArgs = new Object[] {
					ordersId, companyId, shareID, groupId, ibclientId
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ORDERSHARECLIENTGROUPCOMPANY;
			finderArgs = new Object[] {
					ordersId, companyId, shareID, groupId, ibclientId,
					
					start, end, orderByComparator
				};
		}

		List<IBOrder> list = null;

		if (retrieveFromCache) {
			list = (List<IBOrder>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (IBOrder ibOrder : list) {
					if ((ordersId != ibOrder.getOrdersId()) ||
							(companyId != ibOrder.getCompanyId()) ||
							(shareID != ibOrder.getShareID()) ||
							(groupId != ibOrder.getGroupId()) ||
							(ibclientId != ibOrder.getIbclientId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(7 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(7);
			}

			query.append(_SQL_SELECT_IBORDER_WHERE);

			query.append(_FINDER_COLUMN_ORDERSHARECLIENTGROUPCOMPANY_ORDERSID_2);

			query.append(_FINDER_COLUMN_ORDERSHARECLIENTGROUPCOMPANY_COMPANYID_2);

			query.append(_FINDER_COLUMN_ORDERSHARECLIENTGROUPCOMPANY_SHAREID_2);

			query.append(_FINDER_COLUMN_ORDERSHARECLIENTGROUPCOMPANY_GROUPID_2);

			query.append(_FINDER_COLUMN_ORDERSHARECLIENTGROUPCOMPANY_IBCLIENTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(IBOrderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ordersId);

				qPos.add(companyId);

				qPos.add(shareID);

				qPos.add(groupId);

				qPos.add(ibclientId);

				if (!pagination) {
					list = (List<IBOrder>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IBOrder>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first ib order in the ordered set where ordersId = &#63; and companyId = &#63; and shareID = &#63; and groupId = &#63; and ibclientId = &#63;.
	 *
	 * @param ordersId the orders ID
	 * @param companyId the company ID
	 * @param shareID the share ID
	 * @param groupId the group ID
	 * @param ibclientId the ibclient ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ib order
	 * @throws NoSuchIBOrderException if a matching ib order could not be found
	 */
	@Override
	public IBOrder findByOrderShareClientGroupCompany_First(long ordersId,
		long companyId, long shareID, long groupId, long ibclientId,
		OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException {
		IBOrder ibOrder = fetchByOrderShareClientGroupCompany_First(ordersId,
				companyId, shareID, groupId, ibclientId, orderByComparator);

		if (ibOrder != null) {
			return ibOrder;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ordersId=");
		msg.append(ordersId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", shareID=");
		msg.append(shareID);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", ibclientId=");
		msg.append(ibclientId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIBOrderException(msg.toString());
	}

	/**
	 * Returns the first ib order in the ordered set where ordersId = &#63; and companyId = &#63; and shareID = &#63; and groupId = &#63; and ibclientId = &#63;.
	 *
	 * @param ordersId the orders ID
	 * @param companyId the company ID
	 * @param shareID the share ID
	 * @param groupId the group ID
	 * @param ibclientId the ibclient ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ib order, or <code>null</code> if a matching ib order could not be found
	 */
	@Override
	public IBOrder fetchByOrderShareClientGroupCompany_First(long ordersId,
		long companyId, long shareID, long groupId, long ibclientId,
		OrderByComparator<IBOrder> orderByComparator) {
		List<IBOrder> list = findByOrderShareClientGroupCompany(ordersId,
				companyId, shareID, groupId, ibclientId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ib order in the ordered set where ordersId = &#63; and companyId = &#63; and shareID = &#63; and groupId = &#63; and ibclientId = &#63;.
	 *
	 * @param ordersId the orders ID
	 * @param companyId the company ID
	 * @param shareID the share ID
	 * @param groupId the group ID
	 * @param ibclientId the ibclient ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ib order
	 * @throws NoSuchIBOrderException if a matching ib order could not be found
	 */
	@Override
	public IBOrder findByOrderShareClientGroupCompany_Last(long ordersId,
		long companyId, long shareID, long groupId, long ibclientId,
		OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException {
		IBOrder ibOrder = fetchByOrderShareClientGroupCompany_Last(ordersId,
				companyId, shareID, groupId, ibclientId, orderByComparator);

		if (ibOrder != null) {
			return ibOrder;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ordersId=");
		msg.append(ordersId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", shareID=");
		msg.append(shareID);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", ibclientId=");
		msg.append(ibclientId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIBOrderException(msg.toString());
	}

	/**
	 * Returns the last ib order in the ordered set where ordersId = &#63; and companyId = &#63; and shareID = &#63; and groupId = &#63; and ibclientId = &#63;.
	 *
	 * @param ordersId the orders ID
	 * @param companyId the company ID
	 * @param shareID the share ID
	 * @param groupId the group ID
	 * @param ibclientId the ibclient ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ib order, or <code>null</code> if a matching ib order could not be found
	 */
	@Override
	public IBOrder fetchByOrderShareClientGroupCompany_Last(long ordersId,
		long companyId, long shareID, long groupId, long ibclientId,
		OrderByComparator<IBOrder> orderByComparator) {
		int count = countByOrderShareClientGroupCompany(ordersId, companyId,
				shareID, groupId, ibclientId);

		if (count == 0) {
			return null;
		}

		List<IBOrder> list = findByOrderShareClientGroupCompany(ordersId,
				companyId, shareID, groupId, ibclientId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ib orders before and after the current ib order in the ordered set where ordersId = &#63; and companyId = &#63; and shareID = &#63; and groupId = &#63; and ibclientId = &#63;.
	 *
	 * @param orderIdPk the primary key of the current ib order
	 * @param ordersId the orders ID
	 * @param companyId the company ID
	 * @param shareID the share ID
	 * @param groupId the group ID
	 * @param ibclientId the ibclient ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ib order
	 * @throws NoSuchIBOrderException if a ib order with the primary key could not be found
	 */
	@Override
	public IBOrder[] findByOrderShareClientGroupCompany_PrevAndNext(
		long orderIdPk, long ordersId, long companyId, long shareID,
		long groupId, long ibclientId,
		OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException {
		IBOrder ibOrder = findByPrimaryKey(orderIdPk);

		Session session = null;

		try {
			session = openSession();

			IBOrder[] array = new IBOrderImpl[3];

			array[0] = getByOrderShareClientGroupCompany_PrevAndNext(session,
					ibOrder, ordersId, companyId, shareID, groupId, ibclientId,
					orderByComparator, true);

			array[1] = ibOrder;

			array[2] = getByOrderShareClientGroupCompany_PrevAndNext(session,
					ibOrder, ordersId, companyId, shareID, groupId, ibclientId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected IBOrder getByOrderShareClientGroupCompany_PrevAndNext(
		Session session, IBOrder ibOrder, long ordersId, long companyId,
		long shareID, long groupId, long ibclientId,
		OrderByComparator<IBOrder> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(8 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(7);
		}

		query.append(_SQL_SELECT_IBORDER_WHERE);

		query.append(_FINDER_COLUMN_ORDERSHARECLIENTGROUPCOMPANY_ORDERSID_2);

		query.append(_FINDER_COLUMN_ORDERSHARECLIENTGROUPCOMPANY_COMPANYID_2);

		query.append(_FINDER_COLUMN_ORDERSHARECLIENTGROUPCOMPANY_SHAREID_2);

		query.append(_FINDER_COLUMN_ORDERSHARECLIENTGROUPCOMPANY_GROUPID_2);

		query.append(_FINDER_COLUMN_ORDERSHARECLIENTGROUPCOMPANY_IBCLIENTID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(IBOrderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ordersId);

		qPos.add(companyId);

		qPos.add(shareID);

		qPos.add(groupId);

		qPos.add(ibclientId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ibOrder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<IBOrder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ib orders where ordersId = &#63; and companyId = &#63; and shareID = &#63; and groupId = &#63; and ibclientId = &#63; from the database.
	 *
	 * @param ordersId the orders ID
	 * @param companyId the company ID
	 * @param shareID the share ID
	 * @param groupId the group ID
	 * @param ibclientId the ibclient ID
	 */
	@Override
	public void removeByOrderShareClientGroupCompany(long ordersId,
		long companyId, long shareID, long groupId, long ibclientId) {
		for (IBOrder ibOrder : findByOrderShareClientGroupCompany(ordersId,
				companyId, shareID, groupId, ibclientId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(ibOrder);
		}
	}

	/**
	 * Returns the number of ib orders where ordersId = &#63; and companyId = &#63; and shareID = &#63; and groupId = &#63; and ibclientId = &#63;.
	 *
	 * @param ordersId the orders ID
	 * @param companyId the company ID
	 * @param shareID the share ID
	 * @param groupId the group ID
	 * @param ibclientId the ibclient ID
	 * @return the number of matching ib orders
	 */
	@Override
	public int countByOrderShareClientGroupCompany(long ordersId,
		long companyId, long shareID, long groupId, long ibclientId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ORDERSHARECLIENTGROUPCOMPANY;

		Object[] finderArgs = new Object[] {
				ordersId, companyId, shareID, groupId, ibclientId
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_IBORDER_WHERE);

			query.append(_FINDER_COLUMN_ORDERSHARECLIENTGROUPCOMPANY_ORDERSID_2);

			query.append(_FINDER_COLUMN_ORDERSHARECLIENTGROUPCOMPANY_COMPANYID_2);

			query.append(_FINDER_COLUMN_ORDERSHARECLIENTGROUPCOMPANY_SHAREID_2);

			query.append(_FINDER_COLUMN_ORDERSHARECLIENTGROUPCOMPANY_GROUPID_2);

			query.append(_FINDER_COLUMN_ORDERSHARECLIENTGROUPCOMPANY_IBCLIENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ordersId);

				qPos.add(companyId);

				qPos.add(shareID);

				qPos.add(groupId);

				qPos.add(ibclientId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_ORDERSHARECLIENTGROUPCOMPANY_ORDERSID_2 =
		"ibOrder.ordersId = ? AND ";
	private static final String _FINDER_COLUMN_ORDERSHARECLIENTGROUPCOMPANY_COMPANYID_2 =
		"ibOrder.companyId = ? AND ";
	private static final String _FINDER_COLUMN_ORDERSHARECLIENTGROUPCOMPANY_SHAREID_2 =
		"ibOrder.shareID = ? AND ";
	private static final String _FINDER_COLUMN_ORDERSHARECLIENTGROUPCOMPANY_GROUPID_2 =
		"ibOrder.groupId = ? AND ";
	private static final String _FINDER_COLUMN_ORDERSHARECLIENTGROUPCOMPANY_IBCLIENTID_2 =
		"ibOrder.ibclientId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_REMOVABLEDATEGROUPCOMPANY =
		new FinderPath(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderModelImpl.FINDER_CACHE_ENABLED, IBOrderImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByRemovableDateGroupCompany",
			new String[] {
				Boolean.class.getName(), Long.class.getName(),
				Long.class.getName(), Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REMOVABLEDATEGROUPCOMPANY =
		new FinderPath(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderModelImpl.FINDER_CACHE_ENABLED, IBOrderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByRemovableDateGroupCompany",
			new String[] {
				Boolean.class.getName(), Long.class.getName(),
				Long.class.getName(), Date.class.getName()
			},
			IBOrderModelImpl.REMOVABLE_ON_REBOOT_COLUMN_BITMASK |
			IBOrderModelImpl.COMPANYID_COLUMN_BITMASK |
			IBOrderModelImpl.GROUPID_COLUMN_BITMASK |
			IBOrderModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_REMOVABLEDATEGROUPCOMPANY =
		new FinderPath(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByRemovableDateGroupCompany",
			new String[] {
				Boolean.class.getName(), Long.class.getName(),
				Long.class.getName(), Date.class.getName()
			});

	/**
	 * Returns all the ib orders where removable_on_reboot = &#63; and companyId = &#63; and groupId = &#63; and createDate = &#63;.
	 *
	 * @param removable_on_reboot the removable_on_reboot
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @return the matching ib orders
	 */
	@Override
	public List<IBOrder> findByRemovableDateGroupCompany(
		boolean removable_on_reboot, long companyId, long groupId,
		Date createDate) {
		return findByRemovableDateGroupCompany(removable_on_reboot, companyId,
			groupId, createDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ib orders where removable_on_reboot = &#63; and companyId = &#63; and groupId = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param removable_on_reboot the removable_on_reboot
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param start the lower bound of the range of ib orders
	 * @param end the upper bound of the range of ib orders (not inclusive)
	 * @return the range of matching ib orders
	 */
	@Override
	public List<IBOrder> findByRemovableDateGroupCompany(
		boolean removable_on_reboot, long companyId, long groupId,
		Date createDate, int start, int end) {
		return findByRemovableDateGroupCompany(removable_on_reboot, companyId,
			groupId, createDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ib orders where removable_on_reboot = &#63; and companyId = &#63; and groupId = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param removable_on_reboot the removable_on_reboot
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param start the lower bound of the range of ib orders
	 * @param end the upper bound of the range of ib orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ib orders
	 */
	@Override
	public List<IBOrder> findByRemovableDateGroupCompany(
		boolean removable_on_reboot, long companyId, long groupId,
		Date createDate, int start, int end,
		OrderByComparator<IBOrder> orderByComparator) {
		return findByRemovableDateGroupCompany(removable_on_reboot, companyId,
			groupId, createDate, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ib orders where removable_on_reboot = &#63; and companyId = &#63; and groupId = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param removable_on_reboot the removable_on_reboot
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param start the lower bound of the range of ib orders
	 * @param end the upper bound of the range of ib orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ib orders
	 */
	@Override
	public List<IBOrder> findByRemovableDateGroupCompany(
		boolean removable_on_reboot, long companyId, long groupId,
		Date createDate, int start, int end,
		OrderByComparator<IBOrder> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REMOVABLEDATEGROUPCOMPANY;
			finderArgs = new Object[] {
					removable_on_reboot, companyId, groupId, createDate
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_REMOVABLEDATEGROUPCOMPANY;
			finderArgs = new Object[] {
					removable_on_reboot, companyId, groupId, createDate,
					
					start, end, orderByComparator
				};
		}

		List<IBOrder> list = null;

		if (retrieveFromCache) {
			list = (List<IBOrder>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (IBOrder ibOrder : list) {
					if ((removable_on_reboot != ibOrder.getRemovable_on_reboot()) ||
							(companyId != ibOrder.getCompanyId()) ||
							(groupId != ibOrder.getGroupId()) ||
							!Objects.equals(createDate, ibOrder.getCreateDate())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_IBORDER_WHERE);

			query.append(_FINDER_COLUMN_REMOVABLEDATEGROUPCOMPANY_REMOVABLE_ON_REBOOT_2);

			query.append(_FINDER_COLUMN_REMOVABLEDATEGROUPCOMPANY_COMPANYID_2);

			query.append(_FINDER_COLUMN_REMOVABLEDATEGROUPCOMPANY_GROUPID_2);

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_REMOVABLEDATEGROUPCOMPANY_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_REMOVABLEDATEGROUPCOMPANY_CREATEDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(IBOrderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(removable_on_reboot);

				qPos.add(companyId);

				qPos.add(groupId);

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
				}

				if (!pagination) {
					list = (List<IBOrder>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IBOrder>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first ib order in the ordered set where removable_on_reboot = &#63; and companyId = &#63; and groupId = &#63; and createDate = &#63;.
	 *
	 * @param removable_on_reboot the removable_on_reboot
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ib order
	 * @throws NoSuchIBOrderException if a matching ib order could not be found
	 */
	@Override
	public IBOrder findByRemovableDateGroupCompany_First(
		boolean removable_on_reboot, long companyId, long groupId,
		Date createDate, OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException {
		IBOrder ibOrder = fetchByRemovableDateGroupCompany_First(removable_on_reboot,
				companyId, groupId, createDate, orderByComparator);

		if (ibOrder != null) {
			return ibOrder;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("removable_on_reboot=");
		msg.append(removable_on_reboot);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", createDate=");
		msg.append(createDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIBOrderException(msg.toString());
	}

	/**
	 * Returns the first ib order in the ordered set where removable_on_reboot = &#63; and companyId = &#63; and groupId = &#63; and createDate = &#63;.
	 *
	 * @param removable_on_reboot the removable_on_reboot
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ib order, or <code>null</code> if a matching ib order could not be found
	 */
	@Override
	public IBOrder fetchByRemovableDateGroupCompany_First(
		boolean removable_on_reboot, long companyId, long groupId,
		Date createDate, OrderByComparator<IBOrder> orderByComparator) {
		List<IBOrder> list = findByRemovableDateGroupCompany(removable_on_reboot,
				companyId, groupId, createDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ib order in the ordered set where removable_on_reboot = &#63; and companyId = &#63; and groupId = &#63; and createDate = &#63;.
	 *
	 * @param removable_on_reboot the removable_on_reboot
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ib order
	 * @throws NoSuchIBOrderException if a matching ib order could not be found
	 */
	@Override
	public IBOrder findByRemovableDateGroupCompany_Last(
		boolean removable_on_reboot, long companyId, long groupId,
		Date createDate, OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException {
		IBOrder ibOrder = fetchByRemovableDateGroupCompany_Last(removable_on_reboot,
				companyId, groupId, createDate, orderByComparator);

		if (ibOrder != null) {
			return ibOrder;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("removable_on_reboot=");
		msg.append(removable_on_reboot);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", createDate=");
		msg.append(createDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIBOrderException(msg.toString());
	}

	/**
	 * Returns the last ib order in the ordered set where removable_on_reboot = &#63; and companyId = &#63; and groupId = &#63; and createDate = &#63;.
	 *
	 * @param removable_on_reboot the removable_on_reboot
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ib order, or <code>null</code> if a matching ib order could not be found
	 */
	@Override
	public IBOrder fetchByRemovableDateGroupCompany_Last(
		boolean removable_on_reboot, long companyId, long groupId,
		Date createDate, OrderByComparator<IBOrder> orderByComparator) {
		int count = countByRemovableDateGroupCompany(removable_on_reboot,
				companyId, groupId, createDate);

		if (count == 0) {
			return null;
		}

		List<IBOrder> list = findByRemovableDateGroupCompany(removable_on_reboot,
				companyId, groupId, createDate, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ib orders before and after the current ib order in the ordered set where removable_on_reboot = &#63; and companyId = &#63; and groupId = &#63; and createDate = &#63;.
	 *
	 * @param orderIdPk the primary key of the current ib order
	 * @param removable_on_reboot the removable_on_reboot
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ib order
	 * @throws NoSuchIBOrderException if a ib order with the primary key could not be found
	 */
	@Override
	public IBOrder[] findByRemovableDateGroupCompany_PrevAndNext(
		long orderIdPk, boolean removable_on_reboot, long companyId,
		long groupId, Date createDate,
		OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException {
		IBOrder ibOrder = findByPrimaryKey(orderIdPk);

		Session session = null;

		try {
			session = openSession();

			IBOrder[] array = new IBOrderImpl[3];

			array[0] = getByRemovableDateGroupCompany_PrevAndNext(session,
					ibOrder, removable_on_reboot, companyId, groupId,
					createDate, orderByComparator, true);

			array[1] = ibOrder;

			array[2] = getByRemovableDateGroupCompany_PrevAndNext(session,
					ibOrder, removable_on_reboot, companyId, groupId,
					createDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected IBOrder getByRemovableDateGroupCompany_PrevAndNext(
		Session session, IBOrder ibOrder, boolean removable_on_reboot,
		long companyId, long groupId, Date createDate,
		OrderByComparator<IBOrder> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_IBORDER_WHERE);

		query.append(_FINDER_COLUMN_REMOVABLEDATEGROUPCOMPANY_REMOVABLE_ON_REBOOT_2);

		query.append(_FINDER_COLUMN_REMOVABLEDATEGROUPCOMPANY_COMPANYID_2);

		query.append(_FINDER_COLUMN_REMOVABLEDATEGROUPCOMPANY_GROUPID_2);

		boolean bindCreateDate = false;

		if (createDate == null) {
			query.append(_FINDER_COLUMN_REMOVABLEDATEGROUPCOMPANY_CREATEDATE_1);
		}
		else {
			bindCreateDate = true;

			query.append(_FINDER_COLUMN_REMOVABLEDATEGROUPCOMPANY_CREATEDATE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(IBOrderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(removable_on_reboot);

		qPos.add(companyId);

		qPos.add(groupId);

		if (bindCreateDate) {
			qPos.add(new Timestamp(createDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ibOrder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<IBOrder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ib orders where removable_on_reboot = &#63; and companyId = &#63; and groupId = &#63; and createDate = &#63; from the database.
	 *
	 * @param removable_on_reboot the removable_on_reboot
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param createDate the create date
	 */
	@Override
	public void removeByRemovableDateGroupCompany(boolean removable_on_reboot,
		long companyId, long groupId, Date createDate) {
		for (IBOrder ibOrder : findByRemovableDateGroupCompany(
				removable_on_reboot, companyId, groupId, createDate,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ibOrder);
		}
	}

	/**
	 * Returns the number of ib orders where removable_on_reboot = &#63; and companyId = &#63; and groupId = &#63; and createDate = &#63;.
	 *
	 * @param removable_on_reboot the removable_on_reboot
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @return the number of matching ib orders
	 */
	@Override
	public int countByRemovableDateGroupCompany(boolean removable_on_reboot,
		long companyId, long groupId, Date createDate) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_REMOVABLEDATEGROUPCOMPANY;

		Object[] finderArgs = new Object[] {
				removable_on_reboot, companyId, groupId, createDate
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_IBORDER_WHERE);

			query.append(_FINDER_COLUMN_REMOVABLEDATEGROUPCOMPANY_REMOVABLE_ON_REBOOT_2);

			query.append(_FINDER_COLUMN_REMOVABLEDATEGROUPCOMPANY_COMPANYID_2);

			query.append(_FINDER_COLUMN_REMOVABLEDATEGROUPCOMPANY_GROUPID_2);

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_REMOVABLEDATEGROUPCOMPANY_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_REMOVABLEDATEGROUPCOMPANY_CREATEDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(removable_on_reboot);

				qPos.add(companyId);

				qPos.add(groupId);

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_REMOVABLEDATEGROUPCOMPANY_REMOVABLE_ON_REBOOT_2 =
		"ibOrder.removable_on_reboot = ? AND ";
	private static final String _FINDER_COLUMN_REMOVABLEDATEGROUPCOMPANY_COMPANYID_2 =
		"ibOrder.companyId = ? AND ";
	private static final String _FINDER_COLUMN_REMOVABLEDATEGROUPCOMPANY_GROUPID_2 =
		"ibOrder.groupId = ? AND ";
	private static final String _FINDER_COLUMN_REMOVABLEDATEGROUPCOMPANY_CREATEDATE_1 =
		"ibOrder.createDate IS NULL";
	private static final String _FINDER_COLUMN_REMOVABLEDATEGROUPCOMPANY_CREATEDATE_2 =
		"ibOrder.createDate = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ORDERCLIENT =
		new FinderPath(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderModelImpl.FINDER_CACHE_ENABLED, IBOrderImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOrderClient",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERCLIENT =
		new FinderPath(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderModelImpl.FINDER_CACHE_ENABLED, IBOrderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOrderClient",
			new String[] { Long.class.getName(), Long.class.getName() },
			IBOrderModelImpl.ORDERSID_COLUMN_BITMASK |
			IBOrderModelImpl.IBCLIENTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ORDERCLIENT = new FinderPath(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOrderClient",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the ib orders where ordersId = &#63; and ibclientId = &#63;.
	 *
	 * @param ordersId the orders ID
	 * @param ibclientId the ibclient ID
	 * @return the matching ib orders
	 */
	@Override
	public List<IBOrder> findByOrderClient(long ordersId, long ibclientId) {
		return findByOrderClient(ordersId, ibclientId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ib orders where ordersId = &#63; and ibclientId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ordersId the orders ID
	 * @param ibclientId the ibclient ID
	 * @param start the lower bound of the range of ib orders
	 * @param end the upper bound of the range of ib orders (not inclusive)
	 * @return the range of matching ib orders
	 */
	@Override
	public List<IBOrder> findByOrderClient(long ordersId, long ibclientId,
		int start, int end) {
		return findByOrderClient(ordersId, ibclientId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ib orders where ordersId = &#63; and ibclientId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ordersId the orders ID
	 * @param ibclientId the ibclient ID
	 * @param start the lower bound of the range of ib orders
	 * @param end the upper bound of the range of ib orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ib orders
	 */
	@Override
	public List<IBOrder> findByOrderClient(long ordersId, long ibclientId,
		int start, int end, OrderByComparator<IBOrder> orderByComparator) {
		return findByOrderClient(ordersId, ibclientId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ib orders where ordersId = &#63; and ibclientId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ordersId the orders ID
	 * @param ibclientId the ibclient ID
	 * @param start the lower bound of the range of ib orders
	 * @param end the upper bound of the range of ib orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ib orders
	 */
	@Override
	public List<IBOrder> findByOrderClient(long ordersId, long ibclientId,
		int start, int end, OrderByComparator<IBOrder> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERCLIENT;
			finderArgs = new Object[] { ordersId, ibclientId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ORDERCLIENT;
			finderArgs = new Object[] {
					ordersId, ibclientId,
					
					start, end, orderByComparator
				};
		}

		List<IBOrder> list = null;

		if (retrieveFromCache) {
			list = (List<IBOrder>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (IBOrder ibOrder : list) {
					if ((ordersId != ibOrder.getOrdersId()) ||
							(ibclientId != ibOrder.getIbclientId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_IBORDER_WHERE);

			query.append(_FINDER_COLUMN_ORDERCLIENT_ORDERSID_2);

			query.append(_FINDER_COLUMN_ORDERCLIENT_IBCLIENTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(IBOrderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ordersId);

				qPos.add(ibclientId);

				if (!pagination) {
					list = (List<IBOrder>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IBOrder>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first ib order in the ordered set where ordersId = &#63; and ibclientId = &#63;.
	 *
	 * @param ordersId the orders ID
	 * @param ibclientId the ibclient ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ib order
	 * @throws NoSuchIBOrderException if a matching ib order could not be found
	 */
	@Override
	public IBOrder findByOrderClient_First(long ordersId, long ibclientId,
		OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException {
		IBOrder ibOrder = fetchByOrderClient_First(ordersId, ibclientId,
				orderByComparator);

		if (ibOrder != null) {
			return ibOrder;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ordersId=");
		msg.append(ordersId);

		msg.append(", ibclientId=");
		msg.append(ibclientId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIBOrderException(msg.toString());
	}

	/**
	 * Returns the first ib order in the ordered set where ordersId = &#63; and ibclientId = &#63;.
	 *
	 * @param ordersId the orders ID
	 * @param ibclientId the ibclient ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ib order, or <code>null</code> if a matching ib order could not be found
	 */
	@Override
	public IBOrder fetchByOrderClient_First(long ordersId, long ibclientId,
		OrderByComparator<IBOrder> orderByComparator) {
		List<IBOrder> list = findByOrderClient(ordersId, ibclientId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ib order in the ordered set where ordersId = &#63; and ibclientId = &#63;.
	 *
	 * @param ordersId the orders ID
	 * @param ibclientId the ibclient ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ib order
	 * @throws NoSuchIBOrderException if a matching ib order could not be found
	 */
	@Override
	public IBOrder findByOrderClient_Last(long ordersId, long ibclientId,
		OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException {
		IBOrder ibOrder = fetchByOrderClient_Last(ordersId, ibclientId,
				orderByComparator);

		if (ibOrder != null) {
			return ibOrder;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ordersId=");
		msg.append(ordersId);

		msg.append(", ibclientId=");
		msg.append(ibclientId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIBOrderException(msg.toString());
	}

	/**
	 * Returns the last ib order in the ordered set where ordersId = &#63; and ibclientId = &#63;.
	 *
	 * @param ordersId the orders ID
	 * @param ibclientId the ibclient ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ib order, or <code>null</code> if a matching ib order could not be found
	 */
	@Override
	public IBOrder fetchByOrderClient_Last(long ordersId, long ibclientId,
		OrderByComparator<IBOrder> orderByComparator) {
		int count = countByOrderClient(ordersId, ibclientId);

		if (count == 0) {
			return null;
		}

		List<IBOrder> list = findByOrderClient(ordersId, ibclientId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ib orders before and after the current ib order in the ordered set where ordersId = &#63; and ibclientId = &#63;.
	 *
	 * @param orderIdPk the primary key of the current ib order
	 * @param ordersId the orders ID
	 * @param ibclientId the ibclient ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ib order
	 * @throws NoSuchIBOrderException if a ib order with the primary key could not be found
	 */
	@Override
	public IBOrder[] findByOrderClient_PrevAndNext(long orderIdPk,
		long ordersId, long ibclientId,
		OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException {
		IBOrder ibOrder = findByPrimaryKey(orderIdPk);

		Session session = null;

		try {
			session = openSession();

			IBOrder[] array = new IBOrderImpl[3];

			array[0] = getByOrderClient_PrevAndNext(session, ibOrder, ordersId,
					ibclientId, orderByComparator, true);

			array[1] = ibOrder;

			array[2] = getByOrderClient_PrevAndNext(session, ibOrder, ordersId,
					ibclientId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected IBOrder getByOrderClient_PrevAndNext(Session session,
		IBOrder ibOrder, long ordersId, long ibclientId,
		OrderByComparator<IBOrder> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_IBORDER_WHERE);

		query.append(_FINDER_COLUMN_ORDERCLIENT_ORDERSID_2);

		query.append(_FINDER_COLUMN_ORDERCLIENT_IBCLIENTID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(IBOrderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ordersId);

		qPos.add(ibclientId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ibOrder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<IBOrder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ib orders where ordersId = &#63; and ibclientId = &#63; from the database.
	 *
	 * @param ordersId the orders ID
	 * @param ibclientId the ibclient ID
	 */
	@Override
	public void removeByOrderClient(long ordersId, long ibclientId) {
		for (IBOrder ibOrder : findByOrderClient(ordersId, ibclientId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ibOrder);
		}
	}

	/**
	 * Returns the number of ib orders where ordersId = &#63; and ibclientId = &#63;.
	 *
	 * @param ordersId the orders ID
	 * @param ibclientId the ibclient ID
	 * @return the number of matching ib orders
	 */
	@Override
	public int countByOrderClient(long ordersId, long ibclientId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ORDERCLIENT;

		Object[] finderArgs = new Object[] { ordersId, ibclientId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_IBORDER_WHERE);

			query.append(_FINDER_COLUMN_ORDERCLIENT_ORDERSID_2);

			query.append(_FINDER_COLUMN_ORDERCLIENT_IBCLIENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ordersId);

				qPos.add(ibclientId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_ORDERCLIENT_ORDERSID_2 = "ibOrder.ordersId = ? AND ";
	private static final String _FINDER_COLUMN_ORDERCLIENT_IBCLIENTID_2 = "ibOrder.ibclientId = ?";

	public IBOrderPersistenceImpl() {
		setModelClass(IBOrder.class);
	}

	/**
	 * Caches the ib order in the entity cache if it is enabled.
	 *
	 * @param ibOrder the ib order
	 */
	@Override
	public void cacheResult(IBOrder ibOrder) {
		entityCache.putResult(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderImpl.class, ibOrder.getPrimaryKey(), ibOrder);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { ibOrder.getUuid(), ibOrder.getGroupId() }, ibOrder);

		ibOrder.resetOriginalValues();
	}

	/**
	 * Caches the ib orders in the entity cache if it is enabled.
	 *
	 * @param ibOrders the ib orders
	 */
	@Override
	public void cacheResult(List<IBOrder> ibOrders) {
		for (IBOrder ibOrder : ibOrders) {
			if (entityCache.getResult(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
						IBOrderImpl.class, ibOrder.getPrimaryKey()) == null) {
				cacheResult(ibOrder);
			}
			else {
				ibOrder.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ib orders.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IBOrderImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ib order.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IBOrder ibOrder) {
		entityCache.removeResult(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderImpl.class, ibOrder.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((IBOrderModelImpl)ibOrder, true);
	}

	@Override
	public void clearCache(List<IBOrder> ibOrders) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IBOrder ibOrder : ibOrders) {
			entityCache.removeResult(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
				IBOrderImpl.class, ibOrder.getPrimaryKey());

			clearUniqueFindersCache((IBOrderModelImpl)ibOrder, true);
		}
	}

	protected void cacheUniqueFindersCache(IBOrderModelImpl ibOrderModelImpl) {
		Object[] args = new Object[] {
				ibOrderModelImpl.getUuid(), ibOrderModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			ibOrderModelImpl, false);
	}

	protected void clearUniqueFindersCache(IBOrderModelImpl ibOrderModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					ibOrderModelImpl.getUuid(), ibOrderModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((ibOrderModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					ibOrderModelImpl.getOriginalUuid(),
					ibOrderModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new ib order with the primary key. Does not add the ib order to the database.
	 *
	 * @param orderIdPk the primary key for the new ib order
	 * @return the new ib order
	 */
	@Override
	public IBOrder create(long orderIdPk) {
		IBOrder ibOrder = new IBOrderImpl();

		ibOrder.setNew(true);
		ibOrder.setPrimaryKey(orderIdPk);

		String uuid = PortalUUIDUtil.generate();

		ibOrder.setUuid(uuid);

		ibOrder.setCompanyId(companyProvider.getCompanyId());

		return ibOrder;
	}

	/**
	 * Removes the ib order with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param orderIdPk the primary key of the ib order
	 * @return the ib order that was removed
	 * @throws NoSuchIBOrderException if a ib order with the primary key could not be found
	 */
	@Override
	public IBOrder remove(long orderIdPk) throws NoSuchIBOrderException {
		return remove((Serializable)orderIdPk);
	}

	/**
	 * Removes the ib order with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ib order
	 * @return the ib order that was removed
	 * @throws NoSuchIBOrderException if a ib order with the primary key could not be found
	 */
	@Override
	public IBOrder remove(Serializable primaryKey)
		throws NoSuchIBOrderException {
		Session session = null;

		try {
			session = openSession();

			IBOrder ibOrder = (IBOrder)session.get(IBOrderImpl.class, primaryKey);

			if (ibOrder == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIBOrderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ibOrder);
		}
		catch (NoSuchIBOrderException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected IBOrder removeImpl(IBOrder ibOrder) {
		ibOrder = toUnwrappedModel(ibOrder);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ibOrder)) {
				ibOrder = (IBOrder)session.get(IBOrderImpl.class,
						ibOrder.getPrimaryKeyObj());
			}

			if (ibOrder != null) {
				session.delete(ibOrder);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ibOrder != null) {
			clearCache(ibOrder);
		}

		return ibOrder;
	}

	@Override
	public IBOrder updateImpl(IBOrder ibOrder) {
		ibOrder = toUnwrappedModel(ibOrder);

		boolean isNew = ibOrder.isNew();

		IBOrderModelImpl ibOrderModelImpl = (IBOrderModelImpl)ibOrder;

		if (Validator.isNull(ibOrder.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			ibOrder.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (ibOrder.getCreateDate() == null)) {
			if (serviceContext == null) {
				ibOrder.setCreateDate(now);
			}
			else {
				ibOrder.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!ibOrderModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				ibOrder.setModifiedDate(now);
			}
			else {
				ibOrder.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (ibOrder.isNew()) {
				session.save(ibOrder);

				ibOrder.setNew(false);
			}
			else {
				ibOrder = (IBOrder)session.merge(ibOrder);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !IBOrderModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((ibOrderModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { ibOrderModelImpl.getOriginalUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { ibOrderModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((ibOrderModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ibOrderModelImpl.getOriginalUuid(),
						ibOrderModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						ibOrderModelImpl.getUuid(),
						ibOrderModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((ibOrderModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SHAREIDCOMPANYGROUP.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ibOrderModelImpl.getOriginalShareID(),
						ibOrderModelImpl.getOriginalCompanyId(),
						ibOrderModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SHAREIDCOMPANYGROUP,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SHAREIDCOMPANYGROUP,
					args);

				args = new Object[] {
						ibOrderModelImpl.getShareID(),
						ibOrderModelImpl.getCompanyId(),
						ibOrderModelImpl.getGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SHAREIDCOMPANYGROUP,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SHAREIDCOMPANYGROUP,
					args);
			}

			if ((ibOrderModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERGROUPCOMPANY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ibOrderModelImpl.getOriginalShareID(),
						ibOrderModelImpl.getOriginalCompanyId(),
						ibOrderModelImpl.getOriginalOrdersId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ORDERGROUPCOMPANY,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERGROUPCOMPANY,
					args);

				args = new Object[] {
						ibOrderModelImpl.getShareID(),
						ibOrderModelImpl.getCompanyId(),
						ibOrderModelImpl.getOrdersId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ORDERGROUPCOMPANY,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERGROUPCOMPANY,
					args);
			}

			if ((ibOrderModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERCLIENTGROUPCOMPANY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ibOrderModelImpl.getOriginalOrdersId(),
						ibOrderModelImpl.getOriginalCompanyId(),
						ibOrderModelImpl.getOriginalGroupId(),
						ibOrderModelImpl.getOriginalIbclientId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ORDERCLIENTGROUPCOMPANY,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERCLIENTGROUPCOMPANY,
					args);

				args = new Object[] {
						ibOrderModelImpl.getOrdersId(),
						ibOrderModelImpl.getCompanyId(),
						ibOrderModelImpl.getGroupId(),
						ibOrderModelImpl.getIbclientId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ORDERCLIENTGROUPCOMPANY,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERCLIENTGROUPCOMPANY,
					args);
			}

			if ((ibOrderModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERSHARECLIENTGROUPCOMPANY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ibOrderModelImpl.getOriginalOrdersId(),
						ibOrderModelImpl.getOriginalCompanyId(),
						ibOrderModelImpl.getOriginalShareID(),
						ibOrderModelImpl.getOriginalGroupId(),
						ibOrderModelImpl.getOriginalIbclientId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ORDERSHARECLIENTGROUPCOMPANY,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERSHARECLIENTGROUPCOMPANY,
					args);

				args = new Object[] {
						ibOrderModelImpl.getOrdersId(),
						ibOrderModelImpl.getCompanyId(),
						ibOrderModelImpl.getShareID(),
						ibOrderModelImpl.getGroupId(),
						ibOrderModelImpl.getIbclientId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ORDERSHARECLIENTGROUPCOMPANY,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERSHARECLIENTGROUPCOMPANY,
					args);
			}

			if ((ibOrderModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REMOVABLEDATEGROUPCOMPANY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ibOrderModelImpl.getOriginalRemovable_on_reboot(),
						ibOrderModelImpl.getOriginalCompanyId(),
						ibOrderModelImpl.getOriginalGroupId(),
						ibOrderModelImpl.getOriginalCreateDate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_REMOVABLEDATEGROUPCOMPANY,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REMOVABLEDATEGROUPCOMPANY,
					args);

				args = new Object[] {
						ibOrderModelImpl.getRemovable_on_reboot(),
						ibOrderModelImpl.getCompanyId(),
						ibOrderModelImpl.getGroupId(),
						ibOrderModelImpl.getCreateDate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_REMOVABLEDATEGROUPCOMPANY,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REMOVABLEDATEGROUPCOMPANY,
					args);
			}

			if ((ibOrderModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERCLIENT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ibOrderModelImpl.getOriginalOrdersId(),
						ibOrderModelImpl.getOriginalIbclientId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ORDERCLIENT, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERCLIENT,
					args);

				args = new Object[] {
						ibOrderModelImpl.getOrdersId(),
						ibOrderModelImpl.getIbclientId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ORDERCLIENT, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERCLIENT,
					args);
			}
		}

		entityCache.putResult(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
			IBOrderImpl.class, ibOrder.getPrimaryKey(), ibOrder, false);

		clearUniqueFindersCache(ibOrderModelImpl, false);
		cacheUniqueFindersCache(ibOrderModelImpl);

		ibOrder.resetOriginalValues();

		return ibOrder;
	}

	protected IBOrder toUnwrappedModel(IBOrder ibOrder) {
		if (ibOrder instanceof IBOrderImpl) {
			return ibOrder;
		}

		IBOrderImpl ibOrderImpl = new IBOrderImpl();

		ibOrderImpl.setNew(ibOrder.isNew());
		ibOrderImpl.setPrimaryKey(ibOrder.getPrimaryKey());

		ibOrderImpl.setUuid(ibOrder.getUuid());
		ibOrderImpl.setOrderIdPk(ibOrder.getOrderIdPk());
		ibOrderImpl.setOrdersId(ibOrder.getOrdersId());
		ibOrderImpl.setGroupId(ibOrder.getGroupId());
		ibOrderImpl.setCompanyId(ibOrder.getCompanyId());
		ibOrderImpl.setShareID(ibOrder.getShareID());
		ibOrderImpl.setChecked(ibOrder.isChecked());
		ibOrderImpl.setCreateDate(ibOrder.getCreateDate());
		ibOrderImpl.setModifiedDate(ibOrder.getModifiedDate());
		ibOrderImpl.setIbclientId(ibOrder.getIbclientId());
		ibOrderImpl.setRemovable_on_reboot(ibOrder.isRemovable_on_reboot());

		return ibOrderImpl;
	}

	/**
	 * Returns the ib order with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ib order
	 * @return the ib order
	 * @throws NoSuchIBOrderException if a ib order with the primary key could not be found
	 */
	@Override
	public IBOrder findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIBOrderException {
		IBOrder ibOrder = fetchByPrimaryKey(primaryKey);

		if (ibOrder == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIBOrderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ibOrder;
	}

	/**
	 * Returns the ib order with the primary key or throws a {@link NoSuchIBOrderException} if it could not be found.
	 *
	 * @param orderIdPk the primary key of the ib order
	 * @return the ib order
	 * @throws NoSuchIBOrderException if a ib order with the primary key could not be found
	 */
	@Override
	public IBOrder findByPrimaryKey(long orderIdPk)
		throws NoSuchIBOrderException {
		return findByPrimaryKey((Serializable)orderIdPk);
	}

	/**
	 * Returns the ib order with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ib order
	 * @return the ib order, or <code>null</code> if a ib order with the primary key could not be found
	 */
	@Override
	public IBOrder fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
				IBOrderImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IBOrder ibOrder = (IBOrder)serializable;

		if (ibOrder == null) {
			Session session = null;

			try {
				session = openSession();

				ibOrder = (IBOrder)session.get(IBOrderImpl.class, primaryKey);

				if (ibOrder != null) {
					cacheResult(ibOrder);
				}
				else {
					entityCache.putResult(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
						IBOrderImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
					IBOrderImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ibOrder;
	}

	/**
	 * Returns the ib order with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param orderIdPk the primary key of the ib order
	 * @return the ib order, or <code>null</code> if a ib order with the primary key could not be found
	 */
	@Override
	public IBOrder fetchByPrimaryKey(long orderIdPk) {
		return fetchByPrimaryKey((Serializable)orderIdPk);
	}

	@Override
	public Map<Serializable, IBOrder> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IBOrder> map = new HashMap<Serializable, IBOrder>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IBOrder ibOrder = fetchByPrimaryKey(primaryKey);

			if (ibOrder != null) {
				map.put(primaryKey, ibOrder);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
					IBOrderImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IBOrder)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IBORDER_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (IBOrder ibOrder : (List<IBOrder>)q.list()) {
				map.put(ibOrder.getPrimaryKeyObj(), ibOrder);

				cacheResult(ibOrder);

				uncachedPrimaryKeys.remove(ibOrder.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IBOrderModelImpl.ENTITY_CACHE_ENABLED,
					IBOrderImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the ib orders.
	 *
	 * @return the ib orders
	 */
	@Override
	public List<IBOrder> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ib orders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ib orders
	 * @param end the upper bound of the range of ib orders (not inclusive)
	 * @return the range of ib orders
	 */
	@Override
	public List<IBOrder> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ib orders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ib orders
	 * @param end the upper bound of the range of ib orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ib orders
	 */
	@Override
	public List<IBOrder> findAll(int start, int end,
		OrderByComparator<IBOrder> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ib orders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ib orders
	 * @param end the upper bound of the range of ib orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ib orders
	 */
	@Override
	public List<IBOrder> findAll(int start, int end,
		OrderByComparator<IBOrder> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<IBOrder> list = null;

		if (retrieveFromCache) {
			list = (List<IBOrder>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IBORDER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IBORDER;

				if (pagination) {
					sql = sql.concat(IBOrderModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IBOrder>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IBOrder>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the ib orders from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IBOrder ibOrder : findAll()) {
			remove(ibOrder);
		}
	}

	/**
	 * Returns the number of ib orders.
	 *
	 * @return the number of ib orders
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IBORDER);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return IBOrderModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ib order persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IBOrderImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IBORDER = "SELECT ibOrder FROM IBOrder ibOrder";
	private static final String _SQL_SELECT_IBORDER_WHERE_PKS_IN = "SELECT ibOrder FROM IBOrder ibOrder WHERE orderIdPk IN (";
	private static final String _SQL_SELECT_IBORDER_WHERE = "SELECT ibOrder FROM IBOrder ibOrder WHERE ";
	private static final String _SQL_COUNT_IBORDER = "SELECT COUNT(ibOrder) FROM IBOrder ibOrder";
	private static final String _SQL_COUNT_IBORDER_WHERE = "SELECT COUNT(ibOrder) FROM IBOrder ibOrder WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ibOrder.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IBOrder exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No IBOrder exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(IBOrderPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}