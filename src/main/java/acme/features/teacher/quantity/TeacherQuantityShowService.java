package acme.features.teacher.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantities.Quantity;
import acme.entities.tutorials.Tutorial;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;
import acme.roles.Teacher;

@Service
public class TeacherQuantityShowService implements AbstractShowService<Teacher, Quantity>{

	// Internal state
	
	@Autowired
	protected TeacherQuantityRepository repository;
	
	// Interface
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		Principal principal;
		principal = request.getPrincipal();
		Quantity quantity;
		int id;
		id = request.getModel().getInteger("id");
		quantity = this.repository.findQuantityById(id);
		final int activeId = principal.getActiveRoleId();
		return quantity.getCourse().getTeacher().getId()==activeId;
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
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final Tutorial tutorial = this.repository.findTutorialByQuantity(entity.getId());

		request.unbind(entity, model, "units");
		model.setAttribute("tutorialTicker", tutorial.getTicker());
		model.setAttribute("tutorialName", tutorial.getTitle());
		model.setAttribute("tutorialCost", tutorial.getCost());
		model.setAttribute("tutorialAbstractTheory", tutorial.getAbstractTheory());
		model.setAttribute("tutorialType", tutorial.getTutorialType());
		model.setAttribute("tutorialLink", tutorial.getLink());
		model.setAttribute("isPublished", entity.getCourse().isPublished());
		
	}
}