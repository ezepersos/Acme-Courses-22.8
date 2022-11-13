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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<h2>
	<acme:message code="learner.dashboard.form.title"/>
</h2>
<table class="table table-sm">
		<tr>
		<th scope="row">
			<acme:message code="learner.dashboard.form.label.total-number-help-requests"/>
		</th>
		<td>
			<c:forEach items="${totalNumberOfHelpRequestByStatus}" var="entry">
	    		${entry.key} = ${entry.value}<br>
			</c:forEach>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="learner.dashboard.form.label.average-budget-help-requests"/>
		</th>
		<td>
			<c:forEach items="${averageHelpRequestsBudgetByStatus}" var="entry">
	    		${entry.key} = ${entry.value}<br>
			</c:forEach>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="learner.dashboard.form.label.deviation-budget-help-requests"/>
		</th>
		<td>
			<c:forEach items="${deviationHelpRequestsBudgetByStatus}" var="entry">
	    		${entry.key} = ${entry.value}<br>
			</c:forEach>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="learner.dashboard.form.label.minimum-budget-help-requests"/>
		</th>
		<td>
			<c:forEach items="${minimumHelpRequestsBudgetByStatus}" var="entry">
	    		${entry.key} = ${entry.value}<br>
			</c:forEach>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="learner.dashboard.form.label.maximum-budget-help-requests"/>
		</th>
		<td>
			<c:forEach items="${maximumHelpRequestsBudgetByStatus}" var="entry">
	    		${entry.key} = ${entry.value}<br>
			</c:forEach>
		</td>
	</tr>

</table>