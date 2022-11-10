package acme.features.authenticated.tutorials;

/*
 * AuthenticatedConsumerController.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.tutorials.Tutorial;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;

@Controller
@RequestMapping("/authenticated/tutorial/")
public class AuthenticatedTutorialController extends AbstractController<Authenticated, Tutorial> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedTutorialShowService showService;
	@Autowired
	protected AuthenticatedTheoryTutorialListService listTheoryService;
	@Autowired
	protected AuthenticatedLabTutorialListService listLabService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list-theory",  "list", this.listTheoryService);
		super.addCommand("list-lab",  "list", this.listLabService);
		super.addCommand("show", this.showService);
		
	}

}