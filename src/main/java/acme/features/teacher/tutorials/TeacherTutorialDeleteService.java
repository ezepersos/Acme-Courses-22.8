package acme.features.teacher.tutorials;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.help_requests.HelpRequest;
import acme.entities.tutorials.Tutorial;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Teacher;

@Service
public class TeacherTutorialDeleteService implements AbstractDeleteService<Teacher, Tutorial> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected TeacherTutorialRepository repository;

	// AbstractDeleteService<Inventor, Item> interface -------------------------


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
	public void bind(final Request<Tutorial> request, final Tutorial entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "title","ticker", "abstractTheory", "cost", "link", "tutorialType");
	}

	@Override
	public void unbind(final Request<Tutorial> request, final Tutorial entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title","ticker", "abstractTheory", "cost", "link", "tutorialType", "isPublished");
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
	public void validate(final Request<Tutorial> request, final Tutorial entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Tutorial> request, final Tutorial entity) {
		assert request != null;
		assert entity != null;

		if(!entity.isPublished()) {
			Collection<HelpRequest> helpRequests;

			helpRequests = this.repository.findAllHelpRequestByTutorial(entity.getId());
			for (final HelpRequest hr : helpRequests) {
				this.repository.deleteHelpRequestById(hr.getId());
			}
			
			this.repository.delete(entity);
		}
	}
}