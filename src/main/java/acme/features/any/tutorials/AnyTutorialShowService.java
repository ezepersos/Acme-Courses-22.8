package acme.features.any.tutorials;
/*
 * AuthenticatedConsumerCreateService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tutorials.Tutorial;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyTutorialShowService implements AbstractShowService<Any, Tutorial> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyTutorialRepository repository;

	@Override
	public boolean authorise(final Request<Tutorial> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Tutorial> request, final Tutorial entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "ticker", "abstractTheory", "cost", "link", "tutorialType");
		
	}

	@Override
	public Tutorial findOne(final Request<Tutorial> request) {
		assert request != null;
		Tutorial tutorial;
		int id;

		id = request.getModel().getInteger("id");
		tutorial = this.repository.findTutorialById(id);
		
		return tutorial;
	}

	// Internal state ---------------------------------------------------------

}