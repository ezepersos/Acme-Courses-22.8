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
	<acme:message code="administrator.dashboard.form.title"/>
</h2>
<table class="table table-sm">
		<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-theory-tutorials"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfTheoryTutorials}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-cost-theory-tutorials"/>
		</th>
		<td>
		<c:choose>
			<c:when test="${empty averageCostOfTheoryTutorialsByCurrency}">
				<p>0</p>
			</c:when>
			<c:otherwise>
				<c:forEach items="${averageCostOfTheoryTutorialsByCurrency}" var="entry">
		    		${entry.key} = ${entry.value}<br>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-cost-theory-tutorials"/>
		</th>
		<td>
		<c:choose>
			<c:when test="${empty averageCostOfTheoryTutorialsByCurrency}">
				<p>0</p>
			</c:when>
			<c:otherwise>
				<c:forEach items="${deviationCostOfTheoryTutorialsByCurrency}" var="entry">
		    		${entry.key} = ${entry.value}<br>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum-cost-theory-tutorials"/>
		</th>
		<td>
		<c:choose>
			<c:when test="${empty averageCostOfTheoryTutorialsByCurrency}">
				<p>0</p>
			</c:when>
			<c:otherwise>
			<c:forEach items="${minimumCostOfTheoryTutorialsByCurrency}" var="entry">
	    		${entry.key} = ${entry.value}<br>
			</c:forEach>
			</c:otherwise>
		</c:choose>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum-cost-theory-tutorials"/>
		</th>
		<td>
		<c:choose>
			<c:when test="${empty averageCostOfTheoryTutorialsByCurrency}">
				<p>0</p>
			</c:when>
			<c:otherwise>
			<c:forEach items="${maximumCostOfTheoryTutorialsByCurrency}" var="entry">
	    		${entry.key} = ${entry.value}<br>
			</c:forEach>
			</c:otherwise>
		</c:choose>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-lab-tutorials"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfLabTutorials}"/>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-cost-lab-tutorials"/>
		</th>
		<td>
		<c:choose>
			<c:when test="${empty averageCostOfTheoryTutorialsByCurrency}">
				<p>0</p>
			</c:when>
			<c:otherwise>
			<c:forEach items="${averageCostOfLabTutorialsByCurrency}" var="entry">
	    		${entry.key} = ${entry.value}<br>
			</c:forEach>
			</c:otherwise>
		</c:choose>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-cost-lab-tutorials"/>
		</th>
		<td>
		<c:choose>
			<c:when test="${empty averageCostOfTheoryTutorialsByCurrency}">
				<p>0</p>
			</c:when>
			<c:otherwise>
			<c:forEach items="${deviationCostOfLabTutorialsByCurrency}" var="entry">
	    		${entry.key} = ${entry.value}<br>
			</c:forEach>
			</c:otherwise>
		</c:choose>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum-cost-lab-tutorials"/>
		</th>
		<td>
		<c:choose>
			<c:when test="${empty averageCostOfTheoryTutorialsByCurrency}">
				<p>0</p>
			</c:when>
			<c:otherwise>
			<c:forEach items="${minimumCostOfLabTutorialsByCurrency}" var="entry">
	    		${entry.key} = ${entry.value}<br>
			</c:forEach>
			</c:otherwise>
		</c:choose>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum-cost-lab-tutorials"/>
		</th>
		<td>
		<c:choose>
			<c:when test="${empty averageCostOfTheoryTutorialsByCurrency}">
				<p>0</p>
			</c:when>
			<c:otherwise>
			<c:forEach items="${maximumCostOfLabTutorialsByCurrency}" var="entry">
	    		${entry.key} = ${entry.value}<br>
			</c:forEach>
			</c:otherwise>
		</c:choose>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-help-requests"/>
		</th>
		<td>
			<c:forEach items="${totalNumberOfHelpRequestByStatus}" var="entry">
	    		${entry.key} = ${entry.value}<br>
			</c:forEach>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-budget-help-requests"/>
		</th>
		<td>
			<c:forEach items="${averageHelpRequestsBudgetByStatus}" var="entry">
	    		${entry.key} = ${entry.value}<br>
			</c:forEach>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-budget-help-requests"/>
		</th>
		<td>
			<c:forEach items="${deviationHelpRequestsBudgetByStatus}" var="entry">
	    		${entry.key} = ${entry.value}<br>
			</c:forEach>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum-budget-help-requests"/>
		</th>
		<td>
			<c:forEach items="${minimumHelpRequestsBudgetByStatus}" var="entry">
	    		${entry.key} = ${entry.value}<br>
			</c:forEach>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum-budget-help-requests"/>
		</th>
		<td>
			<c:forEach items="${maximumHelpRequestsBudgetByStatus}" var="entry">
	    		${entry.key} = ${entry.value}<br>
			</c:forEach>
		</td>
	</tr>
	
	
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.ratio-blahblah"/>
		</th>
		<td>
			<acme:print value="${ratioTutorialBlahblah}"/>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-blahblah"/>
		</th>
		<td>
		<c:choose>
			<c:when test="${empty deviationBlahBlahCostByCurrency}">
				<p>0</p>
			</c:when>
			<c:otherwise>
			<c:forEach items="${averageBlahBlahCostByCurrency}" var="entry">
	    		${entry.key} = ${entry.value}<br>
			</c:forEach>
			</c:otherwise>
		</c:choose>
	</tr><tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-blahblah"/>
		</th>
		<td>
		<c:choose>
			<c:when test="${empty deviationBlahBlahCostByCurrency}">
				<p>0</p>
			</c:when>
			<c:otherwise>
			<c:forEach items="${deviationBlahBlahCostByCurrency}" var="entry">
	    		${entry.key} = ${entry.value}<br>
			</c:forEach>
			</c:otherwise>
		</c:choose>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum-blahblah"/>
		</th>
		<td>
		<c:choose>
			<c:when test="${empty minimumBlahBlahCostByCurrency}">
				<p>0</p>
			</c:when>
			<c:otherwise>
			<c:forEach items="${minimumBlahBlahCostByCurrency}" var="entry">
	    		${entry.key} = ${entry.value}<br>
			</c:forEach>
			</c:otherwise>
		</c:choose>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum-blahblah"/>
		</th>
		<td>		
		<c:choose>
			<c:when test="${empty maximumBlahBlahCostByCurrency}">
				<p>0</p>
			</c:when>
			<c:otherwise>
			<c:forEach items="${maximumBlahBlahCostByCurrency}" var="entry">
	    		${entry.key} = ${entry.value}<br>
			</c:forEach>
			</c:otherwise>
		</c:choose>
		</td>
		
	</tr>

</table>