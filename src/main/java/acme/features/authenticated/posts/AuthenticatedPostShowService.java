package acme.features.authenticated.posts;
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

import acme.entities.posts.Post;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedPostShowService implements AbstractShowService<Authenticated, Post> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedPostRepository repository;

	@Override
	public boolean authorise(final Request<Post> request) {
		assert request != null;

		return true;
	}
	
	@Override
	public void unbind(final Request<Post> request, final Post entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model,
			"instantiationMoment", "caption", "message", "informational", "url");
	}

	@Override
	public Post findOne(final Request<Post> request) {
		assert request != null;

		Post result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findPostById(id);

		return result;
	}

	// Internal state ---------------------------------------------------------

}