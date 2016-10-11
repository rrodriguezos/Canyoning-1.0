<%--
 * list.jsp
 *
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<display:table name="organisers" id="row" class="displaytag" requestURI="${requestURI}" pagesize="5" keepStatus="true" >
	
	<spring:message	code="organiser.email"  var="email"/>
	<display:column property="email" title="${name}" sortable="true" />
	
	<spring:message	code="organiser.phone"  var="phone"/>
	<display:column property="phone" title="${phone}" sortable="true" />
	

    <display:column>
      <a href="comment/list.do?id=<jstl:out value="${row.organiserComment.id}"/>">
        <spring:message code="organiser.comment" />
      </a>
    </display:column>
	
	</display:table>
