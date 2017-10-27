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

import com.ibtrader.data.exception.NoSuchPositionException;
import com.ibtrader.data.model.Position;
import com.ibtrader.data.model.impl.PositionImpl;
import com.ibtrader.data.model.impl.PositionModelImpl;
import com.ibtrader.data.service.persistence.PositionPersistence;

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
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;

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
 * The persistence implementation for the position service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PositionPersistence
 * @see com.ibtrader.data.service.persistence.PositionUtil
 * @generated
 */
@ProviderType
public class PositionPersistenceImpl extends BasePersistenceImpl<Position>
	implements PositionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PositionUtil} to access the position persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PositionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			PositionModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the positions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching positions
	 */
	@Override
	public List<Position> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the positions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @return the range of matching positions
	 */
	@Override
	public List<Position> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the positions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching positions
	 */
	@Override
	public List<Position> findByUuid(String uuid, int start, int end,
		OrderByComparator<Position> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the positions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching positions
	 */
	@Override
	public List<Position> findByUuid(String uuid, int start, int end,
		OrderByComparator<Position> orderByComparator, boolean retrieveFromCache) {
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

		List<Position> list = null;

		if (retrieveFromCache) {
			list = (List<Position>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Position position : list) {
					if (!Objects.equals(uuid, position.getUuid())) {
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

			query.append(_SQL_SELECT_POSITION_WHERE);

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
				query.append(PositionModelImpl.ORDER_BY_JPQL);
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
					list = (List<Position>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Position>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first position in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByUuid_First(String uuid,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = fetchByUuid_First(uuid, orderByComparator);

		if (position != null) {
			return position;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPositionException(msg.toString());
	}

	/**
	 * Returns the first position in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByUuid_First(String uuid,
		OrderByComparator<Position> orderByComparator) {
		List<Position> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last position in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByUuid_Last(String uuid,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = fetchByUuid_Last(uuid, orderByComparator);

		if (position != null) {
			return position;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPositionException(msg.toString());
	}

	/**
	 * Returns the last position in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByUuid_Last(String uuid,
		OrderByComparator<Position> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Position> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the positions before and after the current position in the ordered set where uuid = &#63;.
	 *
	 * @param positionId the primary key of the current position
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next position
	 * @throws NoSuchPositionException if a position with the primary key could not be found
	 */
	@Override
	public Position[] findByUuid_PrevAndNext(long positionId, String uuid,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = findByPrimaryKey(positionId);

		Session session = null;

		try {
			session = openSession();

			Position[] array = new PositionImpl[3];

			array[0] = getByUuid_PrevAndNext(session, position, uuid,
					orderByComparator, true);

			array[1] = position;

			array[2] = getByUuid_PrevAndNext(session, position, uuid,
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

	protected Position getByUuid_PrevAndNext(Session session,
		Position position, String uuid,
		OrderByComparator<Position> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_POSITION_WHERE);

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
			query.append(PositionModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(position);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Position> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the positions where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Position position : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(position);
		}
	}

	/**
	 * Returns the number of positions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching positions
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_POSITION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "position.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "position.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(position.uuid IS NULL OR position.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			PositionModelImpl.UUID_COLUMN_BITMASK |
			PositionModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the position where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPositionException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByUUID_G(String uuid, long groupId)
		throws NoSuchPositionException {
		Position position = fetchByUUID_G(uuid, groupId);

		if (position == null) {
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

			throw new NoSuchPositionException(msg.toString());
		}

		return position;
	}

	/**
	 * Returns the position where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the position where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Position) {
			Position position = (Position)result;

			if (!Objects.equals(uuid, position.getUuid()) ||
					(groupId != position.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_POSITION_WHERE);

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

				List<Position> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Position position = list.get(0);

					result = position;

					cacheResult(position);

					if ((position.getUuid() == null) ||
							!position.getUuid().equals(uuid) ||
							(position.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, position);
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
			return (Position)result;
		}
	}

	/**
	 * Removes the position where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the position that was removed
	 */
	@Override
	public Position removeByUUID_G(String uuid, long groupId)
		throws NoSuchPositionException {
		Position position = findByUUID_G(uuid, groupId);

		return remove(position);
	}

	/**
	 * Returns the number of positions where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching positions
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_POSITION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "position.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "position.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(position.uuid IS NULL OR position.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "position.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			PositionModelImpl.UUID_COLUMN_BITMASK |
			PositionModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the positions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching positions
	 */
	@Override
	public List<Position> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the positions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @return the range of matching positions
	 */
	@Override
	public List<Position> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the positions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching positions
	 */
	@Override
	public List<Position> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Position> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the positions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching positions
	 */
	@Override
	public List<Position> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Position> orderByComparator,
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

		List<Position> list = null;

		if (retrieveFromCache) {
			list = (List<Position>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Position position : list) {
					if (!Objects.equals(uuid, position.getUuid()) ||
							(companyId != position.getCompanyId())) {
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

			query.append(_SQL_SELECT_POSITION_WHERE);

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
				query.append(PositionModelImpl.ORDER_BY_JPQL);
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
					list = (List<Position>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Position>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first position in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (position != null) {
			return position;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPositionException(msg.toString());
	}

	/**
	 * Returns the first position in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Position> orderByComparator) {
		List<Position> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last position in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (position != null) {
			return position;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPositionException(msg.toString());
	}

	/**
	 * Returns the last position in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Position> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Position> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the positions before and after the current position in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param positionId the primary key of the current position
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next position
	 * @throws NoSuchPositionException if a position with the primary key could not be found
	 */
	@Override
	public Position[] findByUuid_C_PrevAndNext(long positionId, String uuid,
		long companyId, OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = findByPrimaryKey(positionId);

		Session session = null;

		try {
			session = openSession();

			Position[] array = new PositionImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, position, uuid,
					companyId, orderByComparator, true);

			array[1] = position;

			array[2] = getByUuid_C_PrevAndNext(session, position, uuid,
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

	protected Position getByUuid_C_PrevAndNext(Session session,
		Position position, String uuid, long companyId,
		OrderByComparator<Position> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_POSITION_WHERE);

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
			query.append(PositionModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(position);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Position> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the positions where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Position position : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(position);
		}
	}

	/**
	 * Returns the number of positions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching positions
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_POSITION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "position.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "position.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(position.uuid IS NULL OR position.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "position.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_POSITIONID_OUT_TWS =
		new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPositionID_Out_TWS",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POSITIONID_OUT_TWS =
		new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPositionID_Out_TWS", new String[] { Long.class.getName() },
			PositionModelImpl.POSITIONID_TWS_OUT_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_POSITIONID_OUT_TWS = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPositionID_Out_TWS", new String[] { Long.class.getName() });

	/**
	 * Returns all the positions where positionId_tws_out = &#63;.
	 *
	 * @param positionId_tws_out the position id_tws_out
	 * @return the matching positions
	 */
	@Override
	public List<Position> findByPositionID_Out_TWS(long positionId_tws_out) {
		return findByPositionID_Out_TWS(positionId_tws_out, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the positions where positionId_tws_out = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param positionId_tws_out the position id_tws_out
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @return the range of matching positions
	 */
	@Override
	public List<Position> findByPositionID_Out_TWS(long positionId_tws_out,
		int start, int end) {
		return findByPositionID_Out_TWS(positionId_tws_out, start, end, null);
	}

	/**
	 * Returns an ordered range of all the positions where positionId_tws_out = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param positionId_tws_out the position id_tws_out
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching positions
	 */
	@Override
	public List<Position> findByPositionID_Out_TWS(long positionId_tws_out,
		int start, int end, OrderByComparator<Position> orderByComparator) {
		return findByPositionID_Out_TWS(positionId_tws_out, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the positions where positionId_tws_out = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param positionId_tws_out the position id_tws_out
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching positions
	 */
	@Override
	public List<Position> findByPositionID_Out_TWS(long positionId_tws_out,
		int start, int end, OrderByComparator<Position> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POSITIONID_OUT_TWS;
			finderArgs = new Object[] { positionId_tws_out };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_POSITIONID_OUT_TWS;
			finderArgs = new Object[] {
					positionId_tws_out,
					
					start, end, orderByComparator
				};
		}

		List<Position> list = null;

		if (retrieveFromCache) {
			list = (List<Position>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Position position : list) {
					if ((positionId_tws_out != position.getPositionId_tws_out())) {
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

			query.append(_SQL_SELECT_POSITION_WHERE);

			query.append(_FINDER_COLUMN_POSITIONID_OUT_TWS_POSITIONID_TWS_OUT_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PositionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(positionId_tws_out);

				if (!pagination) {
					list = (List<Position>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Position>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first position in the ordered set where positionId_tws_out = &#63;.
	 *
	 * @param positionId_tws_out the position id_tws_out
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByPositionID_Out_TWS_First(long positionId_tws_out,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = fetchByPositionID_Out_TWS_First(positionId_tws_out,
				orderByComparator);

		if (position != null) {
			return position;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("positionId_tws_out=");
		msg.append(positionId_tws_out);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPositionException(msg.toString());
	}

	/**
	 * Returns the first position in the ordered set where positionId_tws_out = &#63;.
	 *
	 * @param positionId_tws_out the position id_tws_out
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByPositionID_Out_TWS_First(long positionId_tws_out,
		OrderByComparator<Position> orderByComparator) {
		List<Position> list = findByPositionID_Out_TWS(positionId_tws_out, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last position in the ordered set where positionId_tws_out = &#63;.
	 *
	 * @param positionId_tws_out the position id_tws_out
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByPositionID_Out_TWS_Last(long positionId_tws_out,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = fetchByPositionID_Out_TWS_Last(positionId_tws_out,
				orderByComparator);

		if (position != null) {
			return position;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("positionId_tws_out=");
		msg.append(positionId_tws_out);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPositionException(msg.toString());
	}

	/**
	 * Returns the last position in the ordered set where positionId_tws_out = &#63;.
	 *
	 * @param positionId_tws_out the position id_tws_out
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByPositionID_Out_TWS_Last(long positionId_tws_out,
		OrderByComparator<Position> orderByComparator) {
		int count = countByPositionID_Out_TWS(positionId_tws_out);

		if (count == 0) {
			return null;
		}

		List<Position> list = findByPositionID_Out_TWS(positionId_tws_out,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the positions before and after the current position in the ordered set where positionId_tws_out = &#63;.
	 *
	 * @param positionId the primary key of the current position
	 * @param positionId_tws_out the position id_tws_out
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next position
	 * @throws NoSuchPositionException if a position with the primary key could not be found
	 */
	@Override
	public Position[] findByPositionID_Out_TWS_PrevAndNext(long positionId,
		long positionId_tws_out, OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = findByPrimaryKey(positionId);

		Session session = null;

		try {
			session = openSession();

			Position[] array = new PositionImpl[3];

			array[0] = getByPositionID_Out_TWS_PrevAndNext(session, position,
					positionId_tws_out, orderByComparator, true);

			array[1] = position;

			array[2] = getByPositionID_Out_TWS_PrevAndNext(session, position,
					positionId_tws_out, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Position getByPositionID_Out_TWS_PrevAndNext(Session session,
		Position position, long positionId_tws_out,
		OrderByComparator<Position> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_POSITION_WHERE);

		query.append(_FINDER_COLUMN_POSITIONID_OUT_TWS_POSITIONID_TWS_OUT_2);

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
			query.append(PositionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(positionId_tws_out);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(position);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Position> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the positions where positionId_tws_out = &#63; from the database.
	 *
	 * @param positionId_tws_out the position id_tws_out
	 */
	@Override
	public void removeByPositionID_Out_TWS(long positionId_tws_out) {
		for (Position position : findByPositionID_Out_TWS(positionId_tws_out,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(position);
		}
	}

	/**
	 * Returns the number of positions where positionId_tws_out = &#63;.
	 *
	 * @param positionId_tws_out the position id_tws_out
	 * @return the number of matching positions
	 */
	@Override
	public int countByPositionID_Out_TWS(long positionId_tws_out) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_POSITIONID_OUT_TWS;

		Object[] finderArgs = new Object[] { positionId_tws_out };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_POSITION_WHERE);

			query.append(_FINDER_COLUMN_POSITIONID_OUT_TWS_POSITIONID_TWS_OUT_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(positionId_tws_out);

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

	private static final String _FINDER_COLUMN_POSITIONID_OUT_TWS_POSITIONID_TWS_OUT_2 =
		"position.positionId_tws_out = ?";

	public PositionPersistenceImpl() {
		setModelClass(Position.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");
			dbColumnNames.put("state", "state_");
			dbColumnNames.put("type", "type_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the position in the entity cache if it is enabled.
	 *
	 * @param position the position
	 */
	@Override
	public void cacheResult(Position position) {
		entityCache.putResult(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionImpl.class, position.getPrimaryKey(), position);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { position.getUuid(), position.getGroupId() }, position);

		position.resetOriginalValues();
	}

	/**
	 * Caches the positions in the entity cache if it is enabled.
	 *
	 * @param positions the positions
	 */
	@Override
	public void cacheResult(List<Position> positions) {
		for (Position position : positions) {
			if (entityCache.getResult(PositionModelImpl.ENTITY_CACHE_ENABLED,
						PositionImpl.class, position.getPrimaryKey()) == null) {
				cacheResult(position);
			}
			else {
				position.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all positions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PositionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the position.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Position position) {
		entityCache.removeResult(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionImpl.class, position.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((PositionModelImpl)position, true);
	}

	@Override
	public void clearCache(List<Position> positions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Position position : positions) {
			entityCache.removeResult(PositionModelImpl.ENTITY_CACHE_ENABLED,
				PositionImpl.class, position.getPrimaryKey());

			clearUniqueFindersCache((PositionModelImpl)position, true);
		}
	}

	protected void cacheUniqueFindersCache(PositionModelImpl positionModelImpl) {
		Object[] args = new Object[] {
				positionModelImpl.getUuid(), positionModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			positionModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		PositionModelImpl positionModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					positionModelImpl.getUuid(), positionModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((positionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					positionModelImpl.getOriginalUuid(),
					positionModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new position with the primary key. Does not add the position to the database.
	 *
	 * @param positionId the primary key for the new position
	 * @return the new position
	 */
	@Override
	public Position create(long positionId) {
		Position position = new PositionImpl();

		position.setNew(true);
		position.setPrimaryKey(positionId);

		String uuid = PortalUUIDUtil.generate();

		position.setUuid(uuid);

		position.setCompanyId(companyProvider.getCompanyId());

		return position;
	}

	/**
	 * Removes the position with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param positionId the primary key of the position
	 * @return the position that was removed
	 * @throws NoSuchPositionException if a position with the primary key could not be found
	 */
	@Override
	public Position remove(long positionId) throws NoSuchPositionException {
		return remove((Serializable)positionId);
	}

	/**
	 * Removes the position with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the position
	 * @return the position that was removed
	 * @throws NoSuchPositionException if a position with the primary key could not be found
	 */
	@Override
	public Position remove(Serializable primaryKey)
		throws NoSuchPositionException {
		Session session = null;

		try {
			session = openSession();

			Position position = (Position)session.get(PositionImpl.class,
					primaryKey);

			if (position == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPositionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(position);
		}
		catch (NoSuchPositionException nsee) {
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
	protected Position removeImpl(Position position) {
		position = toUnwrappedModel(position);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(position)) {
				position = (Position)session.get(PositionImpl.class,
						position.getPrimaryKeyObj());
			}

			if (position != null) {
				session.delete(position);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (position != null) {
			clearCache(position);
		}

		return position;
	}

	@Override
	public Position updateImpl(Position position) {
		position = toUnwrappedModel(position);

		boolean isNew = position.isNew();

		PositionModelImpl positionModelImpl = (PositionModelImpl)position;

		if (Validator.isNull(position.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			position.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (position.getCreateDate() == null)) {
			if (serviceContext == null) {
				position.setCreateDate(now);
			}
			else {
				position.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!positionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				position.setModifiedDate(now);
			}
			else {
				position.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (position.isNew()) {
				session.save(position);

				position.setNew(false);
			}
			else {
				position = (Position)session.merge(position);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!PositionModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { positionModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					positionModelImpl.getUuid(),
					positionModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { positionModelImpl.getPositionId_tws_out() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_POSITIONID_OUT_TWS,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POSITIONID_OUT_TWS,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((positionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { positionModelImpl.getOriginalUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { positionModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((positionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						positionModelImpl.getOriginalUuid(),
						positionModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						positionModelImpl.getUuid(),
						positionModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((positionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POSITIONID_OUT_TWS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						positionModelImpl.getOriginalPositionId_tws_out()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_POSITIONID_OUT_TWS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POSITIONID_OUT_TWS,
					args);

				args = new Object[] { positionModelImpl.getPositionId_tws_out() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_POSITIONID_OUT_TWS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POSITIONID_OUT_TWS,
					args);
			}
		}

		entityCache.putResult(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionImpl.class, position.getPrimaryKey(), position, false);

		clearUniqueFindersCache(positionModelImpl, false);
		cacheUniqueFindersCache(positionModelImpl);

		position.resetOriginalValues();

		return position;
	}

	protected Position toUnwrappedModel(Position position) {
		if (position instanceof PositionImpl) {
			return position;
		}

		PositionImpl positionImpl = new PositionImpl();

		positionImpl.setNew(position.isNew());
		positionImpl.setPrimaryKey(position.getPrimaryKey());

		positionImpl.setUuid(position.getUuid());
		positionImpl.setPositionId(position.getPositionId());
		positionImpl.setGroupId(position.getGroupId());
		positionImpl.setCompanyId(position.getCompanyId());
		positionImpl.setShareId(position.getShareId());
		positionImpl.setValue(position.getValue());
		positionImpl.setCreateDate(position.getCreateDate());
		positionImpl.setModifiedDate(position.getModifiedDate());
		positionImpl.setState(position.getState());
		positionImpl.setState_in(position.getState_in());
		positionImpl.setState_out(position.getState_out());
		positionImpl.setDescription(position.getDescription());
		positionImpl.setPrice_in(position.getPrice_in());
		positionImpl.setPrice_real_in(position.getPrice_real_in());
		positionImpl.setLimit_price_in(position.getLimit_price_in());
		positionImpl.setDate_in(position.getDate_in());
		positionImpl.setDate_real_in(position.getDate_real_in());
		positionImpl.setPositionId_tws_out(position.getPositionId_tws_out());
		positionImpl.setType(position.getType());
		positionImpl.setPrice_out(position.getPrice_out());
		positionImpl.setPrice_real_out(position.getPrice_real_out());
		positionImpl.setLimit_price_out(position.getLimit_price_out());
		positionImpl.setDate_out(position.getDate_out());
		positionImpl.setDate_real_out(position.getDate_real_out());
		positionImpl.setShare_number(position.getShare_number());
		positionImpl.setShare_number_to_trade(position.getShare_number_to_trade());
		positionImpl.setShare_number_traded(position.getShare_number_traded());
		positionImpl.setRealtimeId_in(position.getRealtimeId_in());
		positionImpl.setRealtimeId_out(position.getRealtimeId_out());
		positionImpl.setStrategyId_in(position.getStrategyId_in());
		positionImpl.setStrategyId_out(position.getStrategyId_out());
		positionImpl.setPercentualstoplost_out(position.getPercentualstoplost_out());
		positionImpl.setPricestoplost_out(position.getPricestoplost_out());
		positionImpl.setPercentualstopprofit_out(position.getPercentualstopprofit_out());
		positionImpl.setPricestopprofit_out(position.getPricestopprofit_out());
		positionImpl.setPendingcancelled(position.getPendingcancelled());
		positionImpl.setTrading_data_operations(position.getTrading_data_operations());
		positionImpl.setSimulation_mode(position.isSimulation_mode());

		return positionImpl;
	}

	/**
	 * Returns the position with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the position
	 * @return the position
	 * @throws NoSuchPositionException if a position with the primary key could not be found
	 */
	@Override
	public Position findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPositionException {
		Position position = fetchByPrimaryKey(primaryKey);

		if (position == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPositionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return position;
	}

	/**
	 * Returns the position with the primary key or throws a {@link NoSuchPositionException} if it could not be found.
	 *
	 * @param positionId the primary key of the position
	 * @return the position
	 * @throws NoSuchPositionException if a position with the primary key could not be found
	 */
	@Override
	public Position findByPrimaryKey(long positionId)
		throws NoSuchPositionException {
		return findByPrimaryKey((Serializable)positionId);
	}

	/**
	 * Returns the position with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the position
	 * @return the position, or <code>null</code> if a position with the primary key could not be found
	 */
	@Override
	public Position fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(PositionModelImpl.ENTITY_CACHE_ENABLED,
				PositionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Position position = (Position)serializable;

		if (position == null) {
			Session session = null;

			try {
				session = openSession();

				position = (Position)session.get(PositionImpl.class, primaryKey);

				if (position != null) {
					cacheResult(position);
				}
				else {
					entityCache.putResult(PositionModelImpl.ENTITY_CACHE_ENABLED,
						PositionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(PositionModelImpl.ENTITY_CACHE_ENABLED,
					PositionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return position;
	}

	/**
	 * Returns the position with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param positionId the primary key of the position
	 * @return the position, or <code>null</code> if a position with the primary key could not be found
	 */
	@Override
	public Position fetchByPrimaryKey(long positionId) {
		return fetchByPrimaryKey((Serializable)positionId);
	}

	@Override
	public Map<Serializable, Position> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Position> map = new HashMap<Serializable, Position>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Position position = fetchByPrimaryKey(primaryKey);

			if (position != null) {
				map.put(primaryKey, position);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(PositionModelImpl.ENTITY_CACHE_ENABLED,
					PositionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Position)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_POSITION_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (Position position : (List<Position>)q.list()) {
				map.put(position.getPrimaryKeyObj(), position);

				cacheResult(position);

				uncachedPrimaryKeys.remove(position.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(PositionModelImpl.ENTITY_CACHE_ENABLED,
					PositionImpl.class, primaryKey, nullModel);
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
	 * Returns all the positions.
	 *
	 * @return the positions
	 */
	@Override
	public List<Position> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the positions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @return the range of positions
	 */
	@Override
	public List<Position> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the positions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of positions
	 */
	@Override
	public List<Position> findAll(int start, int end,
		OrderByComparator<Position> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the positions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of positions
	 */
	@Override
	public List<Position> findAll(int start, int end,
		OrderByComparator<Position> orderByComparator, boolean retrieveFromCache) {
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

		List<Position> list = null;

		if (retrieveFromCache) {
			list = (List<Position>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_POSITION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_POSITION;

				if (pagination) {
					sql = sql.concat(PositionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Position>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Position>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the positions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Position position : findAll()) {
			remove(position);
		}
	}

	/**
	 * Returns the number of positions.
	 *
	 * @return the number of positions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_POSITION);

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
		return PositionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the position persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(PositionImpl.class.getName());
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
	private static final String _SQL_SELECT_POSITION = "SELECT position FROM Position position";
	private static final String _SQL_SELECT_POSITION_WHERE_PKS_IN = "SELECT position FROM Position position WHERE positionId IN (";
	private static final String _SQL_SELECT_POSITION_WHERE = "SELECT position FROM Position position WHERE ";
	private static final String _SQL_COUNT_POSITION = "SELECT COUNT(position) FROM Position position";
	private static final String _SQL_COUNT_POSITION_WHERE = "SELECT COUNT(position) FROM Position position WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "position.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Position exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Position exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(PositionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "state", "type"
			});
}