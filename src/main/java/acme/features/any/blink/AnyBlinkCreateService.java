package acme.features.any.blink;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.blinks.Blink;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractCreateService;
import acme.utils.SpamFilterService;

@Service
public class AnyBlinkCreateService implements AbstractCreateService<Any, Blink> {
	
	// Internal state
	private static String CAPTION = "caption";
	private static String AUTHOR_ALIAS = "authorAlias";
	private static String MESSAGE = "message";
	private static String EMAIL = "email";
	private static String SPAM_ERROR = "any.blink.form.error.spam";
	private static String CONFIRM = "confirm";
	
	@Autowired
	protected AnyBlinkRepository repository;
	
	@Autowired
	protected SpamFilterService spamFilterService;
	
	// Interface
	
	@Override
	public boolean authorise(final Request<Blink> request) {
		assert request != null;
		
		return true;
	}
	
	@Override
	public Blink instantiate(final Request<Blink> request) {
		assert request != null;
		Blink result;
		result = new Blink();
		result.setCaption("");
		result.setAuthorAlias("");
		result.setEmail("");
		result.setMessage("");
		final Date dateNow = new Date();
		final Calendar cal = Calendar.getInstance();
		cal.setTime(dateNow);
		cal.add(Calendar.MILLISECOND, -100);
		result.setInstantiationMoment(cal.getTime());
		return result;
	}
	
	@Override
	public void bind(final Request<Blink> request, final Blink entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, AnyBlinkCreateService.CAPTION, AnyBlinkCreateService.AUTHOR_ALIAS, AnyBlinkCreateService.MESSAGE, AnyBlinkCreateService.EMAIL);
	}
	
	@Override
	public void validate(final Request<Blink> request, final Blink entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		errors.state(request, !this.spamFilterService.isSpam(entity.getCaption()), AnyBlinkCreateService.CAPTION, AnyBlinkCreateService.SPAM_ERROR);
		errors.state(request, !this.spamFilterService.isSpam(entity.getAuthorAlias()), AnyBlinkCreateService.AUTHOR_ALIAS, AnyBlinkCreateService.SPAM_ERROR);
		errors.state(request, !this.spamFilterService.isSpam(entity.getMessage()), AnyBlinkCreateService.MESSAGE, AnyBlinkCreateService.SPAM_ERROR);
		
		errors.state(request, request.getModel().getBoolean(AnyBlinkCreateService.CONFIRM), AnyBlinkCreateService.CONFIRM, "any.blink.form.error.must-confirm");
	}
	
	@Override
	public void unbind(final Request<Blink> request, final Blink entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, AnyBlinkCreateService.CAPTION, AnyBlinkCreateService.AUTHOR_ALIAS, AnyBlinkCreateService.MESSAGE, AnyBlinkCreateService.EMAIL, "instantiationMoment");
		model.setAttribute(AnyBlinkCreateService.CONFIRM, "false");
	}
	
	@Override
	public void create(final Request<Blink> request, final Blink entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}
}
