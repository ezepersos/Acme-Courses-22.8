package acme.features.teacher.courses;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.courses.Course;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.AbstractEntity;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;
import acme.roles.Teacher;

@Service
public class TeacherCourseShowService implements AbstractShowService<Teacher, Course> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected TeacherCourseRepository repository;

	@Override
	public boolean authorise(final Request<Course> request) {
		assert request != null;
		int id;
		Principal principal;
		principal = request.getPrincipal();
		final List<Integer> courses = this.repository.findCoursesByTeacher(principal.getActiveRoleId()).stream().map(AbstractEntity::getId).collect(Collectors.toList());
		id = request.getModel().getInteger("id");
		return courses.contains(id);
	}

	@Override
	public void unbind(final Request<Course> request, final Course entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "ticker", "caption", "abstractCourse", "link", "isPublished");
		int id;
		id = request.getModel().getInteger("id");
		final Collection<Object[]> costs;
		costs = this.repository.costByCurrency(id);
		String res = "";
		int i = 0;
		for(final Object[] obj: costs) {

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
		model.setAttribute("costs", res);
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

	// Internal state ---------------------------------------------------------
}
