/*
 * AuthenticatedAnnouncementShowService.java
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

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tutorials.Tutorial;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.AbstractEntity;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;
import acme.roles.Teacher;

@Service
public class TeacherTutorialShowService implements AbstractShowService<Teacher, Tutorial> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected TeacherTutorialRepository repository;

	// AbstractShowService<Administrator, Announcement> interface --------------


	@Override
	public boolean authorise(final Request<Tutorial> request) {
		assert request != null;
		int id;
		Principal principal;
		principal = request.getPrincipal();
		if (request.getModel().hasAttribute("courseId")) {
			final List<Integer> toolkits = this.repository.findAllCoursesByTeacherId(principal.getActiveRoleId()).stream().map(AbstractEntity::getId).collect(Collectors.toList());
			id = request.getModel().getInteger("courseId");
			if (!toolkits.contains(id)) {
				return false;
			}
		}
		return true;

	}

	@Override
	public void unbind(final Request<Tutorial> request, final Tutorial entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "ticker", "abstractTheory", "cost", "link");
	}

	@Override
	public Tutorial findOne(final Request<Tutorial> request) {
		assert request != null;

		Tutorial result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findTutorialById(id);

		return result;
	}

}
