<%--
 * action-1.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<display:table name="requests" id="row" pagesize="5"
	requestURI="${requestUri}" class="displaytag">

	<display:column titleKey="request.status" sortable="true">
		<jstl:if test="${row.requestState=='PENDING' }">
			<spring:message code="request.pending" />
		</jstl:if>
		<spring:message code="request.momentPending" var="momentPending" />
		<display:column property="momentPending" title="${momentPending}"
			sortable="true" format="{0, date, dd/MM/yyyy HH:mm}" />
		<jstl:if test="${row.requestState=='ACCEPTED' }">
			<spring:message code="request.accepted" />

			<spring:message code="request.momentAccepted" var="momentAccepted" />
			<display:column property="momentAccepted" title="${momentAccepted}"
				sortable="true" format="{0, date, dd/MM/yyyy HH:mm}" />
		</jstl:if>
		<jstl:if test="${row.requestState=='REJECTED' }">
			<spring:message code="request.rejected" />
		</jstl:if>
		
			<spring:message code="request.activity" var="activityHeader" />
	<display:column title="${activityHeader}">
		<input type="button"
			value="<spring:message code="request.activity" />"
			onclick="javascript: window.location.assign('activity/listByRequest.do?requestId=${row.id}')" />
	</display:column>

	</display:column>

</display:table>
