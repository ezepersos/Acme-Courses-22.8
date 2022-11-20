<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="administrator.post.form.label.caption" path="caption"/>
	<acme:input-textarea code="administrator.post.form.label.message" path="message"/>
	<acme:input-url code="administrator.post.form.label.url" path="url"/>
	<acme:input-checkbox code="administrator.post.form.label.informational" path="informational"/>
		<acme:input-checkbox code="administrator.post.form.label.confirm" path="confirm"/>

	<acme:submit test="${command == 'create'}" code="administrator.post.form.button.create" action="/administrator/post/create"/>
</acme:form>