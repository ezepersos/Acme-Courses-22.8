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
	<acme:input-textbox code="learner.followup.form.label.caption" path="caption"/>	
	<acme:input-textbox code="learner.followup.form.label.hlink" path="hlink"/>	
	<acme:input-textbox code="learner.followup.form.label.ticker" path="ticker"/>	
	<acme:input-textbox code="learner.followup.form.label.summary" path="summary"/>	
	<acme:input-money code="learner.followup.form.label.cost" path="cost"/>	
    <acme:input-moment code="learner.followup.form.label.startTime" path="startTime"/>	
    <acme:input-moment code="learner.followup.form.label.endingTime" path="endingTime"/>

	<jstl:if test="${acme:anyOf(command, 'show, update, delete')}">
		<acme:input-moment code="learner.blahblah.form.label.creationMoment" path="creationMoment" readonly="true"/>
	</jstl:if>
	<jstl:choose>	 
		<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
			<acme:input-select code="learner.blahblah.form.label.teacher" path="tutorialId">
	   			<jstl:forEach items="${tutorials}" var="tutorial">
					<acme:input-option code="${tutorial.getTitle()}" value="${tutorial.getId()}"/>
				</jstl:forEach>
			</acme:input-select>
			<acme:submit code="learner.helpRequest.form.button.update" action="/learner/blahblah/update"/>
			<acme:submit code="learner.helpRequest.form.button.delete" action="/learner/blahblah/delete"/>
		</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'create')}">
			<acme:input-select code="learner.blahblah.form.label.teacher" path="tutorialId">
	   			<jstl:forEach items="${tutorials}" var="tutorial">
					<acme:input-option code="${tutorial.getTitle()}" value="${tutorial.getId()}"/>
				</jstl:forEach>
			</acme:input-select>
			<acme:submit code="learner.blahblah.form.button.create" action="/learner/blahblah/create"/>
		</jstl:when>		
	</jstl:choose>
</acme:form>