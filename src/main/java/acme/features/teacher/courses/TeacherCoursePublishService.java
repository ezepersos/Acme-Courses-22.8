package acme.features.teacher.courses;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.courses.Course;
import acme.entities.quantities.Quantity;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.entities.AbstractEntity;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Teacher;

@Service
public class TeacherCoursePublishService implements AbstractUpdateService<Teacher, Course> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected TeacherCourseRepository repository;

	// AbstractUpdateService<Teacher, Course> interface ---------------------------


	@Override
	public boolean authorise(final Request<Course> request) {
		assert request != null;

		final int id;
		final Course course;
		
		id = request.getModel().getInteger("id");
		course = this.repository.findCourseById(id);
		Principal principal;
		principal = request.getPrincipal();
		final List<Integer> courses = this.repository.findCoursesByTeacher(principal.getActiveRoleId()).stream().map(AbstractEntity::getId).collect(Collectors.toList());
		return !course.isPublished() && courses.contains(id);
	}
	
	@Override
	public Course findOne(final Request<Course> request) {
		assert request != null;

		Course result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findCourseById(id);

		return result;
	}
	
	@Override
	public void bind(final Request<Course> request, final Course entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "ticker", "abstractCourse", "caption", "link");
	}

	@Override
	public void validate(final Request<Course> request, final Course entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("ticker")) {
			Course existing;

			existing = this.repository.findCourseByTicker(entity.getTicker());
			errors.state(request, existing == null || existing.getId() == entity.getId(), "ticker", "teacher.course.form.error.duplicated");
		}

	}

	@Override
	public void unbind(final Request<Course> request, final Course entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "abstractCourse", "caption", "link", "isPublished");
	}


	@Override
	public void update(final Request<Course> request, final Course entity) {
		assert request != null;
		assert entity != null;

		entity.setPublished(true);
		final Collection<Quantity> quantities =this.repository.findAllQuantitiesByCourseId(entity.getId());
		for(final Quantity q: quantities) {
			q.getTutorial().setPublished(true);
			this.repository.save(q);
		}
		this.repository.save(entity);
	}

}