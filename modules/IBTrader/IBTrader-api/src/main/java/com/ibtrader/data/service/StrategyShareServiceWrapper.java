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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StrategyShareService}.
 *
 * @author Brian Wing Shun Chan
 * @see StrategyShareService
 * @generated
 */
@ProviderType
public class StrategyShareServiceWrapper implements StrategyShareService,
	ServiceWrapper<StrategyShareService> {
	public StrategyShareServiceWrapper(
		StrategyShareService strategyShareService) {
		_strategyShareService = strategyShareService;
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _strategyShareService.getOSGiServiceIdentifier();
	}

	@Override
	public StrategyShareService getWrappedService() {
		return _strategyShareService;
	}

	@Override
	public void setWrappedService(StrategyShareService strategyShareService) {
		_strategyShareService = strategyShareService;
	}

	private StrategyShareService _strategyShareService;
}