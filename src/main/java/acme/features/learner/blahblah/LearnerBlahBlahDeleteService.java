package acme.features.learner.blahblah;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.blahblah.Blahblah;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Learner;

@Service
public class LearnerBlahBlahDeleteService implements AbstractDeleteService<Learner, Blahblah> {
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected LearnerBlahblahRepository repository;

		// AbstractDeleteService<Inventor, Item> interface -------------------------


		@Override
		public boolean authorise(final Request<Blahblah> request) {
			assert request != null;
			
			return true;
		}
		
		@Override
		public void bind(final Request<Blahblah> request, final Blahblah entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;
			
			request.bind(entity, errors, "ticker", "caption", "summary", "cost", "startTime", "endingTime", "hlink");
		}

		@Override
		public void unbind(final Request<Blahblah> request, final Blahblah entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			
			model.setAttribute("tutorials", this.repository.findTutorials());
			request.unbind(entity, model, "ticker", "caption", "summary", "cost", "startTime", "endingTime", "hlink");
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
		public void validate(final Request<Blahblah> request, final Blahblah entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;
		}

		@Override
		public void delete(final Request<Blahblah> request, final Blahblah entity) {
			assert request != null;
			assert entity != null;
			
			this.repository.delete(entity);
			}

}
