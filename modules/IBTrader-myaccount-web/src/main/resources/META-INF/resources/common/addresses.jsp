<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
String className = (String)request.getAttribute("addresses.className");
long classPK = (Long)request.getAttribute("addresses.classPK");
List<Address> addresses = Collections.emptyList();

int[] addressesIndexes = null;

String addressesIndexesParam = ParamUtil.getString(request, "addressesIndexes");

if (Validator.isNotNull(addressesIndexesParam) && !campusElEspanolBehaviour ) {
	addresses = new ArrayList<Address>();

	addressesIndexes = StringUtil.split(addressesIndexesParam, 0);

	for (int addressesIndex : addressesIndexes) {
		addresses.add(new AddressImpl());
	}
}
else {
	if (classPK > 0) {
		addresses = AddressServiceUtil.getAddresses(className, classPK);
	
		addressesIndexes = new int[addresses.size()];
	
		for (int i = 0; i < addresses.size(); i++) {
			addressesIndexes[i] = i;
		}
	}
	
	if (addresses.isEmpty()) {
		addresses = new ArrayList<Address>();
	
		addresses.add(new AddressImpl());
	
		addressesIndexes = new int[] {0};
	}
	
	if (addressesIndexes == null) {
		addressesIndexes = new int[0];
	}
}

%>

<liferay-ui:error-marker key="<%= WebKeys.ERROR_SECTION %>" value="addresses" />

<c:if test="<%= !campusElEspanolBehaviour %>">
	<div class="alert alert-info">
		<liferay-ui:message key="street-1-and-city-are-required-fields.-postal-code-could-be-required-in-some-countries" />
	</div>
</c:if>

<liferay-ui:error exception="<%= AddressCityException.class %>" message="please-enter-a-valid-city" />
<liferay-ui:error exception="<%= AddressStreetException.class %>" message="please-enter-a-valid-street" />
<liferay-ui:error exception="<%= AddressZipException.class %>" message="please-enter-a-valid-postal-code" />
<liferay-ui:error exception="<%= NoSuchCountryException.class %>" message="please-select-a-country" />
<liferay-ui:error key="<%= NoSuchListTypeException.class.getName() + className + ListTypeConstants.ADDRESS %>" message="please-select-a-type" />
<liferay-ui:error exception="<%= NoSuchRegionException.class %>" message="please-select-a-region" />

<aui:fieldset cssClass="addresses" id='<%=renderResponse.getNamespace() + "addresses"%>'>

	<%
	for (int i = 0; i < addressesIndexes.length; i++) {
		int addressesIndex = addressesIndexes[i];

		Address address = addresses.get(i);

		long countryId = ParamUtil.getLong(request, "addressCountryId" + addressesIndex, address.getCountryId());
		long regionId = ParamUtil.getLong(request, "addressRegionId" + addressesIndex, address.getRegionId());
	%>

		<aui:model-context bean="<%= address %>" model="<%= Address.class %>" />
 
		<div class='<%= (campusElEspanolBehaviour) ? renderResponse.getNamespace() + "" :" lfr-form-row"%>'>
			<div class="row-fields">
				<%@ include file="/common/addresses_address.jspf" %>
			</div>
		</div>

		<aui:script use="liferay-address,liferay-dynamic-select">
			new Liferay.DynamicSelect(
				[
					{
						select: '<portlet:namespace />addressCountryId<%= addressesIndex %>',
						selectData: Liferay.Address.getCountries,
						selectDesc: 'nameCurrentValue',
						selectId: 'countryId',
						selectSort: '<%= true %>',
						selectVal: '<%= countryId %>'
					},
					{
						select: '<portlet:namespace />addressRegionId<%= addressesIndex %>',
						selectData: Liferay.Address.getRegions,
						selectDesc: 'name',
						selectId: 'regionId',
						selectVal: '<%= regionId %>'
					}
				]
			);
		</aui:script>

	<%
	}
	%>

	<aui:input name="addressesIndexes" type="hidden" value="<%= StringUtil.merge(addressesIndexes) %>" />
	
</aui:fieldset>

<c:if test="<%= !campusElEspanolBehaviour %>">
	<aui:script use="liferay-address,liferay-auto-fields,liferay-dynamic-select">
		new Liferay.AutoFields(
			{
				contentBox: '#<portlet:namespace />addresses',
				fieldIndexes: '<portlet:namespace />addressesIndexes',
				namespace: '<portlet:namespace />',
				on: {
					'clone': function(event) {
						var guid = event.guid;
						var row = event.row;
	
						var dynamicSelects = row.one('select[data-componentType=dynamic_select]');
	
						if (dynamicSelects) {
							dynamicSelects.detach('change');
						}
	
						new Liferay.DynamicSelect(
							[
								{
									select: '<portlet:namespace />addressCountryId' + guid,
									selectData: Liferay.Address.getCountries,
									selectDesc: 'nameCurrentValue',
									selectId: 'countryId',
									selectSort: '<%= true %>',
									selectVal: '0'
								},
								{
									select: '<portlet:namespace />addressRegionId' + guid,
									selectData: Liferay.Address.getRegions,
									selectDesc: 'name',
									selectId: 'regionId',
									selectVal: '0'
								}
							]
						);
					}
				}
			}
		).render();
	</aui:script>
</c:if>