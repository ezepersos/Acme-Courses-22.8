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

<acme:form>

	<acme:input-textbox code="learner.helpRequest.form.label.ticker" path="ticker"/>	
	<acme:input-textbox code="learner.helpRequest.form.label.statement" path="statement"/>	
	<acme:input-money code="learner.helpRequest.form.label.budget" path="budget"/>	
	<acme:input-textbox code="learner.helpRequest.form.label.link" path="link"/>	
	<jstl:if test="${acme:anyOf(command, 'show, update, delete, publish')}">
		<acme:input-moment code="learner.helpRequest.form.label.creationTime" path="creationTime" readonly="true"/>
		<acme:input-textbox code="learner.helpRequest.form.label.status" path="status"/>	
	</jstl:if>
	<acme:input-moment code="learner.helpRequest.form.label.startTime" path="startTime"/>	
	<acme:input-moment code="learner.helpRequest.form.label.endingTime" path="endingTime"/>	
	<jstl:choose>	 
		<jstl:when test="${isPublished == true }">
			<acme:button code="learner.helpRequest.form.button.teacher" action="/any/user-account/show?id=${teacher.userAccount.id}"/>
		</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && isPublished == false}">
			<acme:button code="learner.helpRequest.form.button.teacher" action="/any/user-account/show?id=${teacher.userAccount.id}"/>
			<acme:submit code="learner.helpRequest.form.button.update" action="/learner/help-request/update"/>
			<acme:submit code="learner.helpRequest.form.button.delete" action="/learner/help-request/delete"/>
			<acme:submit code="learner.helpRequest.form.button.publish" action="/learner/help-request/publish"/>
		</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'create')}">
			<acme:input-select code="learner.helpRequest.form.label.teacher" path="teacherId">
	   			<jstl:forEach items="${teachers}" var="teacher">
					<acme:input-option code="${teacher.getUserAccount().getUsername()}" value="${teacher.getId()}"/>
				</jstl:forEach>
			</acme:input-select>
			<acme:input-select code="learner.helpRequest.form.label.tutorial" path="tutorialId">
	   			<jstl:forEach items="${tutorials}" var="tutorial">
					<acme:input-option code="${tutorial.getTitle()}" value="${tutorial.getTicker()}"/>
				</jstl:forEach>
			</acme:input-select>
			<acme:submit code="learner.helpRequest.form.button.create" action="/learner/help-request/create"/>
		</jstl:when>		
	</jstl:choose>
	<jstl:if test="${acme:anyOf(command, 'show')}">
		<acme:button code="learner.followUp.form.button.create" action="/learner/follow-up/create?masterId=${id}"/>	
	</jstl:if>
</acme:form>