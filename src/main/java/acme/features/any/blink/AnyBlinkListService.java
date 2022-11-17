package acme.features.any.blink;
/*
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.blinks.Blink;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyBlinkListService implements AbstractListService<Any, Blink> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyBlinkRepository repository;

	@Override
	public boolean authorise(final Request<Blink> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Blink> findMany(final Request<Blink> request) {
		assert request != null;
		
		Collection<Blink> result;
		Calendar calendar;
		Date deadline;
		Date start;
		calendar = Calendar.getInstance();
		deadline = calendar.getTime();
		calendar.add(Calendar.MONTH, -1);
		start = calendar.getTime();
		
		result = this.repository.findAllBlinks(start, deadline);
		return result;
	}

	@Override
	public void unbind(final Request<Blink> request, final Blink entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "instantiationMoment", "caption", "authorAlias", "message", "email");
	}

	// Internal state ---------------------------------------------------------

}