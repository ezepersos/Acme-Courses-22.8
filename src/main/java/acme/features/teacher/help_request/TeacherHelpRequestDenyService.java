package acme.features.teacher.help_request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.help_requests.HelpRequest;
import acme.entities.help_requests.HelpRequestStatus;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Teacher;

@Service
public class TeacherHelpRequestDenyService implements AbstractUpdateService<Teacher, HelpRequest> {
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected TeacherHelpRequestRepository repository;

	@Override
	public boolean authorise(final Request<HelpRequest> request) {
		assert request != null;

		final boolean result;
		final int helpRequestId;
		final HelpRequest helpRequest;
		final Teacher teacher;

		helpRequestId = request.getModel().getInteger("id");
		helpRequest = this.repository.findHelpRequestById(helpRequestId);
		teacher = helpRequest.getTeacher();
		result = helpRequest.isPublished() && request.isPrincipal(teacher);

		return result;
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
	public void bind(final Request<HelpRequest> request, final HelpRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void validate(final Request<HelpRequest> request, final HelpRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void unbind(final Request<HelpRequest> request, final HelpRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "status", "ticker", "statement", "budget", "startTime", "creationTime", "endingTime", "link", "isPublished");
	}


	@Override
	public void update(final Request<HelpRequest> request, final HelpRequest entity) {
		assert request != null;
		assert entity != null;
		
		entity.setStatus(HelpRequestStatus.DENIED);
		this.repository.save(entity);
		
	}

}
