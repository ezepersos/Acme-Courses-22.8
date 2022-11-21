package acme.features.learner.help_request;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.follow_up.FollowUp;
import acme.entities.help_requests.HelpRequest;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Learner;

@Service
public class LearnerHelpRequestDeleteService implements AbstractDeleteService<Learner, HelpRequest> {
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected LearnerHelpRequestRepository repository;

		// AbstractDeleteService<Inventor, Item> interface -------------------------


		@Override
		public boolean authorise(final Request<HelpRequest> request) {
			assert request != null;

			final boolean result;
			final int helpRequestId;
			final HelpRequest helpRequest;
			final Learner learner;

			helpRequestId = request.getModel().getInteger("id");
			helpRequest = this.repository.findHelpRequestById(helpRequestId);
			learner = helpRequest.getLearner();
			result = !helpRequest.isPublished() && request.isPrincipal(learner);

			return result;
		}

		@Override
		public void bind(final Request<HelpRequest> request, final HelpRequest entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;
			
			request.bind(entity, errors, "code", "legalStuff", "budget", "startTime", "creationTime", "endingTime", "link");
		}

		@Override
		public void unbind(final Request<HelpRequest> request, final HelpRequest entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			
			model.setAttribute("inventors", this.repository.findTeachers());
			request.unbind(entity, model, "status", "code", "legalStuff", "budget", "startTime", "creationTime", "endingTime", "link", "inventor", "isPublished", "inventors");
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
		public void validate(final Request<HelpRequest> request, final HelpRequest entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;
		}

		@Override
		public void delete(final Request<HelpRequest> request, final HelpRequest entity) {
			assert request != null;
			assert entity != null;
			if(!entity.isPublished()) {
				final Collection<FollowUp> followUps;
				followUps = this.repository.findAllFollowUpByhelpRequestId(entity.getId());
				for (final FollowUp fu : followUps) {
					this.repository.deleteFollowUpById(fu.getId());
				}
				this.repository.delete(entity);
			}
		}

}
