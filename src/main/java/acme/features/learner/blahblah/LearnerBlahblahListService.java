package acme.features.learner.blahblah;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.blahblah.Blahblah;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Learner;


@Service
public class LearnerBlahblahListService implements AbstractListService<Learner, Blahblah>{

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
	public Collection<Blahblah> findMany(final Request<Blahblah> request) {
		assert request != null;
		Collection<Blahblah> result;
		result = this.repository.findAllBlahblah();
		return result;
	}
	
	@Override
	public void unbind(final Request<Blahblah> request, final Blahblah entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "creationMoment", "caption", "summary", "cost");
	}
	
	
	
	
	
	
}


