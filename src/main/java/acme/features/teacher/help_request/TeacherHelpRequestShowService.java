package acme.features.teacher.help_request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.help_requests.HelpRequest;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Teacher;

@Service
public class TeacherHelpRequestShowService implements AbstractShowService<Teacher, HelpRequest>{

	// Internal state
	
	@Autowired
	protected TeacherHelpRequestRepository repository;
	
	// Interface
	
	@Override
	public boolean authorise(final Request<HelpRequest> request) {
		assert request != null;
		
		return true;
	}
	
	@Override
	public HelpRequest findOne(final Request<HelpRequest> request) {
		assert request != null;

		HelpRequest result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findHelpRequestById(id);

		return result;
	}
	
	@Override
	public void unbind(final Request<HelpRequest> request, final HelpRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "status", "ticker", "statement", "budget", "startTime", "creationTime", "endingTime", "link", "learner", "tutorial");
		
	}
	
	
}
