package acme.features.teacher.courses;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.courses.Course;
import acme.entities.quantities.Quantity;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Teacher;

@Service
public class TeacherCourseDeleteService implements AbstractDeleteService<Teacher, Course> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected TeacherCourseRepository repository;

	// AbstractDeleteService<Employer, Duty> interface -------------------------


	@Override
	public boolean authorise(final Request<Course> request) {
		assert request != null;
		int id;

		Course course = null;
		id = request.getModel().getInteger("id");
		course = this.repository.findCourseById(id);
		Principal principal;
		principal = request.getPrincipal();
		final Collection<Course> courses = this.repository.findCoursesByTeacher(principal.getActiveRoleId());
		return !course.isPublished() && courses.contains(course);
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
	public void unbind(final Request<Course> request, final Course entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "caption", "abstractCourse", "link", "isPublished");
		int id;
		id = request.getModel().getInteger("id");
		final Collection<Object[]> retailPrice;
		retailPrice = this.repository.costByCurrency(id);
		String res = "";
		int i = 0;
		for(final Object[] obj: retailPrice) {
			final Double aux = Double.parseDouble(obj[0].toString());
			final NumberFormat formatter = new DecimalFormat("#0.00");
			final String formattedNumber = formatter.format(aux);
			
			if(i == 0) {
				
				res =res.concat(formattedNumber).concat(obj[1].toString());
			}else {
				res = res.concat("+").concat(formattedNumber).concat(obj[1].toString());
			}
			i++;
			
		}
		model.setAttribute("cost", res);
	}

	@Override
	public void validate(final Request<Course> request, final Course entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Course> request, final Course entity) {
		assert request != null;
		assert entity != null;
		
		if(!entity.isPublished()) {
			Collection<Quantity> quantities;

			quantities = this.repository.findAllQuantitiesByCourseId(entity.getId());
			for (final Quantity qty : quantities) {
				this.repository.deleteQuantityId(qty.getId());
			}
			
			this.repository.delete(entity);
		}
		
		this.repository.delete(entity);
	}

}