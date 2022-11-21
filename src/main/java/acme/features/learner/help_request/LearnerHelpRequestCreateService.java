
package acme.features.learner.help_request;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.help_requests.HelpRequest;
import acme.entities.help_requests.HelpRequestStatus;
import acme.entities.systemConfigurations.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Learner;

@Service
public class LearnerHelpRequestCreateService implements AbstractCreateService<Learner, HelpRequest> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LearnerHelpRequestRepository repository;

	// AbstractCreateService<Employer, Job> interface -------------------------


	@Override
	public boolean authorise(final Request<HelpRequest> request) {
		assert request != null;

		return true;
	}

	@Override
	public void validate(final Request<HelpRequest> request, final HelpRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		final List<SystemConfiguration> configurationColl = new ArrayList<>(this.repository.findAllConfigurations());
		final String acceptedCurrencies = configurationColl.get(0).getAcceptedCurrencies();
		final List<String> currencies = new ArrayList<String>();
		for (final String s : acceptedCurrencies.split(";")) {
			currencies.add(s);
		}

		if (!errors.hasErrors("ticker")) {
			HelpRequest existing;

			existing = this.repository.findHelpRequestByTicker(entity.getTicker());
			errors.state(request, existing == null, "ticker", "learner.helpRequest.form.error.duplicated");
		}

		if (!errors.hasErrors("cost")) {
			errors.state(request, !(!currencies.contains(entity.getBudget().getCurrency()) || entity.getBudget().getCurrency() == null || entity.getBudget().getCurrency().length() == 0), "budget", "learner.helprequest.form.error.incorrectCurrency");
			errors.state(request, !(entity.getBudget().getAmount() <= 0.0 || entity.getBudget().getAmount() == null), "budget", "learner.helprequest.form.error.incorrectQuantity");
		}

		if (!errors.hasErrors("startTime")) {
			final Date creationTime = entity.getCreationTime();
			final Calendar calCreation = Calendar.getInstance();
			calCreation.setTime(creationTime);
			calCreation.add(Calendar.WEEK_OF_MONTH, 1);
			final Date creationDateAfterMonth = calCreation.getTime();
			final Date startTime = entity.getStartTime();

			errors.state(request, (startTime.after(creationDateAfterMonth)), "startTime", "learner.helpRequest.form.error.creationDateAfter");
		}

		if (!errors.hasErrors("endTime") && entity.getStartTime() != null) {
				final Date startTime = entity.getStartTime();
				final Calendar calStart = Calendar.getInstance();
				calStart.setTime(startTime);
				calStart.add(Calendar.DAY_OF_MONTH, 1);

				final Date startDateAfterMonth = calStart.getTime();
				final Date endingTime = entity.getEndingTime();

				errors.state(request, (endingTime.after(startDateAfterMonth)), "endingTime", "learner.helpRequest.form.error.startDateAfter");
		}

	}

	@Override
	public void bind(final Request<HelpRequest> request, final HelpRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		final Calendar calCreation = Calendar.getInstance();
		calCreation.add(Calendar.MILLISECOND, -100);
		entity.setCreationTime(calCreation.getTime());
		entity.setTeacher(this.repository.findTeacherById(request.getModel().getInteger("teacherId")));
		entity.setTutorial(this.repository.findTutorialByTicker(request.getModel().getString("tutorialId")));

		request.bind(entity, errors, "ticker", "statement", "budget", "startTime", "endingTime", "link", "teacherId", "tutorialId");

	}

	@Override
	public void unbind(final Request<HelpRequest> request, final HelpRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("teachers", this.repository.findTeachers());
		model.setAttribute("tutorials", this.repository.findTutorials());
		request.unbind(entity, model, "status", "ticker", "statement", "budget", "startTime", "endingTime", "link", "teacher", "tutorial","isPublished");
	}

	@Override
	public HelpRequest instantiate(final Request<HelpRequest> request) {
		assert request != null;
		HelpRequest result;
		final Learner learner;
		learner = this.repository.findOneLearnerById(request.getPrincipal().getActiveRoleId());
		result = new HelpRequest();
		result.setPublished(false);
		result.setLearner(learner);
		result.setStatus(HelpRequestStatus.PROPOSED);
		return result;
	}

	@Override
	public void create(final Request<HelpRequest> request, final HelpRequest entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
