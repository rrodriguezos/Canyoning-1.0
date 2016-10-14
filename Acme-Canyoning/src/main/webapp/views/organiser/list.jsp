<%--
 * list.jsp
 *
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<display:table name="organisers" id="row" class="displaytag"
	requestURI="${requestURI}" pagesize="5" keepStatus="true">

	<spring:message code="organiser.email" var="email" />
	<display:column property="email" title="${name}" sortable="true" />

	<spring:message code="organiser.phone" var="phone" />
	<display:column property="phone" title="${phone}" sortable="true" />


	<security:authorize access="isAuthenticated()">
		<spring:message code="organiser.comment" var="commentHeader" />
		<display:column title="${commentHeader}">
			<input type="button"
				value="<spring:message code="organiser.comment" />"
				onclick="javascript: window.location.assign('comment/list.do?id=${row.organiserComment.id}')" />
		</display:column>
	</security:authorize>

</display:table>
<security:authorize access="hasRole('ADMINISTRATOR')">

<input type="button" name="create" value="<spring:message code="organiser.create" />"
	 onclick="javascript: window.location.assign('organiser/administrator/create.do')" />
</security:authorize>
