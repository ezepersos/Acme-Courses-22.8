package acme.features.teacher.help_request;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.help_requests.HelpRequest;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Teacher;

@Service
public class TeacherHelpRequestListService implements AbstractListService<Teacher, HelpRequest>{

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
	public Collection<HelpRequest> findMany(final Request<HelpRequest> request) {
		assert request != null;
		
		Collection<HelpRequest> result;
		Principal principal;

		principal = request.getPrincipal();
		result = this.repository.findAllHelpRequestByTeacherId(principal.getActiveRoleId());

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


