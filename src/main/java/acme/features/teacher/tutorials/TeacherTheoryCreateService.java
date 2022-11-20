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
import acme.framework.services.AbstractCreateService;
import acme.roles.Teacher;
import acme.utils.SpamFilterService;

@Service
public class TeacherTheoryCreateService implements AbstractCreateService<Teacher, Tutorial> {

		// Internal state ---------------------------------------------------------
		
		private final String RETAIL_PRICE = "retailPrice";
		private final String DESCRIPTION = "description";

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
			
			if(!errors.hasErrors(this.RETAIL_PRICE)) {
				errors.state(request, !(!currencies.contains(entity.getCost().getCurrency()) || entity.getCost().getCurrency() == null ||
					entity.getCost().getCurrency().length() == 0),
					this.RETAIL_PRICE, "inventor.item.form.error.incorrectCurrency");
				errors.state(request, !(entity.getCost().getAmount() <= 0.0 || entity.getCost().getAmount() == null),
					this.RETAIL_PRICE, "inventor.item.form.error.incorrectQuantity");
			}
			
			if(!errors.hasErrors(this.DESCRIPTION)) {
				errors.state(request, !this.spamFilterService.isSpam(entity.getTicker()), this.DESCRIPTION, "inventor.item.form.error.spam");
			}
			
			if(!errors.hasErrors("name")) {
				errors.state(request, !this.spamFilterService.isSpam(entity.getTitle()), "name", "inventor.item.form.error.spam");
			}

		}

		@Override
		public void bind(final Request<Tutorial> request, final Tutorial entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			request.bind(entity, errors, "code");
			request.bind(entity, errors, "name", "code", "technology", this.DESCRIPTION, this.RETAIL_PRICE, "link");
			
		}

		@Override
		public void unbind(final Request<Tutorial> request, final Tutorial entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "name", "code", "technology", this.DESCRIPTION, this.RETAIL_PRICE, "link", "itemType", "isPublished");
		}

		@Override
		public Tutorial instantiate(final Request<Tutorial> request) {
			assert request != null;

			final Tutorial result;
			final Teacher inventor;

//			inventor = this.repository.find(request.getPrincipal().getActiveRoleId());
//			result = new Item();
//			result.setPublished(false);
//			result.setInventor(inventor);
//			result.setItemType(ItemType.COMPONENT);

			return result;
		}

		@Override
		public void create(final Request<Tutorial> request, final Tutorial entity) {
			assert request != null;
			assert entity != null;

			this.repository.save(entity);
		}
}
