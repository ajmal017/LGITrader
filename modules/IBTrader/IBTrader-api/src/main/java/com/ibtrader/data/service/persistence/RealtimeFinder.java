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

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface RealtimeFinder {
	public void removeScheduledRealTimes(long companyId, long groupId);

	public com.ibtrader.data.model.Realtime findSumVolumeBetweenBars(
		java.util.Date from, java.util.Date to, long shareId, long companyId,
		long groupId);

	public java.util.List findMinMaxRealTime(java.util.Date from,
		java.util.Date to, long shareId, long companyId, long groupId);

	public com.ibtrader.data.model.Realtime findMaxRealTime(
		java.util.Date from, java.util.Date to, long shareId, long companyId,
		long groupId);

	public com.ibtrader.data.model.Realtime findMinRealTime(
		java.util.Date from, java.util.Date to, long shareId, long companyId,
		long groupId);

	public java.util.List findMinMaxRealTimesGroupedByBars(
		java.util.Date from, java.util.Date to, long shareId, long companyId,
		long groupId, long timebars, java.lang.String openMarketUTC,
		java.lang.String closeMarketUTC);

	public com.ibtrader.data.model.Realtime findLastRealTime(long shareId,
		long companyId, long groupId);

	public com.ibtrader.data.model.Realtime findLastRealTimeLessThanDate(
		long shareId, long companyId, long groupId, java.util.Date To);

	public java.util.List findSimpleMobileAvgGroupByPeriods(long shareId,
		long companyId, long groupId, java.util.Date from, java.util.Date to,
		java.util.List<java.lang.String> mobileAvgDates);

	public java.util.List<com.ibtrader.data.model.Realtime> findCloseRealTimes(
		long shareId, long companyId, long groupId, java.util.Date from,
		java.util.Date to, java.util.List<java.lang.String> mobileAvgDates);

	public com.ibtrader.data.model.Realtime findFirstRealTimeBetweenDates(
		long shareId, long companyId, long groupId, java.util.Date from,
		java.util.Date to);
}