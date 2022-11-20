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

package acme.features.administrator.posts;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.posts.Post;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorPostCreateService implements AbstractCreateService<Administrator, Post> {

	// Internal state ---------------------------------------------------------
	
	private final String CAPTION = "caption";

	@Autowired
	protected AdministratorPostRepository	repository;

	@Autowired
	protected acme.utils.SpamFilterService						spamFilterService;

	// AbstractCreateService<Authenticated, Consumer> ---------------------------


	@Override
	public boolean authorise(final Request<Post> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Post> request, final Post entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, this.CAPTION, "message", "informational", "url");

	}

	@Override
	public void unbind(final Request<Post> request, final Post entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, this.CAPTION, "message", "informational", "url");

	}

	@Override
	public Post instantiate(final Request<Post> request) {
		assert request != null;
		final Post result = new Post();
		result.setInstantiationMoment(Date.from(Instant.now()));
		return result;
	}

	@Override
	public void validate(final Request<Post> request, final Post entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		errors.state(request, !this.spamFilterService.isSpam(entity.getCaption()), this.CAPTION, "administrator.post.form.error.caption");
		errors.state(request, !this.spamFilterService.isSpam(entity.getMessage()), "message", "administrator.post.form.error.message");
		errors.state(request, !this.spamFilterService.isSpam(entity.getUrl()), "url", "administrator.post.form.error.url");
		errors.state(request, request.getModel().getBoolean("confirm"), "confirm", "any.blink.form.error.must-confirm");

	}

	@Override
	public void create(final Request<Post> request, final Post entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
