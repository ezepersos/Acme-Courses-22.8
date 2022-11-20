<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="authenticated.learner.form.label.school" path="school"/>
	<acme:input-textbox code="authenticated.learner.form.label.statement" path="statement"/>
	<acme:input-textbox code="authenticated.learner.form.label.hyperlink" path="hyperlink"/>
	<acme:submit test="${command == 'create'}" code="authenticated.learner.form.button.create" action="/authenticated/learner/create"/>
</acme:form>