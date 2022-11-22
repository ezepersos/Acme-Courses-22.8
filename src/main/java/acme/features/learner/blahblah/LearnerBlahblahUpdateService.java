package acme.features.learner.blahblah;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.blahblah.Blahblah;
import acme.entities.systemConfigurations.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Learner;

@Service
public class LearnerBlahblahUpdateService implements AbstractUpdateService<Learner, Blahblah> {
	
	// Internal state ---------------------------------------------------------

			@Autowired
			protected LearnerBlahblahRepository repository;

			// AbstractUpdateService<Learner, HelpRequest> interface ---------------------------


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
			public void bind(final Request<Blahblah> request, final Blahblah entity, final Errors errors) {
				assert request != null;
				assert entity != null;
				assert errors != null;

				entity.setTutorial(this.repository.findTutorialById(request.getModel().getInteger("tutorialId")));
				request.bind(entity, errors, "ticker", "caption", "summary", "cost", "startTime", "endingTime", "hlink");
			}

			@Override
			public void validate(final Request<Blahblah> request, final Blahblah entity, final Errors errors) {
				assert request != null;
				assert entity != null;
				assert errors != null;

				final List<SystemConfiguration> configurationColl = new ArrayList<>(this.repository.findAllConfigurations());
				final String acceptedCurrencies = configurationColl.get(0).getAcceptedCurrencies();
				final List<String> currencies = new ArrayList<String>();
				for(final String s : acceptedCurrencies.split(";")) {
					currencies.add(s);
				}
				
				if (!errors.hasErrors("ticker")) {
					Blahblah existing;

					existing = this.repository.findBlahblahByTicker(entity.getTicker());
					errors.state(request, existing == null || existing.getId() == entity.getId(), "ticker", "learner.helprequest.form.error.duplicated");
				}
				
				if(!errors.hasErrors("budget")) {
					errors.state(request, !(!currencies.contains(entity.getCost().getCurrency()) || entity.getCost().getCurrency() == null ||
						entity.getCost().getCurrency().length() == 0),
						"budget", "learner.helprequest.form.error.incorrectCurrency");
					errors.state(request, !(entity.getCost().getAmount() <= 0.0 || entity.getCost().getAmount() == null),
						"budget", "learner.helprequest.form.error.incorrectQuantity");
				}
				
				if(!errors.hasErrors("startTime")) {
					final Date creationTime = entity.getCreationMoment();
					final Calendar calCreation = Calendar.getInstance();
					calCreation.setTime(creationTime);
					calCreation.add(Calendar.WEEK_OF_MONTH, 1);
					final Date creationDateAfterMonth = calCreation.getTime();
					final Date startTime = entity.getStartTime();
					
					errors.state(request, (startTime.after(creationDateAfterMonth)),
						"startTime", "learner.helprequest.form.error.creationDateAfterMonth");
				}
				
				if(!errors.hasErrors("endTime") && entity.getStartTime() != null) {
						final Date startTime = entity.getStartTime();
						final Calendar calStart = Calendar.getInstance();
						calStart.setTime(startTime);
						calStart.add(Calendar.DAY_OF_MONTH, 1);
						
						final Date startDateAfterMonth = calStart.getTime();
						final Date endingTime = entity.getEndingTime();
						
						errors.state(request, (endingTime.after(startDateAfterMonth)),
							"endingTime", "learner.helprequest.form.error.startDateAfterMonth");
				}

			}


			@Override
			public void unbind(final Request<Blahblah> request, final Blahblah entity, final Model model) {
				assert request != null;
				assert entity != null;
				assert model != null;
				model.setAttribute("tutorials", this.repository.findTutorials());
				request.unbind(entity, model, "ticker", "caption", "summary", "cost", "startTime", "endingTime", "hlink","tutorial");
			}

			@Override
			public void update(final Request<Blahblah> request, final Blahblah entity) {
				assert request != null;
				assert entity != null;

				this.repository.save(entity);
			}

}
