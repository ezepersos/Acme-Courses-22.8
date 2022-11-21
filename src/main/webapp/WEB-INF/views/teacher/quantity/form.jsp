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
<jstl:if test="${acme:anyOf(command, 'show')}">
	<acme:input-textbox code="teacher.quantity.form.label.title" path="tutorialName"/>	
	<acme:input-textbox code="teacher.quantity.form.label.units" path="units"/>
	<acme:input-textbox code="teacher.quantity.form.label.cost" path="tutorialCost"/>	
	<acme:input-textbox code="teacher.quantity.form.label.abstractTheory" path="tutorialAbstractTheory"/>
	<acme:input-textbox code="teacher.quantity.form.label.link" path="tutorialLink"/>	
</jstl:if>
<jstl:if test="${acme:anyOf(command, 'show') && isPublished == false}">
<acme:submit code="teacher.quantity.form.button.delete" action="/teacher/quantity/delete?id=${id}"/>
</jstl:if>
<jstl:if test="${acme:anyOf(command, 'create')}">
	<acme:input-integer code="teacher.quantity.form.label.select.units" path="units"/>
	<acme:input-select code="teacher.quantity.form.label.select.tutorial" path="tutorialName">
			<jstl:forEach items="${tutorials}" var="optionTutorial">
				<acme:input-option code="${optionTutorial.title}" value="${optionTutorial.title}"/>
			</jstl:forEach>
	</acme:input-select>
		<acme:submit code="teacher.quantity.list.button.create" action="/teacher/quantity/create?courseId=${courseId}"/>		
	</jstl:if>
</acme:form>