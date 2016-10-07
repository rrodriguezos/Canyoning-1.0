<%--

 * register.jsp

 *

 * Copyright (C) 2016 Universidad de Sevilla

 * 

 * The use of this project is hereby constrained to the conditions of the 

 * TDG Licence, a copy of which you may download from 

 * http://www.tdg-seville.info/License.html

 --%>



<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="isAnonymous()">

	<form:form action="customer/register.do"
		modelAttribute="customerRegisterForm">


		<acme:textbox code="customer.username" path="username" />

		<acme:password code="customer.password" path="password" />
		<acme:password code="customer.confirmPassword" path="confirmPassword" />


		<acme:textbox code="customer.email" path="email" />

		<acme:textbox code="customer.phone" path="phone" />

		<acme:checkbox code="customer.accept" path="accept"
			url="welcome/conditions.do" />

		<acme:submit name="save" code="customer.save" />
		<acme:cancel url="welcome/index.do" code="customer.cancel" />

	</form:form>

</security:authorize>