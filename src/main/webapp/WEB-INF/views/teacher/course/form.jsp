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
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>


<acme:form>
	<acme:input-textbox code="teacher.course.form.label.ticker" path="ticker"/>	
	<acme:input-textbox code="teacher.course.form.label.caption" path="caption"/>	
	<acme:input-textarea code="teacher.course.form.label.abstractCourse" path="abstractCourse"/>
	<acme:input-textbox code="teacher.course.form.label.link" path="link"/>
	<jstl:if test="${acme:anyOf(command, 'show') &&  isPublished == true }">
	<acme:input-textbox code="teacher.course.form.label.costs" path="costs"/>
	</jstl:if>
	<jstl:if test="${acme:anyOf(command, 'show')}">
		<acme:input-textbox code="teacher.course.form.label.published" path="isPublished"/>
	</jstl:if>
	<jstl:if test="${acme:anyOf(command, 'show, update, delete, publish') && isPublished == false}">
		<acme:submit code="teacher.course.form.button.update" action="/teacher/course/update"/>
		<acme:submit code="teacher.course.form.button.delete" action="/teacher/course/delete?id=${id}"/>
		<acme:submit code="teacher.course.form.button.publish" action="/teacher/course/publish?id=${id}"/>
	</jstl:if>
	<jstl:if test="${acme:anyOf(command, 'create')}">
	<acme:submit code="teacher.course.form.button.create" action="/teacher/course/create"/>
	</jstl:if>
</acme:form>
<jstl:if test="${acme:anyOf(command, 'show') && isPublished == true}">
	<acme:button code="teacher.course.form.button.quantity.labTutorial" action="/teacher/tutorial/list-lab?courseId=${id}"/>
	<acme:button code="teacher.course.form.button.quantity.theoryTutorial" action="/teacher/tutorial/list-theory?courseId=${id}"/>
</jstl:if>
