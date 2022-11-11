<%--
- form.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<acme:form readonly="true">
	<acme:input-textbox code="authenticated.course.form.label.ticker" path="ticker"/>	
	<acme:input-textbox code="authenticated.course.form.label.caption" path="caption"/>	
	<acme:input-textarea code="authenticated.course.form.label.abstractCourse" path="abstractCourse"/>
	<acme:input-textbox code="authenticated.course.form.label.link" path="link"/>
	<acme:input-textbox code="authenticated.course.form.label.costs" path="costs"/>
</acme:form>
