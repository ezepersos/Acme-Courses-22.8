package acme.features.teacher.courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.courses.Course;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Teacher;
import acme.utils.SpamFilterService;

@Service
public class TeacherCourseUpdateService implements AbstractUpdateService<Teacher, Course> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected TeacherCourseRepository repository;
	
	@Autowired
	protected SpamFilterService spamFilterService;

	// AbstractUpdateService<Teacher, Course> interface ---------------------------


	@Override
	public boolean authorise(final Request<Course> request) {
		assert request != null;

		final boolean result;
		final int courseId;
		final Course course;
		final Teacher teacher;
		
		courseId = request.getModel().getInteger("id");
		course = this.repository.findCourseById(courseId);
		teacher = course.getTeacher();
		result = !course.isPublished() && request.isPrincipal(teacher);

		return result;
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

		request.bind(entity, errors, "ticker", "caption", "abstractCourse", "link");
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
		
		if(this.spamFilterService.isSpam(entity.getAbstractCourse())) {
			errors.state(request, false, "abstractCourse", "teacher.course.form.error.spam");
		}
		
		if(this.spamFilterService.isSpam(entity.getCaption())) {
			errors.state(request, false, "caption", "teacher.course.form.error.spam");
		}
		
		if(this.spamFilterService.isSpam(entity.getLink())) {
			errors.state(request, false, "link", "teacher.course.form.error.spam");
		}

	}

	@Override
	public void unbind(final Request<Course> request, final Course entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "caption", "abstractCourse", "link", "isPublished");
	}


	@Override
	public void update(final Request<Course> request, final Course entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
}