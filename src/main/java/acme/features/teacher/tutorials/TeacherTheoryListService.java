/*
 * AuthenticatedAnnouncementListService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.teacher.tutorials;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tutorials.Tutorial;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.AbstractEntity;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Teacher;

@Service
public class TeacherTheoryListService implements AbstractListService<Teacher, Tutorial> {

	// Internal state ---------------------------------------------------------
	
	private static final String	COURSEID	= "courseId";

	@Autowired
	protected TeacherTutorialRepository repository;

	// AbstractListService<Administrator, Item> interface --------------


	@Override
	public boolean authorise(final Request<Tutorial> request) {
		assert request != null;
		int id;
		Principal principal;
		principal = request.getPrincipal();
		if (request.getModel().hasAttribute(TeacherTheoryListService.COURSEID)) {
			final List<Integer> courses = this.repository.findAllCoursesByTeacherId(principal.getActiveRoleId()).stream().map(AbstractEntity::getId).collect(Collectors.toList());
			id = request.getModel().getInteger(TeacherTheoryListService.COURSEID);
			if (!courses.contains(id)) {
				return false;
			}
		}
		return true;


	}

	@Override
	public Collection<Tutorial> findMany(final Request<Tutorial> request) {
		assert request != null;
		Integer id;
		Collection<Tutorial> result;
		if (request.getModel().hasAttribute(TeacherTheoryListService.COURSEID)) {
			id = request.getModel().getInteger(TeacherTheoryListService.COURSEID);
			result = this.repository.findTutorialByCourse(id);
			return result;
		} else {
			id = request.getPrincipal().getActiveRoleId();
			result = this.repository.findTheoryByTeacher(id);
			return result;
		}
		}
	

	@Override
	public void unbind(final Request<Tutorial> request, final Tutorial entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "ticker", "abstractTheory", "cost", "link");
		model.setAttribute("courseId", false);
		if (request.getModel().hasAttribute(TeacherTheoryListService.COURSEID)) {
			model.setAttribute("courseId", true);	
		}else {
			model.setAttribute("courseId", false);
		}
	}

}
