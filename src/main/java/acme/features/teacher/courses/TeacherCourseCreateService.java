package acme.features.teacher.courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.courses.Course;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Teacher;
import acme.utils.SpamFilterService;

@Service
public class TeacherCourseCreateService implements AbstractCreateService<Teacher, Course> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected TeacherCourseRepository repository;
	
	@Autowired
	protected SpamFilterService spamFilterService;

	// AbstractCreateService<Employer, Job> interface -------------------------


	@Override
	public boolean authorise(final Request<Course> request) {
		assert request != null;

		return true;
	}

	@Override
	public void validate(final Request<Course> request, final Course entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("ticker")) {
			Course existing;

			existing = this.repository.findCourseByTicker(entity.getTicker());
			errors.state(request, existing == null, "ticker", "teacher.course.form.error.duplicated");
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
	public void bind(final Request<Course> request, final Course entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "ticker", "abstractCourse", "caption", "link");
		
	}

	@Override
	public void unbind(final Request<Course> request, final Course entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "ticker", "abstractCourse", "caption", "link", "isPublished");
	}

	@Override
	public Course instantiate(final Request<Course> request) {
		assert request != null;

		Course result;
		final Teacher teacher = this.repository.findTeacher(request.getPrincipal().getActiveRoleId());
		result = new Course();
		result.setPublished(false);
		result.setTeacher(teacher);
		return result;
	}

	@Override
	public void create(final Request<Course> request, final Course entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}