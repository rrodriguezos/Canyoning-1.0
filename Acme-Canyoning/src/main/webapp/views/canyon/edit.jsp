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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('ADMINISTRATOR')">

	<form:form action="canyon/administrator/edit.do" modelAttribute="canyon">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="administrator" />
		<form:hidden path="activities" />
		<form:hidden path="comments" />


		<acme:textbox code="canyon.name" path="name" />

		<acme:date code="canyon.difficultyLevel" path="difficultyLevel"/>

		<acme:date code="canyon.route" path="route" />

		<acme:textbox code="canyon.description" path="description" />
		
		<acme:textbox code="canyon.latitude" path="latitude"/>
		<acme:textbox code="canyon.longitude" path="longitude"/>
		<acme:textbox code="canyon.altitude" path="altitude"/>

		<acme:textbox code="canyon.pictures" path="pictures" />
		
		<jstl:if test="${canyon.id == 0}">
		<input type="submit" name="save"
			value="<spring:message code="canyon.save" />" />
		</jstl:if>
		
		<jstl:if test="${canyon.id != 0}">
			<input type="submit" name="save"
			value="<spring:message code="canyon.save" />" onclick="return confirm('<spring:message code="canyon.confirm.delete.activities" />')" />
			<input type="submit" name="delete" value="<spring:message code="canyon.delete"/>" onclick="return confirm('<spring:message code="canyon.confirm.delete" />')"/>
		</jstl:if>
		
		<acme:cancel url="canyon/administrator/mylist.do" code="canyon.cancel" />

	</form:form>

</security:authorize>