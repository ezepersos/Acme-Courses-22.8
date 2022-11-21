package acme.features.learner.follow_up;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.follow_up.FollowUp;
import acme.entities.help_requests.HelpRequest;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Learner;
import acme.utils.SpamFilterService;

@Service
public class LearnerFollowUpCreateService implements AbstractCreateService<Learner, FollowUp> {

	// Internal state ---------------------------------------------------------
	
	private static String MASTERID = "masterId";

	@Autowired
	protected LearnerFollowUpRepository repository;

	@Autowired
	protected SpamFilterService spamFilterService;

	// AbstractCreateService<Authenticated, Consumer> ---------------------------


	@Override
	public boolean authorise(final Request<FollowUp> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<FollowUp> request, final FollowUp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "message", "link");
	}

	@Override
	public void unbind(final Request<FollowUp> request, final FollowUp entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "instantiationMoment", "message", "link", "automaticSequenceNumber");
		model.setAttribute(LearnerFollowUpCreateService.MASTERID, request.getModel().getAttribute(LearnerFollowUpCreateService.MASTERID));

	}

	@Override
	public FollowUp instantiate(final Request<FollowUp> request) {
		assert request != null;
		int helpRequestId;
		HelpRequest helpRequest;
		final FollowUp result = new FollowUp();
		helpRequestId = request.getModel().getInteger(LearnerFollowUpCreateService.MASTERID);
		helpRequest = this.repository.findHelpRequestById(helpRequestId);
		result.setInstantiationMoment(Date.from(Instant.now()));
		result.setHelpRequest(helpRequest);
		result.getAutomaticSequenceNumber();
		return result;
	}

	@Override
	public void validate(final Request<FollowUp> request, final FollowUp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if(this.spamFilterService.isSpam(entity.getMessage())) {
			errors.state(request, false, "message", "learner.followUp.form.error.spam");
		}

		if(this.spamFilterService.isSpam(entity.getLink())) {
			errors.state(request, false, "link", "learner.followUp.form.error.spam");
		}
		
		errors.state(request, request.getModel().getBoolean("confirm"), "confirm", "learner.followUp.form.error.must-confirm");

	}

	@Override
	public void create(final Request<FollowUp> request, final FollowUp entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);

	}



}