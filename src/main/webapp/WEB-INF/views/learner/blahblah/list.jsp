<%--
- list.jsp
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
<acme:list>
	<acme:list-column code="learner.blahblah.list.label.creationMoment" path="creationMoment" width="10%"/>
	<acme:list-column code="learner.blahblah.list.label.caption" path="caption" width="10%"/>
	<acme:list-column code="learner.blahblah.list.label.summary" path="summary" width="50%"/>
	<acme:list-column code="learner.blahblah.list.label.cost" path="cost" width="30%"/>
</acme:list>
<jstl:if test="${acme:anyOf(command, 'list')}">
	<acme:button code="learner.blahblah.list.button.create" action="/learner/blahblah/create"/>
</jstl:if>