package acme.features.learner.blahblah;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.blahblah.Blahblah;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Learner;

@Service
public class LearnerBlahblahShowService implements AbstractShowService<Learner, Blahblah>{

	// Internal state
	
	@Autowired
	protected LearnerBlahblahRepository repository;
	
	// Interface
	
	@Override
	public boolean authorise(final Request<Blahblah> request) {
		assert request != null;
		
		return true;
	}
	
	@Override
	public Blahblah findOne(final Request<Blahblah> request) {
		assert request != null;
		Blahblah result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findBlahblahById(id);
		return result;
	}
	
	@Override
	public void unbind(final Request<Blahblah> request, final Blahblah entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "creationMoment", "caption", "summary", "cost", "startTime", "endingTime", "hlink", "ticker");		
	}
	
	
}
