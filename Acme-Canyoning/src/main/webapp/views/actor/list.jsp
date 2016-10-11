<%--
 * register.jsp
 *
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<jstl:if test="${requestURI=='actor/listTrainers.do' || requestURI=='actor/searchTrainer.do'}">
<form action="actor/searchTrainer.do" method="get">
	<label for="q"><spring:message	code="actor.searchtext" />:</label>
	<input type="text" name="q">
	<input type="submit" value="<spring:message	code="common.search" />">
</form>
</jstl:if>

<display:table name="${actor}" id="row" class="displaytag" requestURI="${requestURI}" pagesize="5" keepStatus="true" >
	
	<spring:message	code="actor.email"  var="email"/>
	<display:column property="email" title="${name}" sortable="true" />
	
	<spring:message	code="actor.phone"  var="phone"/>
	<display:column property="phone" title="${phone}" sortable="true" />
	

    <display:column>
      <a href="comment/list.do?id=<jstl:out value="${row.trainerComment.id}"/>">
        <spring:message code="actor.comment" />
      </a>
    </display:column>
	
	</display:table>
	

<security:authorize access="hasRole('ADMIN')">
	<div><a href='actor/register.do'>
        <spring:message code="actor.register" /></a></div>
</security:authorize>
