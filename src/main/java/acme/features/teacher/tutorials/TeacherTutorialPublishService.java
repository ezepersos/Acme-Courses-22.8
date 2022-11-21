package acme.features.teacher.tutorials;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.systemConfigurations.SystemConfiguration;
import acme.entities.tutorials.Tutorial;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Teacher;
import acme.utils.SpamFilterService;

@Service
public class TeacherTutorialPublishService implements AbstractUpdateService<Teacher, Tutorial> {

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected TeacherTutorialRepository repository;
	
	@Autowired
	protected SpamFilterService spamFilterService;

	// AbstractUpdateService<Teacher, Tutorial> interface ---------------------------


	@Override
	public boolean authorise(final Request<Tutorial> request) {
		assert request != null;

		final boolean result;
		final int tutorialId;
		final Tutorial tutorial;
		final Teacher teacher;
		
		tutorialId = request.getModel().getInteger("id");
		tutorial = this.repository.findTutorialById(tutorialId);
		teacher = tutorial.getTeacher();
		result = !tutorial.isPublished() && request.isPrincipal(teacher);

		return result;
	}
	
	@Override
	public Tutorial findOne(final Request<Tutorial> request) {
		assert request != null;

		Tutorial result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findTutorialById(id);

		return result;
	}
	
	@Override
	public void bind(final Request<Tutorial> request, final Tutorial entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "title", "ticker", "abstractTheory", "cost", "link");
	}

	@Override
	public void validate(final Request<Tutorial> request, final Tutorial entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final List<SystemConfiguration> configurationColl = new ArrayList<>(this.repository.findAllConfigurations());
		final String acceptedCurrencies = configurationColl.get(0).getAcceptedCurrencies();
		final List<String> currencies = Arrays.asList(acceptedCurrencies.split(";"));

		if (!errors.hasErrors("code")) {
			Tutorial existing;

			existing = this.repository.findTutorialByTicker(entity.getTicker());
			errors.state(request, existing == null || existing.getId() == entity.getId(), "ticker", "teacher.tutorial.form.error.duplicated");
		}
		
		if(!errors.hasErrors("cost")) {
			errors.state(request, !(!currencies.contains(entity.getCost().getCurrency()) || entity.getCost().getCurrency() == null ||
				entity.getCost().getCurrency().length() == 0),
				"cost", "teacher.tutorial.form.error.incorrectCurrency");
			errors.state(request, !(entity.getCost().getAmount() <= 0.0 || entity.getCost().getAmount() == null),
				"cost", "teacher.tutorial.form.error.incorrectQuantity");
		}
		
		if(!errors.hasErrors("abstractTheory")) {
			errors.state(request, !this.spamFilterService.isSpam(entity.getAbstractTheory()), "abstractTheory", "teacher.tutorial.form.error.spam");
		}
		
		if(!errors.hasErrors("title")) {
			errors.state(request, !this.spamFilterService.isSpam(entity.getTitle()), "title", "teacher.tutorial.form.error.spam");
		}

	}

	@Override
	public void unbind(final Request<Tutorial> request, final Tutorial entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "ticker", "abstractTheory", "cost", "link");
	}


	@Override
	public void update(final Request<Tutorial> request, final Tutorial entity) {
		assert request != null;
		assert entity != null;

		entity.setPublished(true);
		this.repository.save(entity);
	}
}