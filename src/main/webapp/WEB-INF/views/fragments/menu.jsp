<%--
- menu.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.any.list-theory" action="/any/tutorial/list-theory"/>
			<acme:menu-suboption code="master.menu.any.list-lab" action="/any/tutorial/list-lab"/>
			<acme:menu-suboption code="master.menu.any.list-blink" action="/any/blink/list"/>
			<acme:menu-suboption code="master.menu.any.list-userAccounts" action="/any/user-account/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link" action="http://www.example.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-ezequiel" action="https://www.goodreads.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-andrea" action="https://www.imdb.com/title/tt3783958/?ref_=nv_sr_srsg_0"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.authenticated.system-config" action="/authenticated/system-configuration/show"/>
			<acme:menu-suboption code="master.menu.authenticated.list-post" action="/authenticated/post/list"/>
			<acme:menu-suboption code="master.menu.any.list-theory" action="/any/tutorial/list-theory"/>
			<acme:menu-suboption code="master.menu.any.list-lab" action="/any/tutorial/list-lab"/>
			<acme:menu-suboption code="master.menu.any.list-blink" action="/any/blink/list"/>
			<acme:menu-suboption code="master.menu.any.list-userAccounts" action="/any/user-account/list"/>
		</acme:menu-option>
    
		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-suboption code="master.menu.administrator.system-configuration" action="/administrator/system-configuration/show"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.post" action="/administrator/post/create"/>
			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/administrator-dashboard/show"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/administrator/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/administrator/populate-sample"/>			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shut-down" action="/administrator/shut-down"/>
		</acme:menu-option>
		
    <acme:menu-option code="master.menu.teacher" access="hasRole('Teacher')">
      <acme:menu-suboption code="master.menu.teacher.list-courses" action="/teacher/course/list"/>
			<acme:menu-suboption code="master.menu.teacher.list-theory" action="/teacher/tutorial/list-theory"/>
			<acme:menu-suboption code="master.menu.teacher.list-lab" action="/teacher/tutorial/list-lab"/>
      <acme:menu-suboption code="master.menu.teacher.list-follow-up" action="/teacher/follow-up/list"/>
			<acme:menu-suboption code="master.menu.teacher.list-help-request" action="/teacher/help-request/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.learner" access="hasRole('Learner')">
			<acme:menu-suboption code="master.menu.learner.list-help-request" action="/learner/help-request/list"/>
      <acme:menu-suboption code="master.menu.learner.list-follow-up" action="/learner/follow-up/list"/>
      <acme:menu-suboption code="master.menu.learner.list-blahblah" action="/learner/blahblah/list"/>
      <acme:menu-separator/>
      <acme:menu-suboption code="master.menu.learner.dashboard" action="/learner/learner-dashboard/show"/>	
		</acme:menu-option>

	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-learner" action="/authenticated/learner/create" access="!hasRole('Learner')"/>
			<acme:menu-suboption code="master.menu.user-account.become-teacher" action="/authenticated/teacher/create" access="!hasRole('Teacher')"/>
		</acme:menu-option>
		

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

