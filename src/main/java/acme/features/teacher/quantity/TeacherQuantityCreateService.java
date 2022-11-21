package acme.features.teacher.quantity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.courses.Course;
import acme.entities.quantities.Quantity;
import acme.entities.tutorials.Tutorial;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Teacher;

@Service
public class TeacherQuantityCreateService implements AbstractCreateService<Teacher, Quantity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected TeacherQuantityRepository repository;

	// AbstractCreateService<Authenticated, Consumer> ---------------------------


	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		final int teacherid = request.getPrincipal().getActiveRoleId();
		final Course course =  this.repository.findCourseById(request.getModel().getInteger("courseId"));
		return course.getTeacher().getId() == teacherid && !course.isPublished();
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		entity.setTutorial(this.repository.findTutorialByName(request.getModel().getAttribute("tutorialName").toString()));
		request.bind(entity, errors, "units", "tutorialName");
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "units");
		final Collection<Tutorial> tutorials = this.repository.findTutorialByTeacher(request.getPrincipal().getActiveRoleId());
		model.setAttribute("tutorials", tutorials);
		model.setAttribute("courseId", request.getModel().getAttribute("courseId"));
		final Course course =  this.repository.findCourseById(request.getModel().getInteger("courseId"));
		model.setAttribute("isPublished", course.isPublished());

	}

	@Override
	public Quantity instantiate(final Request<Quantity> request) {
		assert request != null;
		int courseId;
		final Course course;
		final Quantity result = new Quantity();
		courseId = request.getModel().getInteger("courseId");
		course = this.repository.findCourseById(courseId);
		result.setCourse(course);
		return result;
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if(this.repository.findQuantityByCourse(entity.getCourse().getId()).stream().anyMatch(x->x.getTutorial() == entity.getTutorial())) {
			errors.state(request, false, "tutorialName", "teacher.quantity.form.error.duplicated");
		}
		

	}

	@Override
	public void create(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;
		final Tutorial item;
		final String name;
		
		name = (String) request.getModel().getAttribute("tutorialName");
		item = this.repository.findTutorialByName(name);

		entity.setTutorial(item);
		this.repository.save(entity);

	}




}