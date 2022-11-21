package acme.features.teacher.quantity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.courses.Course;
import acme.entities.quantities.Quantity;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Teacher;

@Service
public class TeacherQuantityListService implements AbstractListService<Teacher, Quantity>{

	// Internal state
	
	@Autowired
	protected TeacherQuantityRepository repository;
	
	// Interface
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		Principal principal;
		principal = request.getPrincipal();
		final Integer courseId = request.getModel().getInteger("courseId");
		final int activeId = principal.getActiveRoleId();
		final Course course = this.repository.findCourseById(courseId);
		return course.getTeacher().getId() == activeId;
	}
	
	@Override
	public Collection<Quantity> findMany(final Request<Quantity> request) {
		assert request != null;
		
		final Integer courseId = request.getModel().getInteger("courseId");
		return this.repository.findQuantityByCourse(courseId);
	}
	
	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		final String name = entity.getTutorial().getTitle();

		model.setAttribute("tutorialName", name);

		request.unbind(entity, model, "units");
		model.setAttribute("tutorialType", entity.getTutorial().getTutorialType().toString().toLowerCase());
		model.setAttribute("tutorialAbstractTheory", entity.getTutorial().getAbstractTheory());
	}
	
	@Override
    public void unbind(final Request<Quantity> request, final Collection<Quantity> entity, final Model model) {
        assert request != null;
        assert entity != null;
        assert model != null;
        int courseId;
        courseId = request.getModel().getInteger("courseId");
        model.setAttribute("courseId", courseId);
        final Course course = this.repository.findCourseById(courseId);
        model.setAttribute("isPublished", course.isPublished());

    }
}
	
	
	