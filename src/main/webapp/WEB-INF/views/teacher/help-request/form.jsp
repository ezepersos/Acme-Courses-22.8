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

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="true">
    <acme:input-textbox code="teacher.helpRequest.form.label.status" path="status"/>	
	<acme:input-textbox code="teacher.helpRequest.form.label.ticker" path="ticker"/>	
	<acme:input-textbox code="teacher.helpRequest.form.label.statement" path="statement"/>	
	<acme:input-textbox code="teacher.helpRequest.form.label.budget" path="budget"/>	
	<acme:input-textbox code="teacher.helpRequest.form.label.startTime" path="startTime"/>	
	<acme:input-textbox code="teacher.helpRequest.form.label.creationTime" path="creationTime"/>	
	<acme:input-textbox code="teacher.helpRequest.form.label.endingTime" path="endingTime"/>	
	<acme:input-textbox code="teacher.helpRequest.form.label.link" path="link"/>	
	<acme:button code="teacher.followup.form.button.create" action="/teacher/follow-up/create?masterId=${id}"/>
	<acme:button code="teacher.helprequest.form.button.learner" action="/any/user-account/show?id=${learner.userAccount.id}"/>
	<br/>
	<jstl:if test="${statusStr == 'PROPOSED'}">
			<acme:submit code="teacher.helpRequest.form.label.accept" action="/teacher/help-request/accept"/>
			<acme:submit code="teacher.helpRequest.form.label.deny" action="/teacher/help-request/deny"/>
	</jstl:if>	
</acme:form>