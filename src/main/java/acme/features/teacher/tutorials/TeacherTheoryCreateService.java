package acme.features.teacher.tutorials;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.systemConfigurations.SystemConfiguration;
import acme.entities.tutorials.Tutorial;
import acme.entities.tutorials.TutorialType;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Teacher;
import acme.utils.SpamFilterService;

@Service
public class TeacherTheoryCreateService implements AbstractCreateService<Teacher, Tutorial> {

		// Internal state ---------------------------------------------------------

		@Autowired
		protected TeacherTutorialRepository repository;
		
		@Autowired
		protected SpamFilterService spamFilterService;

		// AbstractCreateService<Employer, Job> interface -------------------------


		@Override
		public boolean authorise(final Request<Tutorial> request) {
			assert request != null;

			return true;
		}

		@Override
		public void validate(final Request<Tutorial> request, final Tutorial entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;
			
			final List<SystemConfiguration> configurationColl = new ArrayList<>(this.repository.findAllConfigurations());
			final String acceptedCurrencies = configurationColl.get(0).getAcceptedCurrencies();
			final List<String> currencies = Arrays.asList(acceptedCurrencies.split(";"));
			
			if (!errors.hasErrors("ticker")) {
				Tutorial existing;

				existing = this.repository.findTutorialByTicker(entity.getTicker());
				errors.state(request, existing == null, "ticker", "teacher.tutorial.form.error.duplicated");
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
			
			if(!errors.hasErrors("link")) {
				errors.state(request, !this.spamFilterService.isSpam(entity.getLink()), "link", "teacher.tutorial.form.error.spam");
			}

		}

		@Override
		public void bind(final Request<Tutorial> request, final Tutorial entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			request.bind(entity, errors, "ticker");
			request.bind(entity, errors, "title", "abstractTheory", "cost", "link");
			
		}

		@Override
		public void unbind(final Request<Tutorial> request, final Tutorial entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "title","ticker", "abstractTheory", "cost", "link", "tutorialType", "isPublished");
		}

		@Override
		public Tutorial instantiate(final Request<Tutorial> request) {
			assert request != null;

			final Tutorial result;
			final Teacher teacher;

			teacher = this.repository.findTeacher(request.getPrincipal().getActiveRoleId());
			result = new Tutorial();
			result.setPublished(false);
			result.setTeacher(teacher);
			result.setTutorialType(TutorialType.THEORY);

			return result;
		}

		@Override
		public void create(final Request<Tutorial> request, final Tutorial entity) {
			assert request != null;
			assert entity != null;

			this.repository.save(entity);
		}
}
