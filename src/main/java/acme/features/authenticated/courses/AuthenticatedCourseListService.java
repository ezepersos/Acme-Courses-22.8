package acme.features.authenticated.courses;
/*
 * AuthenticatedConsumerCreateService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.courses.Course;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedCourseListService implements AbstractListService<Authenticated, Course> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedCourseRepository repository;

	
	@Override
	public boolean authorise(final Request<Course> request) {
		assert request != null;
		
		return true;
	}
	
	@Override
	public Collection<Course> findMany(final Request<Course> request) {
		assert request != null;
		
		Collection<Course> result;
		
		result = this.repository.findAllPublishedCourses();
		
		return result;
	}
	
	@Override
	public void unbind(final Request<Course> request, final Course entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "caption", "abstractCourse", "link");
	}
}