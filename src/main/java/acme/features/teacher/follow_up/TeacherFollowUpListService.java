package acme.features.teacher.follow_up;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import acme.entities.follow_up.FollowUp;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Teacher;

@Service
public class TeacherFollowUpListService implements AbstractListService<Teacher, FollowUp>{

	// Internal state
	
	@Autowired
	protected TeacherFollowUpRepository repository;
	
	// Interface
	
	@Override
	public boolean authorise(final Request<FollowUp> request) {
		assert request != null;
		return true;
	}
	
	@Override
	public Collection<FollowUp> findMany(final Request<FollowUp> request) {
		assert request != null;
		
		Collection<FollowUp> result;
		Principal principal;

		principal = request.getPrincipal();
		result = this.repository.findAllFollowUpsByTeacherId(principal.getActiveRoleId());

		return result;
	}
	
	@Override
	public void unbind(final Request<FollowUp> request, final FollowUp entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "instantiationMoment", "message", "link", "automaticSequenceNumber");
	}
	
	
	
	
	
	
}


