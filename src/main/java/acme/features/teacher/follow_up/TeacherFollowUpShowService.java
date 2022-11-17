package acme.features.teacher.follow_up;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import acme.entities.follow_up.FollowUp;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Teacher;

@Service
public class TeacherFollowUpShowService implements AbstractShowService<Teacher, FollowUp>{

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
	public FollowUp findOne(final Request<FollowUp> request) {
		assert request != null;

		FollowUp result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findFollowUpById(id);

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
