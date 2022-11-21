package acme.features.teacher.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantities.Quantity;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Teacher;

@Service
public class TeacherQuantityDeleteService implements AbstractDeleteService<Teacher, Quantity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected TeacherQuantityRepository repository;

	// AbstractDeleteService<Teacher, Quantity> interface -------------------------


	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		final int teacherid = request.getPrincipal().getActiveRoleId();
		final Quantity quantity = this.repository.findQuantityById(request.getModel().getInteger("id"));
		return quantity.getCourse().getTeacher().getId() == teacherid && !quantity.getCourse().isPublished();
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "amount", "tutorialName");

	}
	@Override
	public Quantity findOne(final Request<Quantity> request) {
		assert request != null;
		
		Integer id;
		Quantity result;
		id = request.getModel().getInteger("id");
		result = this.repository.findQuantityById(id);
		return result;
	}
	

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
	}

	@Override
	public void delete(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;
		this.repository.delete(entity);
	}
}