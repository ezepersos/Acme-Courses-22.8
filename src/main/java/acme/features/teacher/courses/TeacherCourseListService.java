package acme.features.teacher.courses;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.courses.Course;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Teacher;

@Service
public class TeacherCourseListService implements AbstractListService<Teacher, Course> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected TeacherCourseRepository repository;


	@Override
	public boolean authorise(final Request<Course> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Course> findMany(final Request<Course> request) {
		assert request != null;

		Collection<Course> result;
		Principal principal;

		principal = request.getPrincipal();
		result = this.repository.findCoursesByTeacher(principal.getActiveRoleId());

		return result;
	}

	@Override
	public void unbind(final Request<Course> request, final Course entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "caption", "abstractCourse", "link", "isPublished");
	}
}